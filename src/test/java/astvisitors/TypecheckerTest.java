package astvisitors;

import ast.AstNode;
import cstvisitors.BuildAstVisitor;
import exceptions.ErrorHandler;
import generated.EzuinoLexer;
import generated.EzuinoParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

public class TypecheckerTest {

    @Test
    public void wrongIfExprType() throws IOException {
        String program = "if(1){}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void correctIfExprType() throws IOException {
        String program = "if(true){}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void wrongVarIfExprType() throws IOException {
        String program = "int a if(a){}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void correctVarIfExprType() throws IOException {
        String program = "boolean a if(a){}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void wrongWhileExprType() throws IOException {
        String program = "while(1){}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void correctWhileExprType() throws IOException {
        String program = "while(true){}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void wrongAssignBooleanExpr() throws IOException {
        String program = "boolean b b := true AND 1";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void correctAssignBooleanExpr() throws IOException {
        String program = "boolean b b := true AND false";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void wrongEqualityCheck() throws IOException {
        String program = "boolean b b := true = 1";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void correctEqualityCheck() throws IOException {
        String program = "boolean b b := true = false";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void correctDoubleEqualityCheck() throws IOException {
        String program = "boolean b b := 2.0 = 2.1";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void intNotOperator() throws IOException {
        String program = "int b b := !2";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void intMinusOperator() throws IOException {
        String program = "int b b := -2";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void doubleNotOperator() throws IOException {
        String program = "double b b := !2.0";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void doubleMinusOperator() throws IOException {
        String program = "double b b := -2.0";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void booleanNotOperator() throws IOException {
        String program = "boolean b b := !true";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void booleanMinusOperator() throws IOException {
        String program = "double b b := -true";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    private ErrorHandler testProgram(String program) throws IOException {
        CharStream stream = CharStreams.fromString(program);

        EzuinoLexer lexer = new EzuinoLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        ParseTree parseTree = parser.start();
        AstNode astNode = parseTree.accept(new BuildAstVisitor());

        ErrorHandler errorhandler = new ErrorHandler();
        boolean printDcl = false;
        astNode.accept(new SymbolTableVisitor(printDcl, errorhandler));
        astNode.accept(new Typechecker(errorhandler));
        return errorhandler;
    }
}
