package astvisitors;

import ast.AstNode;
import astvisitors.SymbolTableVisitor;
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

public class SymbolTableVisitorTest {

    @Test
    public void intDclNodeTest() throws IOException {
        String testProgram = "int number\n" +
                " \n" +
                "func main() {\n" +
                "    int a\n" +
                "    int a\n" +
                "    a := 1\n" +
                "    if (number < 100) {\n" +
                "        int a\n" +
                "        b := 2\n" +
                "    }\n" +
                "    if (number = 100) {\n" +
                "       int b\n" +
                "       c := 2\n" +
                "    }\n" +
                "}";
        ErrorHandler e = testProgram(testProgram);
        assertTrue(e.hasErrors());
    }

    @Test
    public void doesNotViewVariableDeclarationAsFunction() throws IOException {
        String testProgram = "int b\n" +
                "int a\n" +
                "b := a()";
        ErrorHandler e = testProgram(testProgram);
        assertTrue(e.hasErrors());
    }

    @Test
    public void funcStmtAnd() throws IOException {
        String testProgram = "int a func int b() {return 4} a := b()";
        ErrorHandler e = testProgram(testProgram);
        assertFalse(e.hasErrors());
    }

    @Test
    public void funcCallInFuncDefWrong() throws IOException {
        String testProgram = "func main() { stuff(321) } ";
        ErrorHandler e = testProgram(testProgram);
        assertTrue(e.hasErrors());
    }

    @Test
    public void funcCallInFuncDefCorrect() throws IOException {
        String program = "func stuff() { } func main() { stuff() }";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void undefinedVarInExprInFuncDef() throws IOException {
        String program = "func main() { if(a) { } }";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedVarInExprInFuncDef() throws IOException {
        String program = "func main() { boolean a if(a) { } }";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void undefinedVarInIfExpr() throws IOException {
        String program = "if (a) { }";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedVarInIfExpr() throws IOException {
        String program = "boolean a if (a) {}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void undefinedVarInWhileExpr() throws IOException {
        String program = "while (a) { }";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedVarInWhileExpr() throws IOException {
        String program = "boolean a while (a) {}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void undefinedVarFuncCall() throws IOException {
        String program = "print(a)";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedVarFuncCall() throws IOException {
        String program = "string a Print(a)";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void undefinedVarInIfBlock() throws IOException {
        String program = "if(1<2) {a := 4}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedVarInIfBlock() throws IOException {
        String program = "int a if(1<2) {a := 4}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void undefinedVarInWhileBlock() throws IOException {
        String program = "while(1<2) {a := 4}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedVarInWhileBlock() throws IOException {
        String program = "int a while(1<2) {a := 4}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void undefinedFuncCallAsExpr() throws IOException {
        String program = "if(main()) {}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedVarInAssignStmt() throws IOException {
        String program = "int a func int test() {return 4} a := test()";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void undefinedVarInAssignStmt() throws IOException {
        String program = "int a a := test()";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedFuncCallAsExpr() throws IOException {
        String program = "func boolean main() {return true} if(main()) {}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void definedVarOutsideScope() throws IOException {
        String program = "int a func boolean main() {a := 4}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void definedInOtherScope() throws IOException {
        String program = "if(1<2) {int a} else {a := 4}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedInOtherScopeAndSameScope() throws IOException {
        String program = "if(1<2) {int a} else {int a a := 4}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void doubleDcl() throws IOException {
        String program = "int a int a";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void dclInNestedScope() throws IOException {
        String program = "int a func test() {int a}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void useParameter() throws IOException {
        String program = "func test(int a) {a := 4}";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void doubleDclParameter() throws IOException {
        String program = "func test(int a) {int a}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void doubleDclParameter2() throws IOException {
        String program = "func test(int a, int b) {int b}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void doubleDclParameter3() throws IOException {
        String program = "func test(int a, int a) {}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void doubleDclFuncDef() throws IOException {
        String program = "func test() {int a int a}";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    private ErrorHandler testProgram(String testString) throws IOException {

        CharStream stream = CharStreams.fromString(testString);

        EzuinoLexer lexer = new EzuinoLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        BuildAstVisitor buildAstVisitor = new BuildAstVisitor();
        ParseTree parseTree = parser.start();
        AstNode astNode = parseTree.accept(buildAstVisitor);

        ErrorHandler errorhandler = new ErrorHandler();
        boolean printDcl = false;
        SymbolTableVisitor symbolTableVisitor = new SymbolTableVisitor(printDcl, errorhandler);
        astNode.accept(symbolTableVisitor);
        return errorhandler;
    }

}