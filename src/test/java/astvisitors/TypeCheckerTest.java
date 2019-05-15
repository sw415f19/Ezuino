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

public class TypeCheckerTest {

    @Test
    public void wrongIfExprType() throws IOException {
        String program = "func Setup() {if(1){}}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void correctIfExprType() throws IOException {
        String program = "func Setup() {if(true){}}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void wrongVarIfExprType() throws IOException {
        String program = "func Setup() {int a if(a){}}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void correctVarIfExprType() throws IOException {
        String program = "func Setup() {boolean a if(a){}}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void wrongWhileExprType() throws IOException {
        String program = "func Setup() {while(1){}}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void correctWhileExprType() throws IOException {
        String program = "func Setup() {while(true){}}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void wrongAssignBooleanExpr() throws IOException {
        String program = "func Setup() {boolean b b := true AND 1}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void correctAssignBooleanExpr() throws IOException {
        String program = "func Setup() {boolean b b := true AND false}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void wrongEqualityCheck() throws IOException {
        String program = "func Setup() {boolean b b := true = 1}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void correctEqualityCheck() throws IOException {
        String program = "func Setup() {boolean b b := true = false}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void correctDoubleEqualityCheck() throws IOException {
        String program = "func Setup() {boolean b b := 2.0 = 2.1}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void intNotOperator() throws IOException {
        String program = "func Setup() {int b b := !2}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void intMinusOperator() throws IOException {
        String program = "func Setup() {int b b := -2}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void doubleNotOperator() throws IOException {
        String program = "func Setup() {double b b := !2.0}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void doubleMinusOperator() throws IOException {
        String program = "func Setup() {double b b := -2.0}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void booleanNotOperator() throws IOException {
        String program = "func Setup() {boolean b b := !true}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void notStringTest() throws IOException {
        String program = "func Setup() {string s s := !\"test\"}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void minusStringTest() throws IOException {
        String program = "func Setup() {string s s := -\"test\"}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void notIntegerTest() throws IOException {
        String program = "func Setup() {int i i := !1}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void minusIntegerTest() throws IOException {
        String program = "func Setup() {int i i := -1}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void notDoubleTest() throws IOException {
        String program = "func Setup() {double d d := !1.0}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void minusDoubleTest() throws IOException {
        String program = "func Setup() {double d d := -1.0}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void notBooleanTest() throws IOException {
        String program = "func Setup() {boolean b b := !true}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void minusBooleanTest() throws IOException {
        String program = "func Setup() {boolean b b := -true}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void addString() throws IOException {
        String program = "func Setup() {string s s := \"1\" + \"2\"}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void addBoolean() throws IOException {
        String program = "func Setup() {boolean b b := true + false}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void addInteger() throws IOException {
        String program = "func Setup() {int i i := 1 + 1}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void addDouble() throws IOException {
        String program = "func Setup() {double d d := 1.0 + 1.0}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void subtractString() throws IOException {
        String program = "func Setup() {string s s := \"1\" - \"2\"}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void subtractBoolean() throws IOException {
        String program = "func Setup() {boolean b b := true - false}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void subtractInteger() throws IOException {
        String program = "func Setup() {int i i := 1 - 1}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void subtractDouble() throws IOException {
        String program = "func Setup() {double d d := 1.0 - 1.0}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void multiplyString() throws IOException {
        String program = "func Setup() {string s s := \"1\" * \"2\"}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void multiplyBoolean() throws IOException {
        String program = "func Setup() {boolean b b := true * false}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void multiplyInteger() throws IOException {
        String program = "func Setup() {int i i := 1 * 1}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void multiplyDouble() throws IOException {
        String program = "func Setup() {double d d := 1.0 * 1.0}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void divideString() throws IOException {
        String program = "func Setup() {string s s := \"1\" / \"2\"}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void divideBoolean() throws IOException {
        String program = "func Setup() {boolean b b := true / false}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void divideInteger() throws IOException {
        String program = "func Setup() {int i i := 1 / 1}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void divideDouble() throws IOException {
        String program = "func Setup() {double d d := 1.0 / 1.0}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
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
        astNode.accept(new TypeChecker(errorhandler));
        return errorhandler;
    }
}
