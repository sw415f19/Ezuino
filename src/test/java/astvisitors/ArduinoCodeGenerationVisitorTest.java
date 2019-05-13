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

import static org.junit.Assert.*;

public class ArduinoCodeGenerationVisitorTest {
    // Each generated code ends with a blank line, therefor every expected string ends with a \n
    @Test
    public void analogReadTest() throws IOException {
        String program = "int a\n" +
                "a := AnalogRead(14)";
        String expected = "int a;\n" +
                "a = analogRead(14);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void analogReadIdNodeTest() throws IOException {
        String program = "int a\n" +
                "int b\n" +
                "b := 14\n" +
                "a := AnalogRead(b)";
        String expected = "int a;\n" +
                "int b;\n" +
                "b = 14;\n" +
                "a = analogRead(b);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void analogWriteTest() throws IOException {
        String program = "AnalogWrite(13, 15)";
        String expected = "analogWrite(13, 15);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void analogWriteFirstIdNodeTest() throws IOException {
        String program = "int a\n" +
                "a := 13\n" +
                "AnalogWrite(a, 15)";
        String expected = "int a;\n" +
                "a = 13;\n" +
                "analogWrite(a, 15);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void analogWriteSecondIdNodeTest() throws IOException {
        String program = "int a\n" +
                "a := 15\n" +
                "AnalogWrite(13, a)";
        String expected = "int a;\n" +
                "a = 15;\n" +
                "analogWrite(13, a);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void delayMicroTest() throws IOException {
        String program = "DelayMicro(1000)";
        String expected = "delayMicroseconds(1000);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void delayMicroIdNodeTest() throws IOException {
        String program = "int a\n" +
                "a := 1000\n" +
                "DelayMicro(a)";
        String expected = "int a;\n" +
                "a = 1000;\n" +
                "delayMicroseconds(a);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void delayTest() throws IOException {
        String program = "Delay(1000)";
        String expected = "delay(1000);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void delayIdNodeTest() throws IOException {
        String program = "int a\n" +
                "a := 1000\n" +
                "Delay(a)";
        String expected = "int a;\n" +
                "a = 1000;\n" +
                "delay(a);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void pinModeInputPullUpTest() throws IOException {
        String program = "PinMode(13, INPUT_PULLUP)";
        String expected = "pinMode(13, INPUT_PULLUP);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void pinModeOutputTest() throws IOException {
        String program = "PinMode(13, OUTPUT)";
        String expected = "pinMode(13, OUTPUT);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void pinModeInputTest() throws IOException {
        String program = "PinMode(13, INPUT)";
        String expected = "pinMode(13, INPUT);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void pinModeInputFirstIdNodeTest() throws IOException {
        String program = "int a\n" +
                "a := 13\n" +
                "PinMode(a, INPUT)";
        String expected = "int a;\n" +
                "a = 13;\n" +
                "pinMode(a, INPUT);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void pinModeInputSecondIdNodeTest() throws IOException {
        String program = "int a\n" +
                "a := 2\n" +
                "PinMode(13, a)";
        String expected = "int a;\n" +
                "a = 2;\n" +
                "pinMode(13, a);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void digitalWriteLowTest() throws IOException {
        String program = "DigitalWrite(13, LOW)";
        String expected = "digitalWrite(13, LOW);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void digitalWriteHighTest() throws IOException {
        String program = "DigitalWrite(13, HIGH)";
        String expected = "digitalWrite(13, HIGH);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void digitalWriteSecondIdNodeTest() throws IOException {
        String program = "int a\n" +
                "a := 1" +
                "DigitalWrite(13, a)";
        String expected = "int a;\n" +
                "a = 1;\n" +
                "digitalWrite(13, a);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void digitalWriteFirstIdNodeTest() throws IOException {
        String program = "int a\n" +
                "a := 13\n" +
                "DigitalWrite(a, HIGH)";
        String expected = "int a;\n" +
                "a = 13;\n" +
                "digitalWrite(a, HIGH);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void digitalReadTest() throws IOException {
        String program = "int a\n" +
                "a := DigitalRead(13)";
        String expected = "int a;\n" +
                "a = digitalRead(13);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void digitalReadIdNodeTest() throws IOException {
        String program = "int a\n" +
                "int b\n" +
                "b := 13\n" +
                "a := DigitalRead(b)";
        String expected = "int a;\n" +
                "int b;\n" +
                "b = 13;\n" +
                "a = digitalRead(b);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void serialBeginTest() throws IOException {
        String program = "SerialBegin(9600)";
        String expected = "Serial.begin(9600);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void serialBeginIdNodeTest() throws IOException {
        String program = "int a\n" +
                "a := 19200\n" +
                "SerialBegin(a)";
        String expected = "int a;\n" +
                "a = 19200;\n" +
                "Serial.begin(a);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void serialEndTest() throws IOException {
        String program = "SerialEnd()";
        String expected = "Serial.end();\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void printNodeTest() throws IOException {
        String program = "Print (\"Hello world!\")";
        String expected = "Serial.print(\"Hello world!\");\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void funcCallBlockTest() throws IOException {
        String program = "func boolean b() {\n" +
                "return true\n" +
                "}\n" +
                "if (true) {\n" +
                "boolean a\n" +
                "a := b()\n" +
                "}";
        String expected = "bool b() {\n" +
                "return true;\n" +
                "}\n" +
                "if (true) {\n" +
                "bool a;\n" +
                "a = b();\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void funcDefBlockTest() throws IOException {
        String program = "if (true) {\n" +
                "func boolean b() {\n" +
                "return true\n" +
                "}\n" +
                "}";
        String expected = "if (true) {\n" +
                "bool b() {\n" +
                "return true;\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void ifStmtBlockTest() throws IOException {
        String program = "if (true) {\n" +
                "if (true) {\n" +
                "}\n" +
                "}";
        String expected = "if (true) {\n" +
                "if (true) {\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void whileStmtBlockTest() throws IOException {
        String program = "if (true) {\n" +
                "while (true) {\n" +
                "}\n" +
                "}";
        String expected = "if (true) {\n" +
                "while (true) {\n" +
                "}\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void booleanBlockTest() throws IOException {
        String program = "if (true) {\n" +
                "boolean b\n" +
                "b := true\n" +
                "}";
        String expected = "if (true) {\n" +
                "bool b;\n" +
                "b = true;\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void stringBlockTest() throws IOException {
        String program = "if (true) {\n" +
                "string s\n" +
                "s := \"hello world!\"\n" +
                "}";
        String expected = "if (true) {\n" +
                "char s[42];\n" +
                "s = \"hello world!\";\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void doubleBlockTest() throws IOException {
        String program = "if (true) {\n" +
                "double d\n" +
                "d := 23.14\n" +
                "}";
        String expected = "if (true) {\n" +
                "double d;\n" +
                "d = 23.14;\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void intBlockTest() throws IOException {
        String program = "if (true) {\n" +
                "int i\n" +
                "i := 0\n" +
                "}";
        String expected = "if (true) {\n" +
                "int i;\n" +
                "i = 0;\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void sampleIfStmtTest() throws IOException {
        String program = "func boolean b() {\n" +
                "return false\n" +
                "}\n" +
                "if (!(1 > 2) AND (true) AND (!b())) {\n" +
                "} else {\n" +
                "}";
        String expected = "bool b() {\n" +
                "return false;\n" +
                "}\n" +
                "if (!(1>2)&&(true)&&(!b())) {\n" +
                "}\n" +
                "else {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void notFuncCallIfStmtTest() throws IOException {
        String program = "func boolean b() {\n" +
                "return true\n" +
                "}\n" +
                "if (!b()) {\n" +
                "}";
        String expected = "bool b() {\n" +
                "return true;\n" +
                "}\n" +
                "if (!b()) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void funcCallIfStmtTest() throws IOException {
        String program = "func boolean b() {\n" +
                "return true\n" +
                "}\n" +
                "if (b()) {\n" +
                "}";
        String expected = "bool b() {\n" +
                "return true;\n" +
                "}\n" +
                "if (b()) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void notFalseParenthesisIfStmtTest() throws IOException {
        String program = "if ((((!false)))) {\n" +
                "}";
        String expected = "if ((((!false)))) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void notTrueParenthesisIfStmtTest() throws IOException {
        String program = "if ((((!true)))) {\n" +
                "}";
        String expected = "if ((((!true)))) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void falseParenthesisIfStmtTest() throws IOException {
        String program = "if ((((false)))) {\n" +
                "}";
        String expected = "if ((((false)))) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void trueParenthesisIfStmtTest() throws IOException {
        String program = "if ((((true)))) {\n" +
                "}";
        String expected = "if ((((true)))) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void notEqualIfStmtTest() throws IOException {
        String program = "if (1 != 2) {\n" +
                "}";
        String expected = "if (1!=2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void equalIfStmtTest() throws IOException {
        String program = "if (1 = 2) {\n" +
                "}";
        String expected = "if (1==2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void greaterOrEqualIfStmtTest() throws IOException {
        String program = "if (1 >= 2) {\n" +
                "}";
        String expected = "if (1>=2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void lessOrEqualIfStmtTest() throws IOException {
        String program = "if (1 <= 2) {\n" +
                "}";
        String expected = "if (1<=2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void greaterIfStmtTest() throws IOException {
        String program = "if (1 > 2) {\n" +
                "}";
        String expected = "if (1>2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void lessIfStmtTest() throws IOException {
        String program = "if (1 < 2) {\n" +
                "}";
        String expected = "if (1<2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void falseIfStmtTest() throws IOException {
        String program = "if (false) {\n" +
                "}";
        String expected = "if (false) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void trueIfStmtTest() throws IOException {
        String program = "if (true) {\n" +
                "}";
        String expected = "if (true) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void withElseIfStmtTest() throws IOException {
        String program = "if(true) {\n" +
                "} else {\n" +
                "}";
        String expected = "if (true) {\n" +
                "}\n" +
                "else {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void sampleWhileStmtTest() throws IOException {
        String program = "func boolean b() {\n" +
                "return false\n" +
                "}\n" +
                "while (!(1 > 2) AND (true) AND (!b())) {\n" +
                "}";
        String expected = "bool b() {\n" +
                "return false;\n" +
                "}\n" +
                "while (!(1>2)&&(true)&&(!b())) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void notFuncCallWhileStmtTest() throws IOException {
        String program = "func boolean b() {\n" +
                "return true\n" +
                "}\n" +
                "while (!b()) {\n" +
                "}";
        String expected = "bool b() {\n" +
                "return true;\n" +
                "}\n" +
                "while (!b()) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void funcCallWhileStmtTest() throws IOException {
        String program = "func boolean b() {\n" +
                "return true\n" +
                "}\n" +
                "while (b()) {\n" +
                "}";
        String expected = "bool b() {\n" +
                "return true;\n" +
                "}\n" +
                "while (b()) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void notFalseParenthesisWhileStmtTest() throws IOException {
        String program = "while ((((!false)))) {\n" +
                "}";
        String expected = "while ((((!false)))) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void notTrueParenthesisWhileStmtTest() throws IOException {
        String program = "while ((((!true)))) {\n" +
                "}";
        String expected = "while ((((!true)))) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void falseParenthesisWhileStmtTest() throws IOException {
        String program = "while ((((false)))) {\n" +
                "}";
        String expected = "while ((((false)))) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void trueParenthesisWhileStmtTest() throws IOException {
        String program = "while ((((true)))) {\n" +
                "}";
        String expected = "while ((((true)))) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void notEqualWhileStmtTest() throws IOException {
        String program = "while (1 != 2) {\n" +
                "}";
        String expected = "while (1!=2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void equalWhileStmtTest() throws IOException {
        String program = "while (1 = 2) {\n" +
                "}";
        String expected = "while (1==2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void greaterOrEqualWhileStmtTest() throws IOException {
        String program = "while (1 >= 2) {\n" +
                "}";
        String expected = "while (1>=2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void lessOrEqualWhileStmtTest() throws IOException {
        String program = "while (1 <= 2) {\n" +
                "}";
        String expected = "while (1<=2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void greaterWhileStmtTest() throws IOException {
        String program = "while (1 > 2) {\n" +
                "}";
        String expected = "while (1>2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void lessWhileStmtTest() throws IOException {
        String program = "while (1 < 2) {\n" +
                "}";
        String expected = "while (1<2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void falseWhileStmtTest() throws IOException {
        String program = "while (false) {\n" +
                "}";
        String expected = "while (false) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void trueWhileStmtTest() throws IOException {
        String program = "while (true) {\n" +
                "}";
        String expected = "while (true) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void dclsNodeTest() throws IOException {
        String program = "int i\n" +
                "double d\n" +
                "string s\n" +
                "boolean b";
        String expected = "int i;\n" +
                "double d;\n" +
                "char s[42];\n" +
                "bool b;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void booleanDclTest() throws IOException {
        String program = "boolean b";
        String expected = "bool b;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void stringDclTest() throws IOException {
        String program = "string s";
        String expected = "char s[42];\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void doubleDclTest() throws IOException {
        String program = "double d";
        String expected = "double d;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void intDclTest() throws IOException {
        String program = "int i";
        String expected = "int i;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void stringFuncDefTest() throws IOException {
        String program = "string s\n" +
                "func string b(string s) {\n" +
                "return s\n" +
                "}\n" +
                "s := b(\"Hello world!\")";
        String expected = "char s[42];\n" +
                "char b(char s[42]) {\n" +
                "return s;\n" +
                "}\n" +
                "s = b(\"Hello world!\");\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void doubleFuncDefTest() throws IOException {
        String program = "double a\n" +
                "func double b(double a) {\n" +
                "return a\n" +
                "}\n" +
                "a := b(1.23)";
        String expected = "double a;\n" +
                "double b(double a) {\n" +
                "return a;\n" +
                "}\n" +
                "a = b(1.23);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void intFuncDefTest() throws IOException {
        String program = "int a\n" +
                "func int b(int a) {\n" +
                "return a\n" +
                "}\n" +
                "a := b(1)";
        String expected = "int a;\n" +
                "int b(int a) {\n" +
                "return a;\n" +
                "}\n" +
                "a = b(1);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void voidFuncDefTest() throws IOException {
        String program = "func b() {\n" +
                "return\n" +
                "}";
        String expected = "void b() {\n" +
                "return;\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void multipleParenthesisExprTest() throws IOException {
        String program = "int a\n" +
                "a := (100 + (10 + ((1 + 1000))))";
        String expected = "int a;\n" +
                "a = (100+(10+((1+1000))));\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void stringParenthesisExprTest() throws IOException {
        String program = "string s\n" +
                "s := (\"Hello world!\")";
        String expected = "char s[42];\n" +
                "s = (\"Hello world!\");\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void doubleParenthesisExprTest() throws IOException {
        String program = "double a\n" +
                "a := (34.115)";
        String expected = "double a;\n" +
                "a = (34.115);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void integerParenthesisExprTest() throws IOException {
        String program = "int a\n" +
                "a := (100)";
        String expected = "int a;\n" +
                "a = (100);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void doubleUnaryExprTest() throws IOException {
        String program = "double a\n" +
                "a := -23.12";
        String expected = "double a;\n" +
                "a = -23.12;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void integerUnaryExprTest() throws IOException {
        String program = "int a\n" +
                "a := -100";
        String expected = "int a;\n" +
                "a = -100;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void trueFalseUnaryExprTest() throws IOException {
        String program = "if (!true AND !false) {\n" +
                "}";
        String expected = "if (!true&&!false) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void longNotFalseUnaryExprTest() throws IOException {
        String program = "if (!(false)) {\n" +
                "}";
        String expected = "if (!(false)) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void shortNotFalseUnaryExprTest() throws IOException {
        String program = "if (!false) {\n" +
                "}";
        String expected = "if (!false) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void longNotTrueUnaryExprTest() throws IOException {
        String program = "if (!(true)) {\n" +
                "}";
        String expected = "if (!(true)) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void shortNotTrueUnaryExprTest() throws IOException {
        String program = "if (!true) {\n" +
                "}";
        String expected = "if (!true) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void divideMultiplicativeExprTest() throws IOException {
        String program = "int a\n" +
                "a := 1 / 2";
        String expected = "int a;\n" +
                "a = 1/2;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void multiplyMultiplicativeExprTest() throws IOException {
        String program = "int a\n" +
                "a := 1 * 2";
        String expected = "int a;\n" +
                "a = 1*2;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void minusAdditiveExprTest() throws IOException {
        String program = "int a\n" +
                "a := 1 - 2";
        String expected = "int a;\n" +
                "a = 1-2;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void plusAdditiveExprTest() throws IOException {
        String program = "int a\n" +
                "a := 1 + 2";
        String expected = "int a;\n" +
                "a = 1+2;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void greaterOrEqualRelationalExprTest() throws IOException {
        String program = "if (1 >= 2) {\n" +
                "}";
        String expected = "if (1>=2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void lessOrEqualRelationalExprTest() throws IOException {
        String program = "if (1 <= 2) {\n" +
                "}";
        String expected = "if (1<=2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void greaterRelationalExprTest() throws IOException {
        String program = "if (1 > 2) {\n" +
                "}";
        String expected = "if (1>2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void lessRelationalExprTest() throws IOException {
        String program = "if (1 < 2) {\n" +
                "}";
        String expected = "if (1<2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void notequalEqualityExprTest() throws IOException {
        String program = "if (1 != 2) {\n" +
                "}";
        String expected = "if (1!=2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void equalEqualityExprTest() throws IOException {
        String program = "if (1 = 2) {\n" +
                "}";
        String expected = "if (1==2) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void trueFalseLogicalOrExprTest() throws IOException {
        String program = "if (true OR false) {\n" +
                "}";
        String expected = "if (true||false) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void falseLogicalOrExprTest() throws IOException {
        String program = "if (false OR false) {\n" +
                "}";
        String expected = "if (false||false) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void trueLogicalOrExprTest() throws IOException {
        String program = "if (true OR true) {\n" +
                "}";
        String expected = "if (true||true) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void truefalseLogicalAndExprTest() throws IOException {
        String program = "if (true AND false) {\n" +
                "}";
        String expected = "if (true&&false) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void falseLogicalAndExprTest() throws IOException {
        String program = "if (false AND false) {\n" +
                "}";
        String expected = "if (false&&false) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void trueLogicalAndExprTest() throws IOException {
        String program = "if (true AND true) {\n" +
                "}";
        String expected = "if (true&&true) {\n" +
                "}\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void functionAssignStmtTest() throws IOException {
        String program = "int a\n" +
                "func int b(int a) {\n" +
                "return 1\n" +
                "}\n" +
                "a := b(1)";
        String expected = "int a;\n" +
                "int b(int a) {\n" +
                "return 1;\n" +
                "}\n" +
                "a = b(1);\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void booleanAssignStmtTest() throws IOException {
        String program = "boolean d\n" +
                "d := true";
        String expected = "bool d;\n" +
                "d = true;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void integerAssignStmtTest() throws IOException {
        String program = "int a\n" +
                "a := 100";
        String expected = "int a;\n" +
                "a = 100;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void doubleAssignStmtTest() throws IOException {
        String program = "double b\n" +
                "b := 52.04";
        String expected = "double b;\n" +
                "b = 52.04;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void stringAssignStmtTest() throws IOException {
        String program = "string c\n" +
                "c := \"Is anybody there?\"";
        String expected = "char c[42];\n" +
                "c = \"Is anybody there?\";\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void falseBooleanLiteralTest() throws IOException {
        String program = "boolean a\n" +
                "a := false";
        String expected = "bool a;\n" +
                "a = false;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void trueBooleanLiteralTest() throws IOException {
        String program = "boolean a\n" +
                "a := true";
        String expected = "bool a;\n" +
                "a = true;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void integerLiteralTest() throws IOException {
        String program = "int i\n" +
                "i := 1001";
        String expected = "int i;\n" +
                "i = 1001;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void doubleLiteralTest() throws IOException {
        String program = "double d\n" +
                "d := 23.12";
        String expected = "double d;\n" +
                "d = 23.12;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void stringLiteralTest() throws IOException {
        String program = "string s\n" +
                "s := \"Hello World!\"";
        String expected = "char s[42];\n" +
                "s = \"Hello World!\";\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void idNodeTest() throws IOException {
        String program = "int a\n" +
                "a := 1";
        String expected = "int a;\n" +
                "a = 1;\n";
        assertEquals(expected, getArduinoCode(program));
    }

    @Test
    public void startNodeTest() throws IOException {
        String program = "";
        String expected = "";
        assertEquals(expected, getArduinoCode(program));
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

        String expected = "int testOne;\n" +
                "int testTwo;\n" +
                "int testThree;\n" +
                "int testFourOne;\n" +
                "int testFourTwo;\n" +
                "int testFour;\n" +
                "int medium;\n" +
                "double doubleTest;\n" +
                "bool boolTest1;\n" +
                "bool boolTest2;\n" +
                "char stringTest[42];\n" +
                "testOne = 1;\n" +
                "testTwo = 20;\n" +
                "testThree = 30;\n" +
                "testFourOne = 10;\n" +
                "testFourTwo = 10;\n" +
                "testFour = testFourOne+testFourTwo;\n" +
                "doubleTest = 23.23;\n" +
                "boolTest1 = true;\n" +
                "boolTest2 = false;\n" +
                "stringTest = \"Hello world!\";\n" +
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
        assertEquals(expected, getArduinoCode(program));
    }

    // Takes a program as a String and returns the generated C code
    private String getArduinoCode(String input) throws IOException {
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
        ArduinoCodeGenerationVisitor arduinoCodeGenerationVisitor = new ArduinoCodeGenerationVisitor(ps);
        ast.accept(arduinoCodeGenerationVisitor);
        if(errorHandler.hasErrors()) {
            errorHandler.printErrors("Test reason");
        }

        // Return ByteArrayOutputStream String
        return os.toString("UTF8");
    }
}