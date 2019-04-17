package astvisitors;

import ast.AstNode;
import cstvisitors.BuildAstVisitor;
import exceptions.ErrorHandler;
import generated.EzuinoLexer;
import generated.EzuinoParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

public class TypecheckerTest {

    private SymbolTableVisitor symbolTableVisitor;
    private BuildAstVisitor buildAstVisitor;
    private Typechecker typechecker;

    @Before
    void init(){
        this.symbolTableVisitor = new SymbolTableVisitor();
        this.buildAstVisitor = new BuildAstVisitor();
        this.typechecker = new Typechecker();
    }


    @Test
    public void nestedIfStmtsOuterScopeElseReturnValue() throws IOException {
        String testProgram = "func int hello(){\n" +
                "  if(true){\n" +
                "    if(true){\n" +
                "        if(true){\n" +
                "            return 1\n" +
                "        }\n" +
                "    }\n" +
                "  }\n" +
                " else{" +
                "   return 1" +
                "} " +
                "  \n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
        assertFalse(ErrorHandler.hasErrors());
    }

    @Test
    public void nestedIfStmtsOuterScopeElseDoNotReturnValueGivesError() throws IOException {
        String testProgram = "func int hello(){\n" +
                "  if(true){\n" +
                "    if(true){\n" +
                "        if(true){\n" +
                "            return 1\n" +
                "        }\n" +
                "    }\n" +
                "  }\n" +
                " else{" +
                "  " +
                "} " +
                "  \n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
        assertTrue(ErrorHandler.hasErrors());
    }

    @Test
    public void nestedIfStmtsOuterScopeElseDoNotExistGivesError() throws IOException {
        String testProgram = "func int hello(){\n" +
                "  if(true){\n" +
                "    if(true){\n" +
                "        if(true){\n" +
                "            return 1\n" +
                "        }\n" +
                "    }\n" +
                "  }\n" +
                "  \n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
        assertTrue(ErrorHandler.hasErrors());
    }

    @Test
    public void emptyIfStmtAndReturnValue() throws IOException {
        String testProgram = "func int hello(){\n" +
                "    if(true) {\n" +
                "\n" +
                "    }\n" +
                "    return 1\n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
        assertFalse(ErrorHandler.hasErrors());
    }

    @Test
    public void multipleIfsSameLevel() throws IOException {
        String testProgram = "func int hello(){\n" +
                "    if(true) {\n" +
                "        int a\n" +
                "        return a\n" +
                "    }\n" +
                "    if(true){\n" +
                "        int b\n" +
                "        return b\n" +
                "    }\n" +
                "    if(true) {\n" +
                "        int c\n" +
                "        return c\n" +
                "    }\n" +
                "    else{" +
                "       return 1" +
                "    }" +
                "}";
        testWithTableAndTypeChecker(testProgram);
        ErrorHandler.printErrorList();
        assertFalse(ErrorHandler.hasErrors());
    }

    @Test
    public void multipleIfsSameLevelNoElseOuterScopeGivesError() throws IOException {
        String testProgram = "func int hello(){\n" +
                "    if(true) {\n" +
                "        int a\n" +
                "        return a\n" +
                "    }\n" +
                "    if(true){\n" +
                "        int b\n" +
                "        return b\n" +
                "    }\n" +
                "    if(true) {\n" +
                "        int c\n" +
                "        return c\n" +
                "    }\n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
        assertTrue(ErrorHandler.hasErrors());
    }

    @Test
    public void missingElseStmtReturnError() throws IOException {
        String testProgram = "func int hello(){\n" +
                "  int a\n" +
                "  if(true){\n" +
                "        return a\n" +
                "  } \n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
        assertTrue(ErrorHandler.hasErrors());

    }

    @Test
    public void elseStmtHaveNoReturnGivesError() throws IOException {
        String testProgram = "func int hello(){\n" +
                "  int a\n" +
                "  if(true){\n" +
                "        return a\n" +
                "  } else{} \n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
        assertTrue(ErrorHandler.hasErrors());
    }

    @Test
    public void returnAsBreakReturnTypeError() throws IOException {
        String testProgram = "func hello(){\n" +
                "  if(true){\n" +
                "    return 1\n" +
                "  }\n" +
                "  return\n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
        assertTrue(ErrorHandler.hasErrors());
    }

    @Test
    public void returnAsBreakReturn() throws IOException {
        String testProgram = "func hello(){\n" +
                "  if(true){\n" +
                "    return\n" +
                "  }\n" +
                "  return\n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
        assertFalse(ErrorHandler.hasErrors());
    }

    /* Gives error type mismatch, return statements are not the same type */
    @Test
    public void returnTwoDifferentTypesError() throws IOException {
        String testProgram = "func int hello(){\n" +
                "  if(true){\n" +
                "    return 3.4\n" +
                "  }\n" +
                "  else {\n" +
                "    return 4\n" +
                "  }\n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
        assertTrue(ErrorHandler.hasErrors());
    }

    @Test
    public void whileTest() throws IOException {
        String testProgram = "func int main() {\n" +
                "    while(1<2){\n" +
                "        return 1\n" +
                "    }\n" +
                "    return 1\n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
        assertFalse(ErrorHandler.hasErrors());
    }

    @Test
    public void whileTestNotGuaranteedReturn() throws IOException {
        String testProgram = "func int main() {\n" +
                "    while(1<2){\n" +
                "        return 1\n" +
                "    }\n" +
                "}\n";
        testWithTableAndTypeChecker(testProgram);
        assertTrue(ErrorHandler.hasErrors());
    }

    @Test
    public void whileTestTypeNotTheSame() throws IOException {
        String testProgram = "func int main() {\n" +
                "    while(1<2){\n" +
                "        return \"hello\"\n" +
                "    }\n" +
                "    return \"my world\"\n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
        assertTrue(ErrorHandler.hasErrors());
    }

    @Test
    public void whileTestVoidFunc() throws IOException {
        String testProgram = "func main() {\n" +
                "    while(1<2){\n" +
                "        int a\n" +
                "    }\n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
        assertFalse(ErrorHandler.hasErrors());
    }

    @Test
    public void funcWithWhileStmtWithoutReturn() throws IOException {
        String testProgram = "func int main() {\n" +
                "    while(1<2){\n" +
                "        int a\n" +
                "    }\n" +
                "    return 1\n" +
                "}\n";
        testWithTableAndTypeChecker(testProgram);
        assertFalse(ErrorHandler.hasErrors());
    }

    private EzuinoParser createParser(String testString) throws IOException {
        CharStream stream = CharStreams.fromString(testString);
        EzuinoLexer lexer = new EzuinoLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        return parser;
    }

    private void testWithTableAndTypeChecker(String testProgram) throws IOException {
        EzuinoParser ezuinoParser = createParser(testProgram);
        AstNode astNode = ezuinoParser.start().accept(buildAstVisitor);
        astNode.accept(symbolTableVisitor);
        astNode.accept(typechecker);
    }
}
