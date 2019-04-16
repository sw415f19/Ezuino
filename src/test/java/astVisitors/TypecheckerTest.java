package astVisitors;

import ast.AstNode;
import astvisitors.SymbolTableVisitor;
import astvisitors.Typechecker;
import cstvisitors.BuildAstVisitor;
import generated.EzuinoLexer;
import generated.EzuinoParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import java.io.IOException;

public class TypecheckerTest {
    private SymbolTableVisitor symbolTableVisitor = new SymbolTableVisitor();
    private BuildAstVisitor buildAstVisitor = new BuildAstVisitor();
    private Typechecker typechecker = new Typechecker();


    @Test
    public void nestedIfStmtsOuterScopeElseReturnValue() throws IOException {
        String testProgram = "func int hello(){\n" +
                "  if(TRUE){\n" +
                "    if(TRUE){\n" +
                "        if(TRUE){\n" +
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
    }

    @Test
    public void nestedIfStmtsOuterScopeElseDoNotReturnValue() throws IOException {
        String testProgram = "func int hello(){\n" +
                "  if(TRUE){\n" +
                "    if(TRUE){\n" +
                "        if(TRUE){\n" +
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
    }

    @Test
    public void nestedIfStmtsOuterScopeElseDoNotExist() throws IOException {
        String testProgram = "func int hello(){\n" +
                "  if(TRUE){\n" +
                "    if(TRUE){\n" +
                "        if(TRUE){\n" +
                "            return 1\n" +
                "        }\n" +
                "    }\n" +
                "  }\n" +
                "  \n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
    }

    @Test
    public void emptyIfStmtAndReturnValue() throws IOException {
        String testProgram = "func int hello(){\n" +
                "    if(TRUE) {\n" +
                "\n" +
                "    }\n" +
                "    return 1\n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
    }

    @Test
    public void multipleIfsSameLevel() throws IOException {
        String testProgram = "func int hello(){\n" +
                "    if(TRUE) {\n" +
                "        int a\n" +
                "        return a\n" +
                "    }\n" +
                "    if(TRUE){\n" +
                "        int b\n" +
                "        return b\n" +
                "    }\n" +
                "    if(TRUE) {\n" +
                "        int c\n" +
                "        return c\n" +
                "    }\n" +
                "    else{" +
                "       return 1" +
                "    }" +
                "}";
        testWithTableAndTypeChecker(testProgram);
    }

    @Test
    public void multipleIfsSameLevelNoElseOuterScopeGivesError() throws IOException {
        String testProgram = "func int hello(){\n" +
                "    if(TRUE) {\n" +
                "        int a\n" +
                "        return a\n" +
                "    }\n" +
                "    if(TRUE){\n" +
                "        int b\n" +
                "        return b\n" +
                "    }\n" +
                "    if(TRUE) {\n" +
                "        int c\n" +
                "        return c\n" +
                "    }\n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
    }

    @Test
    public void missingElseStmtReturnError() throws IOException {
        String testProgram = "func int hello(){\n" +
                "  int a\n" +
                "  if(TRUE){\n" +
                "        return a\n" +
                "  } \n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
    }

    @Test
    public void elseStmtHaveNoReturnGivesError() throws IOException {
        String testProgram = "func int hello(){\n" +
                "  int a\n" +
                "  if(TRUE){\n" +
                "        return a\n" +
                "  } else{} \n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
    }

    @Test
    public void returnAsBreakReturnTypeError() throws IOException {
        String testProgram = "func hello(){\n" +
                "  if(TRUE){\n" +
                "    return 1\n" +
                "  }\n" +
                "  return\n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
    }

    @Test
    public void returnAsBreakReturn() throws IOException {
        String testProgram = "func hello(){\n" +
                "  if(TRUE){\n" +
                "    return\n" +
                "  }\n" +
                "  return\n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
    }

    /* Gives error type mismatch, return statements are not the same type */
    @Test
    public void returnTwoDifferentTypesError() throws IOException {
        String testProgram = "func int hello(){\n" +
                "  if(TRUE){\n" +
                "    return 3.4\n" +
                "  }\n" +
                "  else {\n" +
                "    return 4\n" +
                "  }\n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
    }



    private EzuinoParser createParser(String testString) throws IOException {
        CharStream stream = CharStreams.fromString(testString);
        EzuinoLexer lexer = new EzuinoLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        return parser;
    }

    public void testWithTableAndTypeChecker(String testProgram) throws IOException {
        EzuinoParser ep = createParser(testProgram);
        AstNode astNode = ep.start().accept(buildAstVisitor);
        astNode.accept(symbolTableVisitor);
        astNode.accept(typechecker);
    }
}
