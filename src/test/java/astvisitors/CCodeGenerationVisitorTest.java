package astvisitors;

import ast.AstNode;
import cstvisitors.BuildAstVisitor;
import exceptions.ErrorHandler;
import exceptions.ErrorListener;
import generated.EzuinoLexer;
import generated.EzuinoParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CCodeGenerationVisitorTest {
    // Each generated code ends with a blank line, therefor every expected string ends with a \n
    @Test
    public void printNodeTest() {
        String program = "func Setup() {Print (\"Hello world!\")}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nprintf(%s, \"Hello world!\");\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void funcCallBlockTest() throws IOException {
        String program = "func boolean b() {\n" +
                "return true\n" +
                "}\n" +
                "func Setup() {if (true) {\n" +
                "boolean a\n" +
                "a := b()\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int b() {\n" +
                "return 1;\n" +
                "}\n" +
                "void setup() {\nif (1) {\n" +
                "int a;\n" +
                "a = b();\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void ifStmtBlockTest() throws IOException {
        String program = "func Setup() {if (true) {\n" +
                "if (true) {\n" +
                "}\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1) {\n" +
                "if (1) {\n" +
                "}\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void whileStmtBlockTest() throws IOException {
        String program = "func Setup() {\nif (true) {\n" +
                "while (true) {\n" +
                "}\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1) {\n" +
                "while (1) {\n" +
                "}\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void booleanBlockTest() throws IOException {
        String program = "func Setup() {if (true) {\n" +
                "boolean b\n" +
                "b := true\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1) {\n" +
                "int b;\n" +
                "b = 1;\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void stringBlockTest() throws IOException {
        String program = "func Setup() {if (true) {\n" +
                "string s\n" +
                "s := \"hello world!\"\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1) {\n" +
                "char s[256];\n" +
                "strcpy(s, \"hello world!\");\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void doubleBlockTest() throws IOException {
        String program = "func Setup() {if (true) {\n" +
                "double d\n" +
                "d := 23.14\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1) {\n" +
                "double d;\n" +
                "d = 23.14;\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void intBlockTest() throws IOException {
        String program = "func Setup() {if (true) {\n" +
                "int i\n" +
                "i := 0\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1) {\n" +
                "int i;\n" +
                "i = 0;\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void sampleIfStmtTest() throws IOException {
        String program = "func boolean b() {\n" +
                "return false\n" +
                "}\n" +
                "func Setup() {if (!(1 > 2) AND (true) AND (!b())) {\n" +
                "} else {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int b() {\n" +
                "return 0;\n" +
                "}\n" +
                "void setup() {\nif (!(1>2)&&(1)&&(!b())) {\n" +
                "}\n" +
                "else {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void notFuncCallIfStmtTest() throws IOException {
        String program = "func boolean b() {\n" +
                "return true\n" +
                "}\n" +
                "func Setup() {if (!b()) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int b() {\n" +
                "return 1;\n" +
                "}\n" +
                "void setup() {\nif (!b()) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void funcCallIfStmtTest() throws IOException {
        String program = "func boolean b() {\n" +
                "return true\n" +
                "}\n" +
                "func Setup() {if (b()) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int b() {\n" +
                "return 1;\n" +
                "}\n" +
                "void setup() {\nif (b()) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void notFalseParenthesisIfStmtTest() throws IOException {
        String program = "func Setup() {if ((((!false)))) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif ((((!0)))) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void notTrueParenthesisIfStmtTest() throws IOException {
        String program = "func Setup() {if ((((!true)))) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif ((((!1)))) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void falseParenthesisIfStmtTest() throws IOException {
        String program = "func Setup() {if ((((false)))) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif ((((0)))) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void trueParenthesisIfStmtTest() throws IOException {
        String program = "func Setup() {if ((((true)))) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif ((((1)))) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void notEqualIfStmtTest() throws IOException {
        String program = "func Setup() {if (1 != 2) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1!=2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void equalIfStmtTest() throws IOException {
        String program = "func Setup() {if (1 = 2) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1==2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void greaterOrEqualIfStmtTest() throws IOException {
        String program = "func Setup() {if (1 >= 2) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1>=2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void lessOrEqualIfStmtTest() throws IOException {
        String program = "func Setup() {if (1 <= 2) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1<=2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void greaterIfStmtTest() throws IOException {
        String program = "func Setup() {if (1 > 2) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1>2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void lessIfStmtTest() throws IOException {
        String program = "func Setup() {if (1 < 2) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1<2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void falseIfStmtTest() throws IOException {
        String program = "func Setup() {if (false) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (0) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void trueIfStmtTest() throws IOException {
        String program = "func Setup() {if (true) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void withElseIfStmtTest() throws IOException {
        String program = "func Setup() {if(true) {\n" +
                "} else {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1) {\n" +
                "}\n" +
                "else {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void sampleWhileStmtTest() throws IOException {
        String program = "func boolean b() {\n" +
                "return false\n" +
                "}\n" +
                "func Setup() {while (!(1 > 2) AND (true) AND (!b())) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int b() {\n" +
                "return 0;\n" +
                "}\n" +
                "void setup() {\nwhile (!(1>2)&&(1)&&(!b())) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void notFuncCallWhileStmtTest() throws IOException {
        String program = "func boolean b() {\n" +
                "return true\n" +
                "}\n" +
                "func Setup() {while (!b()) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int b() {\n" +
                "return 1;\n" +
                "}\n" +
                "void setup() {\nwhile (!b()) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void funcCallWhileStmtTest() throws IOException {
        String program = "func boolean b() {\n" +
                "return true\n" +
                "}\n" +
                "func Setup() {while (b()) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int b() {\n" +
                "return 1;\n" +
                "}\n" +
                "void setup() {\nwhile (b()) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void notFalseParenthesisWhileStmtTest() throws IOException {
        String program = "func Setup() {while ((((!false)))) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nwhile ((((!0)))) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void notTrueParenthesisWhileStmtTest() throws IOException {
        String program = "func Setup() {while ((((!true)))) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nwhile ((((!1)))) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void falseParenthesisWhileStmtTest() throws IOException {
        String program = "func Setup() {while ((((false)))) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nwhile ((((0)))) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void trueParenthesisWhileStmtTest() throws IOException {
        String program = "func Setup() {while ((((true)))) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nwhile ((((1)))) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void notEqualWhileStmtTest() throws IOException {
        String program = "func Setup() {while (1 != 2) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nwhile (1!=2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void equalWhileStmtTest() throws IOException {
        String program = "func Setup() {while (1 = 2) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nwhile (1==2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void greaterOrEqualWhileStmtTest() throws IOException {
        String program = "func Setup() {while (1 >= 2) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nwhile (1>=2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void lessOrEqualWhileStmtTest() throws IOException {
        String program = "func Setup() {while (1 <= 2) {}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nwhile (1<=2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void greaterWhileStmtTest() throws IOException {
        String program = "func Setup() {while (1 > 2) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nwhile (1>2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void lessWhileStmtTest() throws IOException {
        String program = "func Setup() {while (1 < 2) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nwhile (1<2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void falseWhileStmtTest() throws IOException {
        String program = "func Setup() {while (false) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nwhile (0) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void trueWhileStmtTest() throws IOException {
        String program = "func Setup() {while (true) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nwhile (1) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void dclsNodeTest() throws IOException {
        String program = "int i\n" +
                "double d\n" +
                "string s\n" +
                "boolean b func Setup() {}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int i;\n" +
                "double d;\n" +
                "char s[256];\n" +
                "int b;\nvoid setup() {\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void booleanDclTest() throws IOException {
        String program = "boolean b func Setup() {}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int b;\nvoid setup() {\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void stringDclTest() throws IOException {
        String program = "string s func Setup() {}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "char s[256];\nvoid setup() {\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void doubleDclTest() throws IOException {
        String program = "double d func Setup() {}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "double d;\nvoid setup() {\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void intDclTest() throws IOException {
        String program = "int i func Setup() {}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int i;\nvoid setup() {\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void stringFuncDefTest() throws IOException {
        String program = "string s\n" +
                "func string b(string s) {\n" +
                "return s\n" +
                "}\n" +
                "func Setup() {s := b(\"Hello world!\")}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "char s[256];\n" +
                "char b(char s[256]) {\n" +
                "return s;\n" +
                "}\n" +
                "void setup() {\nstrcpy(s, b(\"Hello world!\"));\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void doubleFuncDefTest() throws IOException {
        String program = "double a\n" +
                "func double b(double a) {\n" +
                "return a\n" +
                "}\n" +
                "func Setup() {a := b(1.23)}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "double a;\n" +
                "double b(double a) {\n" +
                "return a;\n" +
                "}\n" +
                "void setup() {\na = b(1.23);\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void intFuncDefTest() throws IOException {
        String program = "int a\n" +
                "func int b(int a) {\n" +
                "return a\n" +
                "}\n" +
                "func Setup() {a := b(1)}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int a;\n" +
                "int b(int a) {\n" +
                "return a;\n" +
                "}\n" +
                "void setup() {\na = b(1);\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void voidFuncDefTest() throws IOException {
        String program = "func b() {\n" +
                "return\n" +
                "} func Setup(){}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void b() {\n" +
                "return;\n" +
                "}\nvoid setup() {\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void multipleParenthesisExprTest() throws IOException {
        String program = "int a\n" +
                "func Setup(){a := (100 + (10 + ((1 + 1000))))}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int a;\n" +
                "void setup() {\na = (100+(10+((1+1000))));\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void stringParenthesisExprTest() throws IOException {
        String program = "string s\n" +
                "func Setup() {s := (\"Hello world!\")}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "char s[256];\n" +
                "void setup() {\nstrcpy(s, (\"Hello world!\"));\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void doubleParenthesisExprTest() throws IOException {
        String program = "double a\n" +
                "func Setup() {a := (34.115)}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "double a;\n" +
                "void setup() {\na = (34.115);\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void integerParenthesisExprTest() throws IOException {
        String program = "int a\n" +
                "func Setup() {a := (100)}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int a;\n" +
                "void setup() {\na = (100);\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void doubleUnaryExprTest() throws IOException {
        String program = "double a\n" +
                "func Setup() {a := -23.12}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "double a;\n" +
                "void setup() {\na = -23.12;\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void integerUnaryExprTest() throws IOException {
        String program = "int a\n" +
                "func Setup() {a := -100}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int a;\n" +
                "void setup() {\na = -100;\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void trueFalseUnaryExprTest() throws IOException {
        String program = "func Setup() {if (!true AND !false) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (!1&&!0) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void longNotFalseUnaryExprTest() throws IOException {
        String program = "func Setup() {if (!(false)) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (!(0)) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void shortNotFalseUnaryExprTest() throws IOException {
        String program = "func Setup() {if (!false) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (!0) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void longNotTrueUnaryExprTest() throws IOException {
        String program = "func Setup() {if (!(true)) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (!(1)) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void shortNotTrueUnaryExprTest() throws IOException {
        String program = "func Setup() {if (!true) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (!1) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void divideMultiplicativeExprTest() throws IOException {
        String program = "int a\n" +
                "func Setup() {a := 1 / 2}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int a;\n" +
                "void setup() {\na = 1/2;\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void multiplyMultiplicativeExprTest() throws IOException {
        String program = "int a\n" +
                "func Setup() {a := 1 * 2}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int a;\n" +
                "void setup() {\na = 1*2;\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void minusAdditiveExprTest() throws IOException {
        String program = "int a\n" +
                "func Setup() {a := 1 - 2}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int a;\n" +
                "void setup() {\na = 1-2;\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void plusAdditiveExprTest() throws IOException {
        String program = "int a\n" +
                "func Setup() {a := 1 + 2}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int a;\n" +
                "void setup() {\na = 1+2;\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void greaterOrEqualRelationalExprTest() throws IOException {
        String program = "func Setup() {if (1 >= 2) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1>=2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void lessOrEqualRelationalExprTest() throws IOException {
        String program = "func Setup() {if (1 <= 2) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1<=2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void greaterRelationalExprTest() throws IOException {
        String program = "func Setup() {if (1 > 2) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1>2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void lessRelationalExprTest() throws IOException {
        String program = "func Setup() {if (1 < 2) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1<2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void notequalEqualityExprTest() throws IOException {
        String program = "func Setup() {if (1 != 2) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1!=2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void equalEqualityExprTest() throws IOException {
        String program = "func Setup() {if (1 = 2) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1==2) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void trueFalseLogicalOrExprTest() throws IOException {
        String program = "func Setup() {if (true OR false) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1||0) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void falseLogicalOrExprTest() throws IOException {
        String program = "func Setup() {if (false OR false) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (0||0) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void trueLogicalOrExprTest() throws IOException {
        String program = "func Setup() {if (true OR true) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1||1) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void truefalseLogicalAndExprTest() throws IOException {
        String program = "func Setup() {if (true AND false) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1&&0) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void falseLogicalAndExprTest() throws IOException {
        String program = "func Setup() {if (false AND false) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (0&&0) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void trueLogicalAndExprTest() throws IOException {
        String program = "func Setup() {if (true AND true) {\n" +
                "}}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "void setup() {\nif (1&&1) {\n" +
                "}\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void functionAssignStmtTest() throws IOException {
        String program = "int a\n" +
                "func int b(int a) {\n" +
                "return 1\n" +
                "}\n" +
                "func Setup() {a := b(1)}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int a;\n" +
                "int b(int a) {\n" +
                "return 1;\n" +
                "}\n" +
                "void setup() {\na = b(1);\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void booleanAssignStmtTest() throws IOException {
        String program = "boolean d\n" +
                "func Setup() {d := true}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int d;\n" +
                "void setup() {\nd = 1;\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void integerAssignStmtTest() throws IOException {
        String program = "int a\n" +
                "func Setup() {a := 100}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int a;\n" +
                "void setup() {\na = 100;\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void doubleAssignStmtTest() throws IOException {
        String program = "double b\n" +
                "func Setup() {b := 52.04}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "double b;\n" +
                "void setup() {\nb = 52.04;\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void stringAssignStmtTest() throws IOException {
        String program = "string c\n" +
                "func Setup() {c := \"Is anybody there?\"}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "char c[256];\n" +
                "void setup() {\nstrcpy(c, \"Is anybody there?\");\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void falseBooleanLiteralTest() throws IOException {
        String program = "boolean a\n" +
                "func Setup() {a := false}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int a;\n" +
                "void setup() {\na = 0;\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void trueBooleanLiteralTest() throws IOException {
        String program = "boolean a\n" +
                "func Setup() {a := true}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int a;\n" +
                "void setup() {\na = 1;\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void integerLiteralTest() throws IOException {
        String program = "int i\n" +
                "func Setup() {i := 1001}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int i;\n" +
                "void setup() {\ni = 1001;\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void doubleLiteralTest() throws IOException {
        String program = "double d\n" +
                "func Setup() {d := 23.12}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "double d;\n" +
                "void setup() {\nd = 23.12;\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void stringLiteralTest() throws IOException {
        String program = "string s\n" +
                "func Setup() {s := \"Hello World!\"}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "char s[256];\n" +
                "void setup() {\nstrcpy(s, \"Hello World!\");\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void idNodeTest() throws IOException {
        String program = "int a\n" +
                "func Setup() {a := 1}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "int a;\n" +
                "void setup() {\na = 1;\n}\n";
        assertEquals(expected, getCCode(program));
    }

    @Test
    public void startNodeTest() throws IOException {
        String program = "func Setup() {}";
        String expected = "#include <stdio.h>\n" +
                "#include <string.h>\nvoid setup() {\n}\n";
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
                "func Setup() {" +
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
                "}\n" +
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
                "void setup() {\n" +
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
                "}\n" +
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
                "}\n";
        assertEquals(expected, getCCode(program));
    }

    // Takes a program as a String and returns the generated C code
    private String getCCode(String input) {
        ErrorHandler errorHandler = new ErrorHandler();

        // ANTLR
        CharStream charStream = CharStreams.fromString(input);
        ErrorListener errorListener = new ErrorListener(errorHandler);
        EzuinoLexer lLexer = new EzuinoLexer(charStream);
        lLexer.removeErrorListeners();
        lLexer.addErrorListener(errorListener);
        CommonTokenStream tokens = new CommonTokenStream(lLexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        ParseTree parseTree = parser.start();

        // Custom AST
        BuildAstVisitor buildAstVisitor = new BuildAstVisitor();
        AstNode ast = parseTree.accept(buildAstVisitor);
        ast.accept(new ReservedKeywordsVisitor(errorHandler));
        ast.accept(new SymbolTableVisitor(false, errorHandler));
        ast.accept(new TypeChecker(errorHandler));
        ast.accept(new ReturnStmtTypeCheckVisitor(errorHandler));
        ast.accept(new MissingReturnStmtVisitor(errorHandler));
        ast.accept(new FuncStructureVisitor(errorHandler));

        // Custom Code generation
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        CCodeGenerationVisitor cCodeGenerationVisitor = new CCodeGenerationVisitor(ps);
        ast.accept(cCodeGenerationVisitor);
        assertFalse(errorHandler.hasErrors());
        if(errorHandler.hasErrors()) {
            errorHandler.printErrors("Test reason");
        }

        String s = null;
        try {
            s = os.toString("UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }
}
