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
        ErrorHandler e = testProgram(testProgram);
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
        ErrorHandler e = testProgram(testProgram);
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
        ErrorHandler e = testProgram(testProgram);
        assertTrue(e.hasErrors());
    }

    @Test
    public void emptyIfStmtAndReturnValue() throws IOException {
        String testProgram = "func int hello(){\n" +
                "    if(true) {\n" +
                "\n" +
                "    }\n" +
                "    return 1\n" +
                "}";
        ErrorHandler e = testProgram(testProgram);
        assertFalse(e.hasErrors());
    }

    @Test
    public void multipleIfsSameLevel() throws IOException {
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
                "    else{" +
                "       return 1" +
                "    }" +
                "}";
        ErrorHandler e = testProgram(testProgram);
        assertFalse(e.hasErrors());
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
        ErrorHandler e = testProgram(testProgram);
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
        ErrorHandler e = testProgram(testProgram);
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
        ErrorHandler e = testProgram(testProgram);
        assertTrue(e.hasErrors());
    }

    @Test
    public void returnAsBreakReturnTypeError() throws IOException {
        String testProgram = "func hello(){\n" +
                "  if(true){\n" +
                "    return 1\n" +
                "  }\n" +
                "  return\n" +
                "}";
        ErrorHandler e = testProgram(testProgram);
        assertTrue(e.hasErrors());
    }

    @Test
    public void returnAsBreakReturn() throws IOException {
        String testProgram = "func hello(){ \n" +
                "  if(true){ \n" +
                "    return \n" +
                "  } \n" +
                "  return \n" +
                "";
        ErrorHandler e = testProgram(testProgram);
        assertFalse(e.hasErrors());
    }

    /* Gives error type mismatch, return statements are not the same type */
    @Test
    public void returnTwoDifferentTypesError() throws IOException {
        String testProgram = "func int hello(){\n" +
                "  if(true){\n" +
                "    return 3.4\n" +
                "  }\n" +
                "  else {\n" +
                "    return 4\n" +
                "  }\n" +
                "}";
        ErrorHandler e = testProgram(testProgram);
        assertTrue(e.hasErrors());
    }

    @Test
    public void whileTest() throws IOException {
        String testProgram = "func int main() {\n" +
                "    while(1<2){\n" +
                "        return 1\n" +
                "    }\n" +
                "    return 1\n" +
                "}";
        ErrorHandler e = testProgram(testProgram);
        assertFalse(e.hasErrors());
    }

    @Test
    public void whileTestNotGuaranteedReturn() throws IOException {
        String testProgram = "func int main() {\n" +
                "    while(1<2){\n" +
                "        return 1\n" +
                "    }\n" +
                "}\n";
        ErrorHandler e = testProgram(testProgram);
        assertTrue(e.hasErrors());
    }

    @Test
    public void whileTestTypeNotTheSame() throws IOException {
        String testProgram = "func int main() {\n" +
                "    while(1<2){\n" +
                "        return \"hello\"\n" +
                "    }\n" +
                "    return \"my world\"\n" +
                "}";
        ErrorHandler e = testProgram(testProgram);
        assertTrue(e.hasErrors());
    }

    @Test
    public void whileTestVoidFunc() throws IOException {
        String testProgram = "func main() {\n" +
                "    while(1<2){\n" +
                "        int a\n" +
                "    }\n" +
                "}";
        ErrorHandler e = testProgram(testProgram);
        assertFalse(e.hasErrors());
    }

    @Test
    public void funcWithWhileStmtWithoutReturn() throws IOException {
        String testProgram = "func int main() { \n" +
                "    while(1<2){ \n" +
                "        int a \n" +
                "    } \n" +
                "    return 1 \n" +
                "}\n";
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ErrorHandler e = testProgram(testProgram);
        assertFalse(e.hasErrors());
    }

    @Test
    public void ifStmtAfterWhile() throws IOException {
        String testProgram = "func int main() {\n" +
                "    while(1<2){\n" +
                "    }\n" +
                "    if(1>2) {\n" +
                "      \n" +
                "    }\n" +
                " return 1" +
                "}";
        ErrorHandler e = testProgram(testProgram);
        assertFalse(e.hasErrors());
    }

    @Test
    public void notStringTest() throws IOException {
        String program = "string s s := !\"test\"";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void minusStringTest() throws IOException {
        String program = "string s s := -\"test\"";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void notIntegerTest() throws IOException {
        String program = "int i i := !1";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void minusIntegerTest() throws IOException {
        String program = "int i i := -1";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void notDoubleTest() throws IOException {
        String program = "double d d := !1.0";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void minusDoubleTest() throws IOException {
        String program = "double d d := -1.0";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void notBooleanTest() throws IOException {
        String program = "boolean b b := !true";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void minusBooleanTest() throws IOException {
        String program = "boolean b b := -true";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void addString() throws IOException {
        String program = "string s s := \"1\" + \"2\"";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void addBoolean() throws IOException {
        String program = "boolean b b := true + false";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void addInteger() throws IOException {
        String program = "int i i := 1 + 1";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void addDouble() throws IOException {
        String program = "double d d := 1.0 + 1.0";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void subtractString() throws IOException {
        String program = "string s s := \"1\" - \"2\"";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void subtractBoolean() throws IOException {
        String program = "boolean b b := true - false";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void subtractInteger() throws IOException {
        String program = "int i i := 1 - 1";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void subtractDouble() throws IOException {
        String program = "double d d := 1.0 - 1.0";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void multiplyString() throws IOException {
        String program = "string s s := \"1\" * \"2\"";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void multiplyBoolean() throws IOException {
        String program = "boolean b b := true * false";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void multiplyInteger() throws IOException {
        String program = "int i i := 1 * 1";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void multiplyDouble() throws IOException {
        String program = "double d d := 1.0 * 1.0";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void divideString() throws IOException {
        String program = "string s s := \"1\" / \"2\"";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void divideBoolean() throws IOException {
        String program = "boolean b b := true / false";
        ErrorHandler e = testProgram(program);
        assertTrue(e.hasErrors());
    }

    @Test
    public void divideInteger() throws IOException {
        String program = "int i i := 1 / 1";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
    }

    @Test
    public void divideDouble() throws IOException {
        String program = "double d d := 1.0 / 1.0";
        ErrorHandler e = testProgram(program);
        assertFalse(e.hasErrors());
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
        astNode.accept(new SymbolTableVisitor(printDcl, errorhandler));
        astNode.accept(new Typechecker(errorhandler));
        return errorhandler;
    }
}
