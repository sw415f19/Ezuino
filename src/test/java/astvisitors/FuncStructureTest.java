package astvisitors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

public class FuncStructureTest {

    @Test
    public void correctFunctionCall() {
        String program = "func main(int a, string b) {} main(1, \"123\")";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void wrongTypeFunctionCall() {
        String program = "func main(int a, string b) {} main(1, 123)";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void tooFewParametersFunctionCall() {
        String program = "func main(int a, string b) {} main(1)";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void tooManyParametersFunctionCall() {
        String program = "func main(int a, string b) {} main(1, 1, 2)";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void printWrongType() {
        String program = "Print(1321)";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void printWrongLength() {
        String program = "Print(1321, 2)";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void printCorrectSignature() {
        String program = "Print(\"test\")";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
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
        ast.accept(new TypeChecker(errorHandler));
        ast.accept(new FuncStructureVisitor(errorHandler));
        return errorHandler;
    }
}
