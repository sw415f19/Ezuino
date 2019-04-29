package astvisitors;

import ast.AstNode;
import astvisitors.CCodeGenerationVisitor;
import astvisitors.SymbolTableVisitor;
import astvisitors.Typechecker;
import cstvisitors.BuildAstVisitor;
import generated.EzuinoLexer;
import generated.EzuinoParser;
import exceptions.ErrorHandler;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CCodeGenerationVisitorTest {
    // Each generated code ends with a blank line, therefor every expected string ends with a \n

    //    id referencing (int a\n a := a + 1)
    //    Func_callExprNode
    //    CustomFuncCallStmtNode // print
    //    BlockNode
    //    Func_defNode  // done
    //    Return_stmtNode
    //    If_stmtNode
    //    StartNode  // no code
    //    BooleanLiteral  // done
    //    StmtsNode
    //    DclNode  // done
    //    DclsNode  // done
    //    While_stmtNode
    //    AdditiveExprNode  // done
    //    MultiplicativeExprNode  // done
    //    LogicalAndExprNode  // done
    //    LogicalOrExprNode  // done
    //    RelationalExprNode  // done?
    //    EqualityExprNode  // done?
    //    ParenthesisExprNode  // done
    //    UnaryExprNode  // done
    //    Assign_stmtNode  // done
    //    IntegerLiteral  // done
    //    DoubleLiteral  // done
    //    StringLiteral  // done
    //    IdNode  // done

    @Test
    public void falseParenthesisWhile_stmtTest() throws IOException {
        String program = "while ((((0)))) {\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "while ((((0)))) {\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void trueParenthesisWhile_stmtTest() throws IOException {
        String program = "while ((((1)))) {\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "while ((((1)))) {\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void notEqualWhile_stmtTest() throws IOException {
        String program = "while (1 != 2) {\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "while (1!=2) {\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void equalWhile_stmtTest() throws IOException {
        String program = "while (1 = 2) {\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "while (1==2) {\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void greaterOrEqualWhile_stmtTest() throws IOException {
        String program = "while (1 >= 2) {\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "while (1>=2) {\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void lessOrEqualWhile_stmtTest() throws IOException {
        String program = "while (1 <= 2) {\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "while (1<=2) {\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void greaterWhile_stmtTest() throws IOException {
        String program = "while (1 > 2) {\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "while (1>2) {\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void lessWhile_stmtTest() throws IOException {
        String program = "while (1 < 2) {\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "while (1<2) {\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void dclsNodeTest() throws IOException {
        String program = "int i\n" +
                "double d\n" +
                "string s\n" +
                "boolean b";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int i;\n" +
                "double d;\n" +
                "char s[256];\n" +
                "int b;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void booleanDclTest() throws IOException {
        String program = "boolean b";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int b;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void stringDclTest() throws IOException {
        String program = "string s";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "char s[256];\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void doubleDclTest() throws IOException {
        String program = "double d";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "double d;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void intDclTest() throws IOException {
        String program = "int i";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int i;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void stringFunc_defTest() throws IOException {
        String program = "string s\n" +
                "func string b(string s) {\n" +
                "return s\n" +
                "}\n" +
                "s := b(\"Hello world!\")";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "char s[256];\n" +
                "char b(char s[256]) {\n" +
                "return s;\n" +
                "}\n" +
                "strcpy(s, b(\"Hello world!\"));\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void doubleFunc_defTest() throws IOException {
        String program = "double a\n" +
                "func double b(double a) {\n" +
                "return a\n" +
                "}\n" +
                "a := b(1.23)";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "double a;\n" +
                "double b(double a) {\n" +
                "return a;\n" +
                "}\n" +
                "a = b(1.23);\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void intFunc_defTest() throws IOException {
        String program = "int a\n" +
                "func int b(int a) {\n" +
                "return a\n" +
                "}\n" +
                "a := b(1)";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int a;\n" +
                "int b(int a) {\n" +
                "return a;\n" +
                "}\n" +
                "a = b(1);\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void voidFunc_defTest() throws IOException {
        String program = "int a\n" +
                "func b(int a) {\n" +
                "return\n" +
                "}\n" +
                "a := b(1)";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int a;\n" +
                "void b(int a) {\n" +
                "return;\n" +
                "}\n" +
                "a = b(1);\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void multipleParenthesisExprTest() throws IOException {
        String program = "int a\n" +
                "a := (100 + (10 + ((1 + 1000))))";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int a;\n" +
                "a = (100+(10+((1+1000))));\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    // will most likely fail when we change how to concatenate strings in the C code generator
    @Test
    public void stringParenthesisExprTest() throws IOException {
        String program = "string s\n" +
                "s := (\"Hello world!\")";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "char s[256];\n" +
                "strcpy(s, (\"Hello world!\"));\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void doubleParenthesisExprTest() throws IOException {
        String program = "double a\n" +
                "a := (34.115)";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "double a;\n" +
                "a = (34.115);\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void integerParenthesisExprTest() throws IOException {
        String program = "int a\n" +
                "a := (100)";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int a;\n" +
                "a = (100);\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void DoubleUnaryExprTest() throws IOException {
        String program = "double a\n" +
                "a := -23.12";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "double a;\n" +
                "a = -23.12;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void integerUnaryExprTest() throws IOException {
        String program = "int a\n" +
                "a := -100";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int a;\n" +
                "a = -100;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void trueFalseUnaryExprTest() throws IOException {
        String program = "if (!true AND !false) {\n" +
                "return 1\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "if (!1&&!0) {\n" +
                "return 1;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void longNotFalseUnaryExprTest() throws IOException {
        String program = "if (!(false)) {\n" +
                "return 1\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "if (!(0)) {\n" +
                "return 1;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void shortNotFalseUnaryExprTest() throws IOException {
        String program = "if (!false) {\n" +
                "return 1\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "if (!0) {\n" +
                "return 1;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void longNotTrueUnaryExprTest() throws IOException {
        String program = "if (!(true)) {\n" +
                "return 1\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "if (!(1)) {\n" +
                "return 1;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void shortNotTrueUnaryExprTest() throws IOException {
        String program = "if (!true) {\n" +
                "return 1\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "if (!1) {\n" +
                "return 1;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void divideMultiplicativeExprTest() throws IOException {
        String program = "int a\n" +
                "a := 1 / 2";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int a;\n" +
                "a = 1/2;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void multiplyMultiplicativeExprTest() throws IOException {
        String program = "int a\n" +
                "a := 1 * 2";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int a;\n" +
                "a = 1*2;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void minusAdditiveExprTest() throws IOException {
        String program = "int a\n" +
                "a := 1 - 2";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int a;\n" +
                "a = 1-2;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void plusAdditiveExprTest() throws IOException {
        String program = "int a\n" +
                "a := 1 + 2";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int a;\n" +
                "a = 1+2;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void greaterOrEqualRelationalExprTest() throws IOException {
        String program = "if (1 >= 2) {\n" +
                "return 1\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "if (1>=2) {\n" +
                "return 1;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void lessOrEqualRelationalExprTest() throws IOException {
        String program = "if (1 <= 2) {\n" +
                "return 1\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "if (1<=2) {\n" +
                "return 1;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void greaterRelationalExprTest() throws IOException {
        String program = "if (1 > 2) {\n" +
                "return 1\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "if (1>2) {\n" +
                "return 1;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void lessRelationalExprTest() throws IOException {
        String program = "if (1 < 2) {\n" +
                "return 1\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "if (1<2) {\n" +
                "return 1;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void notequalEqualityExprTest() throws IOException {
        String program = "if (1 != 2) {\n" +
                "return 1\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "if (1!=2) {\n" +
                "return 1;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void equalEqualityExprTest() throws IOException {
        String program = "if (1 = 2) {\n" +
                "return 1\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "if (1==2) {\n" +
                "return 1;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void trueFalseLogicalOrExprTest() throws IOException {
        String program = "if (true OR false) {\n" +
                "return 1\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "if (1||0) {\n" +
                "return 1;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void falseLogicalOrExprTest() throws IOException {
        String program = "if (false OR false) {\n" +
                "return 1\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "if (0||0) {\n" +
                "return 1;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void trueLogicalOrExprTest() throws IOException {
        String program = "if (true OR true) {\n" +
                "return 1\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "if (1||1) {\n" +
                "return 1;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void truefalseLogicalAndExprTest() throws IOException {
        String program = "if (true AND false) {\n" +
                "return 1\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "if (1&&0) {\n" +
                "return 1;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void falseLogicalAndExprTest() throws IOException {
        String program = "if (false AND false) {\n" +
                "return 1\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "if (0&&0) {\n" +
                "return 1;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void trueLogicalAndExprTest() throws IOException {
        String program = "if (true AND true) {\n" +
                "return 1\n" +
                "}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "if (1&&1) {\n" +
                "return 1;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void functionAssign_stmtTest() throws IOException {
        String program = "int a\n" +
                "func int b(int a) {\n" +
                "return 1\n" +
                "}\n" +
                "a := b(1)";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int a;\n" +
                "int b(int a) {\n" +
                "return 1;\n" +
                "}\n" +
                "a = b(1);\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void BooleanAssign_stmtTest() throws IOException {
        String program = "boolean d\n" +
                "d := true";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int d;\n" +
                "d = 1;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void integerAssign_stmtTest() throws IOException {
        String program = "int a\n" +
                "a := 100";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\nint a;\n" +
                "a = 100;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void doubleAssign_stmtTest() throws IOException {
        String program = "double b\n" +
                "b := 52.04";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "double b;\n" +
                "b = 52.04;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void stringAssign_stmtTest() throws IOException {
        String program = "string c\n" +
                "c := \"Is anybody there?\"";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "char c[256];\n" +
                "strcpy(c, \"Is anybody there?\");\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void falseBooleanLiteralTest() throws IOException {
        String program = "boolean a\n" +
                "a := false";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int a;\n" +
                "a = 0;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void trueBooleanLiteralTest() throws IOException {
        String program = "boolean a\n" +
                "a := true";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int a;\n" +
                "a = 1;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void integerLiteralTest() throws IOException {
        String program = "int i\n" +
                "i := 1001";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int i;\n" +
                "i = 1001;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void doubleLiteralTest() throws IOException {
        String program = "double d\n" +
                "d := 23.12";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\ndouble d;\n" +
                "d = 23.12;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void stringLiteralTest() throws IOException {
        String program = "string s\n" +
                "s := \"Hello World!\"";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "char s[256];\n" +
                "strcpy(s, \"Hello World!\");\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void idNodeTest() throws IOException {
        String program = "int a\n" +
                "a := 1";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int a;\n" +
                "a = 1;\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void startNodeTest() throws IOException {
        String program = "";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void sampleProgramTest() throws IOException {
        String program = "int testOne\n" +
                "int testTwo\n" +
                "int testThree\n" +
                "int testFourOne\n" +
                "int testFourTwo\n" +
                "int testFour\n" +
                "int medium\n" +
                "double doubleTest\n" +
                "boolean boolTest1\n" +
                "boolean boolTest2\n" +
                "string stringTest\n" +
                " \n" +
                "testOne := 1\n" +
                "testTwo := 20\n" +
                "testThree := 30\n" +
                "testFourOne := 10\n" +
                "testFourTwo := 10\n" +
                "testFour := testFourOne + testFourTwo\n" +
                "doubleTest := 23.23\n" +
                "boolTest1 := true\n" +
                "boolTest2 := false\n" +
                "stringTest := \"Hello world!\"\n" +
                "\n" +
                "func int findMedium(int testOne, int testTwo, int testThree, int testFour) {\n" +
                "        int ret\n" +
                "        ret := 1\n" +
                "        return (testOne + testTwo + testThree + testFour) / 4\n" +
                "    }\n" +
                "\n" +
                "func int asd() {\n" +
                "    while (testOne < 10 OR testOne < 10) {\n" +
                "        testOne := testOne +1\n" +
                "    }\n" +
                "    if (testOne >= testTwo AND testOne != testFour) {\n" +
                "        testTwo := testTwo + testTwo\n" +
                "    }\n" +
                "    else {\n" +
                "        testTwo := testTwo * testTwo + testTwo #mindgames\n" +
                "    }\n" +
                "    medium := findMedium(testOne, testTwo, testThree, testFour)\n" +
                "    return medium\n" +
                "}\n";

        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int main (void) {\n" +
                "int testOne;\n" +
                "int testTwo;\n" +
                "int testThree;\n" +
                "int testFourOne;\n" +
                "int testFourTwo;\n" +
                "int testFour;\n" +
                "int medium;\n" +
                "double doubleTest;\n" +
                "int boolTest1;\n" +
                "int boolTest2;\n" +
                "char stringTest[256];\n" +
                "testOne = 1;\n" +
                "testTwo = 20;\n" +
                "testThree = 30;\n" +
                "testFourOne = 10;\n" +
                "testFourTwo = 10;\n" +
                "testFour = testFourOne+testFourTwo;\n" +
                "doubleTest = 23.23;\n" +
                "boolTest1 = 1;\n" +
                "boolTest2 = 0;\n" +
                "strcpy(stringTest, \"Hello world!\");\n" +
                "int findMedium(int testOne, int testTwo, int testThree, int testFour) {\n" +
                "int ret;\n" +
                "ret = 1;\n" +
                "return (testOne+testTwo+testThree+testFour)/4;\n" +
                "}\n" +
                "int asd() {\n" +
                "while (testOne<10||testOne<10) {\n" +
                "testOne = testOne+1;\n" +
                "}\n" +
                "if (testOne>=testTwo&&testOne!=testFour) {\n" +
                "testTwo = testTwo+testTwo;\n" +
                "}\n" +
                "else {\n" +
                "testTwo = testTwo*testTwo+testTwo;\n" +
                "}\n" +
                "medium = findMedium(testOne, testTwo, testThree, testFour);\n" +
                "return medium;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    // Takes a program as a String and returns the generated C code
    private String getCCode(String input) throws IOException {
        // ANTLR
        CharStream cs = CharStreams.fromString(input);
        EzuinoLexer lLexer = new EzuinoLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lLexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        ParseTree parseTree = parser.start();

        // Custom AST
        BuildAstVisitor buildAstVisitor = new BuildAstVisitor();
        ErrorHandler errorhandler = new ErrorHandler();
        AstNode astNode = parseTree.accept(buildAstVisitor);
        SymbolTableVisitor symbolTableFillingVisitor = new SymbolTableVisitor(true, errorhandler);
        astNode.accept(symbolTableFillingVisitor);
        Typechecker tc = new Typechecker(errorhandler);
        astNode.accept(tc);

        // Custom Code generation
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        CCodeGenerationVisitor cCodeGenerationVisitor = new CCodeGenerationVisitor(ps);
        astNode.accept(cCodeGenerationVisitor);

        // Return ByteArrayOutputStream String
        return os.toString("UTF8");
    }
}
