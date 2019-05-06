package astvisitors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import ast.AstNode;
import cstvisitors.BuildAstVisitor;
import exceptions.ErrorHandler;
import generated.EzuinoLexer;
import generated.EzuinoParser;

public class MissingReturnStmtTest {

    @Test
    public void missingReturn() {
        String program = "func int main() { }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void hasReturn() {
        String program = "func int main() { return 2 }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void elseBlockMissingReturn() {
        String program = "func int main() { if(1<2) { return 1 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void ifBlockMissingReturn() {
        String program = "func int main() { if(1<2) { } else { return 2 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void ifElseBlockHasReturn() {
        String program = "func int main() { if(1<2) { return 1 } else { return 2 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void whileTestNotGuaranteedReturn() throws IOException {
        String testProgram = "func int main() {\n" +
                "    while(1<2){\n" +
                "        return 1\n" +
                "    }\n" +
                "}\n";
        ErrorHandler e = parseProgram(testProgram);
        assertTrue(e.hasErrors());
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
        ErrorHandler e = parseProgram(testProgram);
        assertTrue(e.hasErrors());
    }

    @Test
    public void elseStmtHaveNoReturnGivesError() throws IOException {
        String testProgram = "func int hello(){\n" +
                "  int a\n" +
                "  if(true){\n" +
                "        return a\n" +
                "  } else{} \n" +
                "}";
        ErrorHandler e = parseProgram(testProgram);
        assertTrue(e.hasErrors());
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
        ErrorHandler e = parseProgram(testProgram);
        assertTrue(e.hasErrors());
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
        ErrorHandler e = parseProgram(testProgram);
        assertTrue(e.hasErrors());
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
        ErrorHandler e = parseProgram(testProgram);
        assertTrue(e.hasErrors());
    }

    @Test
    public void missingElseStmtReturnError() throws IOException {
        String testProgram = "func int hello(){\n" +
                "  int a\n" +
                "  if(true){\n" +
                "        return a\n" +
                "  } \n" +
                "}";
        ErrorHandler e = parseProgram(testProgram);
        assertTrue(e.hasErrors());

    }

    private ErrorHandler parseProgram(String program) {
        CharStream stream = CharStreams.fromString(program);
        EzuinoLexer lexer = new EzuinoLexer(stream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        EzuinoParser parser = new EzuinoParser(tokenStream);
        ParseTree parseTree = parser.start();
        AstNode ast = parseTree.accept(new BuildAstVisitor());

        ErrorHandler errorHandler = new ErrorHandler();
        ast.accept(new SymbolTableVisitor(false, errorHandler));
        ast.accept(new Typechecker(errorHandler));
        ast.accept(new ReturnStmtTypeCheckVisitor(errorHandler));
        ast.accept(new MissingReturnStmtVisitor(errorHandler));
        return errorHandler;
    }

}
