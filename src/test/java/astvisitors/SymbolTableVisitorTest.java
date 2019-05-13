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

public class SymbolTableVisitorTest {

    @Test
    public void intDclNodeTest() {
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
        ErrorHandler e = parseProgram(testProgram);
        assertTrue(e.hasErrors());
    }

    @Test
    public void doesNotViewVariableDeclarationAsFunction() {
        String testProgram = "int b\n" +
                "int a\n" +
                "b := a()";
        ErrorHandler e = parseProgram(testProgram);
        assertTrue(e.hasErrors());
    }

    @Test
    public void funcStmtAnd() {
        String testProgram = "int a func int b() {return 4} a := b()";
        ErrorHandler e = parseProgram(testProgram);
        assertFalse(e.hasErrors());
    }

    @Test
    public void funcCallInFuncDefWrong() {
        String testProgram = "func main() { stuff(321) } ";
        ErrorHandler e = parseProgram(testProgram);
        assertTrue(e.hasErrors());
    }

    @Test
    public void funcCallInFuncDefCorrect() {
        String program = "func stuff() { } func main() { stuff() }";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void undefinedVarInExprInFuncDef() {
        String program = "func main() { if(a) { } }";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedVarInExprInFuncDef() {
        String program = "func main() { boolean a if(a) { } }";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void undefinedVarInIfExpr() {
        String program = "if (a) { }";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedVarInIfExpr() {
        String program = "boolean a if (a) {}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void undefinedVarInWhileExpr() {
        String program = "while (a) { }";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedVarInWhileExpr() {
        String program = "boolean a while (a) {}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void undefinedVarFuncCall() {
        String program = "print(a)";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedVarFuncCall() {
        String program = "string a Print(a)";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void undefinedVarInIfBlock() {
        String program = "if(1<2) {a := 4}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedVarInIfBlock() {
        String program = "int a if(1<2) {a := 4}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void undefinedVarInWhileBlock() {
        String program = "while(1<2) {a := 4}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedVarInWhileBlock() {
        String program = "int a while(1<2) {a := 4}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void undefinedFuncCallAsExpr() {
        String program = "if(main()) {}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedVarInAssignStmt() {
        String program = "int a func int test() {return 4} a := test()";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void undefinedVarInAssignStmt() {
        String program = "int a a := test()";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedFuncCallAsExpr() {
        String program = "func boolean main() {return true} if(main()) {}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void definedVarOutsideScope() {
        String program = "int a func boolean main() {a := 4}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void definedInOtherScope() {
        String program = "if(1<2) {int a} else {a := 4}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void definedInOtherScopeAndSameScope() {
        String program = "if(1<2) {int a} else {int a a := 4}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void doubleDcl() {
        String program = "int a int a";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void dclInNestedScope() {
        String program = "int a func test() {int a}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void useParameter() {
        String program = "func test(int a) {a := 4}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void doubleDclParameter() {
        String program = "func test(int a) {int a}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void doubleDclParameter2() {
        String program = "func test(int a, int b) {int b}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void doubleDclParameter3() {
        String program = "func test(int a, int a) {}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void doubleDclFuncDef() {
        String program = "func test() {int a int a}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void funcDefWithinFuncdef() {
        String program = "func main() {func main2() {}}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void funcDefSibling() {
        String program = "func main() {} func main2() {}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void funcWithinIf() {
        String program = "if(1<2) {func main() {}}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void funcWithinElse() {
        String program = "if(1<2) {} else {func main() {}}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void funcWithinWhile() {
        String program = "while(1<2) {func main() {}}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void doubleFuncDcl() {
        String program = "func main() {} func main() {}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void doubleFuncDclInIf() {
        String program = "if(1<2) { func main() {} }  func main() {}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void doubleFuncDclDiffReturnType() {
        String program = "func int main() {} func double main() {}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void doubleFuncDclDiffParam() {
        String program = "func main(int a) {} func main(double a) {}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void doubleFuncDclDiffParamAndReturnType() {
        String program = "func int main(int a) {} func double main(double a) {}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }

    private ErrorHandler parseProgram(String program) {

        CharStream stream = CharStreams.fromString(program);

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