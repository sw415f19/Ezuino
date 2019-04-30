package generated;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import exceptions.ErrorHandler;
import exceptions.ErrorListener;

public class EzuinoLexerTest {

    @Test
    public void testNewLine() throws IOException {
        ErrorHandler errorHandler = parseProgram("\n");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void testDclAfterStmt() throws IOException {
        ErrorHandler errorHandler = parseProgram("a := 1 int a");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void testDcl() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void testNoMultiDcl() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a, b");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void typeBoolean() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignment() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := TRUE");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignmentSpellingLowercase() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := true");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignmentSpellingTitlecase() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := True");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignmentWithANDOperator() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := TRUE AND FALSE");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignmentWith2ANDOperator() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := TRUE AND FALSE AND FALSE");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignmentWithOROperator() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := TRUE OR FALSE");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignmentWith2OROperator() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := TRUE OR FALSE OR FALSE");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignmentWithParanthesisOperator() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := TRUE OR (FALSE OR FALSE)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignmentWith2ParanthesisOperator() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := TRUE OR (FALSE OR (TRUE AND TRUE))");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignmentWithLessThanLogicOperator() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := 1 < 2");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignmentWithGreatherThanLogicOperator() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := 1 > 2");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignmentWithEqualLogicOperator() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := 1 = 2");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignmentWithNotEqualLogicOperator() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := 1 != 2");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignmentWithNotOperator() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := !FALSE");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignmentWithGreatherThanOrEqualLogicOperator() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := 1 >= 2");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignmentWithLessThanOrEqualLogicOperator() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := 1 <= 2");
        assertFalse(errorHandler.hasErrors());
    }

    /*
     * TODO: Skal denne test virkelig kaste en RecognitionException? Dette er jo en
     * typefejl, og ikke en genkendelsesfejl
     */
    @Test
    public void typeBooleanAssignmentWith3WayLogicOperator() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := 1 < 2 < 3");
        assertFalse(errorHandler.hasErrors());
    }

    // Se ovenstående kommentar
    @Test
    public void typeBooleanAssignmentAdd() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := TRUE + TRUE");
        assertFalse(errorHandler.hasErrors());
    }

    // Se ovenstående kommentar
    @Test
    public void typeBooleanAssignmentMinus() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := TRUE - TRUE");
        assertFalse(errorHandler.hasErrors());
    }

    // Se ovenstående kommentar
    @Test
    public void typeBooleanAssignmentMult() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := TRUE * TRUE");
        assertFalse(errorHandler.hasErrors());
    }

    // Se ovenstående kommentar
    @Test
    public void typeBooleanAssignmentDiv() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := TRUE / TRUE");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignExpression() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := (1 < 2)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanAssignMultiExpression() throws IOException {
        ErrorHandler errorHandler = parseProgram("boolean a\na := (1 < 2) OR (asd AND 3 < 4)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeBooleanShortName() throws IOException {
        ErrorHandler errorHandler = parseProgram("bool a");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void typeInt() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeIntLongName() throws IOException {
        ErrorHandler errorHandler = parseProgram("integer a");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void typeIntLongNameTitleCase() throws IOException {
        ErrorHandler errorHandler = parseProgram("Integer a");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void typeIntAssignment() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := 12");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeIntAssignmentAdd() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := 12 + 10");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeIntAssignment2Add() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := 12 + 10 + 10");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeIntAssignmentAddOne() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := 0\na := a + 1");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeIntPostFixIncrement() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := 0\na++");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void typeIntShorthandIncrement() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := 0\na += 1");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void typeIntShorthandIncrement2() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := 0\na +:= 1");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void typeIntPreFixIncrement() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := 0\n++a");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void typeIntAssignmentMinus() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := 12 - 10");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeIntAssignment2Minus() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := 12 - 10 - 10");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeIntAssignmentMult() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := 12 * 10");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeIntAssignment2Mult() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := 12 * 10 * 5");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeIntAssignmentDiv() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := 20 / 10");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeIntAssignment2Div() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := 20 / 10 / 1");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeDouble() throws IOException {
        ErrorHandler errorHandler = parseProgram("double a");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeDoubleAssignment() throws IOException {
        ErrorHandler errorHandler = parseProgram("double a\na := 1.0");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeDoubleAssignmentNoTrailingZeroes() throws IOException {
        ErrorHandler errorHandler = parseProgram("double a\na := 1.");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void typeDoubleAssignmentNoLeadingZeroes() throws IOException {
        ErrorHandler errorHandler = parseProgram("double a\na := .5");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void typeDoubleAssignmentAdd() throws IOException {
        ErrorHandler errorHandler = parseProgram("double a\na := 1.0 + 1.0");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeDoubleAssignmentMinus() throws IOException {
        ErrorHandler errorHandler = parseProgram("double a\na := 1.0 - 1.0");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeDoubleAssignmentMult() throws IOException {
        ErrorHandler errorHandler = parseProgram("double a\na := 1.0 * 2.0");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeDoubleAssignmentDiv() throws IOException {
        ErrorHandler errorHandler = parseProgram("double a\na := 1.0 / 1.0");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeString() throws IOException {
        ErrorHandler errorHandler = parseProgram("string s");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeStringAssignment() throws IOException {
        ErrorHandler errorHandler = parseProgram("string s\ns := \"string\"");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeStringAssignmentContainingSingleQuotes() throws IOException {
        ErrorHandler errorHandler = parseProgram("string s\ns := \"string's string\"");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeStringAssignmentWrongQuotesSingleQuote() throws IOException {
        ErrorHandler errorHandler = parseProgram("string s\ns := 'string'");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void typeStringAssignmentWrongQuotesGraveAccent() throws IOException {
        ErrorHandler errorHandler = parseProgram("string s\ns := `string`");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void typeStringAssignmentDoubleQuotesInString() throws IOException {
        ErrorHandler errorHandler = parseProgram("string s\ns := \"this is called a \"\"string\"\" \"");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void print() throws IOException {
        ErrorHandler errorHandler = parseProgram("print(1)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeStringConcat() throws IOException {
        ErrorHandler errorHandler = parseProgram("string s\ns := \"a\"+\"b\"");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeStringMinus() throws IOException {
        ErrorHandler errorHandler = parseProgram("string s\ns := \"a\"-\"b\"");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeStringMult() throws IOException {
        ErrorHandler errorHandler = parseProgram("string s\ns := \"a\"*\"b\"");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void typeStringDiv() throws IOException {
        ErrorHandler errorHandler = parseProgram("string s\ns := \"a\"/\"b\"");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void printExpr() throws IOException {
        ErrorHandler errorHandler = parseProgram("print(\"a\"+\"b\")");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void ifStmt() throws IOException {
        ErrorHandler errorHandler = parseProgram("if(true){\nprint(\"YAS\")}");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void ifStmtNoBrackets() throws IOException {
        ErrorHandler errorHandler = parseProgram("if(true)\nprint(\"YAS\")");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void ifStmtExpr() throws IOException {
        ErrorHandler errorHandler = parseProgram("if(1<2){\nprint(\"YAS\")}");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void ifStmtExprWithNested() throws IOException {
        ErrorHandler errorHandler = parseProgram("if(FALSE OR (1<2)){\nprint(\"YAS\")}");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void ifElseStmtExpr() throws IOException {
        ErrorHandler errorHandler = parseProgram("if(TRUE){\nprint(\"YAS\")}else{print(\"NOOO\")}");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void ifElseStmtExprNoBrackets() throws IOException {
        ErrorHandler errorHandler = parseProgram("if(TRUE){\nprint(\"YAS\")}else print(\"NOOO\")");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void ifShorthandIf() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := TRUE? 1 : 2");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void whilestmt() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := 1\nwhile(a < 4){print(a)\na := a + 1}");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void voidFunctionTest() throws IOException {
        ErrorHandler errorHandler = parseProgram("func f(){}");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void intFunctionTest() throws IOException {
        ErrorHandler errorHandler = parseProgram("func int f(){}");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void doubleFunctionTest() throws IOException {
        ErrorHandler errorHandler = parseProgram("func double f(){}");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void stringFunctionTest() throws IOException {
        ErrorHandler errorHandler = parseProgram("func string f(){}");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void listFunctionTest() throws IOException {
        ErrorHandler errorHandler = parseProgram("func list f(){}");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void unknownFunctionTest() throws IOException {
        ErrorHandler errorHandler = parseProgram("func money f(){}");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void singleParamFunctionTest() throws IOException {
        ErrorHandler errorHandler = parseProgram("func f(int a){}");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void multiParamFunctionTest() throws IOException {
        ErrorHandler errorHandler = parseProgram("func f(int a, int b){}");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void multiParamDiffTypesFunctionTest() throws IOException {
        ErrorHandler errorHandler = parseProgram("func f(int a, double b){}");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void voidReturnFunction() throws IOException {
        ErrorHandler errorHandler = parseProgram("func f(){return 42}");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void returnFunction() throws IOException {
        ErrorHandler errorHandler = parseProgram("func int f(){return 42}");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void functionInvokation() throws IOException {
        ErrorHandler errorHandler = parseProgram("f()");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void assignFunctionInvokation() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := f()");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void functionInvokationParam() throws IOException {
        ErrorHandler errorHandler = parseProgram("f(b)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void assignFunctionInvokationParam() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := f(b)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void functionInvokationMultiParam() throws IOException {
        ErrorHandler errorHandler = parseProgram("f(b, c, d, e)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void assignFunctionInvokationMultiParam() throws IOException {
        ErrorHandler errorHandler = parseProgram("int a\na := f(b, c, d, e)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void listAdd() throws IOException {
        ErrorHandler errorHandler = parseProgram("list_add()");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void listAddParam() throws IOException {
        ErrorHandler errorHandler = parseProgram("list_add(a)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void listAddParams() throws IOException {
        ErrorHandler errorHandler = parseProgram("list_add(a, b, c)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void listRemove() throws IOException {
        ErrorHandler errorHandler = parseProgram("list_remove()");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void listRemoveParam() throws IOException {
        ErrorHandler errorHandler = parseProgram("list_remove(a)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void listRemoveParams() throws IOException {
        ErrorHandler errorHandler = parseProgram("list_remove(a, b, c)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void intListDcl() throws IOException {
        ErrorHandler errorHandler = parseProgram("list int myList 4 := (1, 2, 3)");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void doubleListDcl() throws IOException {
        ErrorHandler errorHandler = parseProgram("list double myList 4 := (1.0, 2.5, 3.3)");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void stringListDcl() throws IOException {
        ErrorHandler errorHandler = parseProgram("list string myList 4 := (\"aa\", \"bb\", \"cc\")");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void booleanListDcl() throws IOException {
        ErrorHandler errorHandler = parseProgram("list boolean myList 4 := (TRUE, FALSE, TRUE)");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void emptyBraches() throws IOException {
        ErrorHandler errorHandler = parseProgram("{}");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void switchStmt() throws IOException {
        String program = "switch(a)\n" + "  {case 1: return 1}" + "  {case 2: return 2}";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void switchMultiStmt() throws IOException {
        String program = "switch(a)\n" + "  {case 1,3: return 1}" + "  {case 2: return 2}";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void missingParamInFuncCall() throws IOException {
        ErrorHandler errorHandler = parseProgram("a(");
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void emptyVoidFuncCall() throws IOException {
        ErrorHandler errorHandler = parseProgram("void()");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void valInVoidFuncCall() throws IOException {
        ErrorHandler errorHandler = parseProgram("void(55)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void manyValInVoidFuncCall() throws IOException {
        ErrorHandler errorHandler = parseProgram("void(55, 55, 55, 55)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void plusValInVoidFuncCall() throws IOException {
        ErrorHandler errorHandler = parseProgram("void(55 + 55)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void minusValInVoidFuncCall() throws IOException {
        ErrorHandler errorHandler = parseProgram("void(55 - 55)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void multipleValInVoidFuncCall() throws IOException {
        ErrorHandler errorHandler = parseProgram("void(55 * 55)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void divideValInVoidFuncCall() throws IOException {
        ErrorHandler errorHandler = parseProgram("void(55 / 55)");
        assertFalse(errorHandler.hasErrors());
    }

    // Should be caught in type checking
    @Test
    public void andInVoidFuncCall() throws IOException {
        ErrorHandler errorHandler = parseProgram("void(55 AND 55)");
        assertFalse(errorHandler.hasErrors());
    }

    // Should be caught in type checking
    @Test
    public void orInVoidFuncCall() throws IOException {
        ErrorHandler errorHandler = parseProgram("void(55 OR 55)");
        assertFalse(errorHandler.hasErrors());
    }

    // Should be caught in type checking
    @Test
    public void equalInVoidFuncCall() throws IOException {
        ErrorHandler errorHandler = parseProgram("void(55 = 55)");
        assertFalse(errorHandler.hasErrors());
    }

    // Should be caught in type checking
    @Test
    public void notEqualInVoidFuncCall() throws IOException {
        ErrorHandler errorHandler = parseProgram("void(55 != 55)");
        assertFalse(errorHandler.hasErrors());
    }

    // Should be caught in type checking
    @Test
    public void lessInVoidFuncCall() throws IOException {
        ErrorHandler errorHandler = parseProgram("void(55 < 55)");
        assertFalse(errorHandler.hasErrors());
    }

    // Should be caught in type checking
    @Test
    public void lessThanOrEqualInVoidFuncCall() throws IOException {
        ErrorHandler errorHandler = parseProgram("void(55 <= 55)");
        assertFalse(errorHandler.hasErrors());
    }

    // Should be caught in type checking
    @Test
    public void greaterInVoidFuncCall() throws IOException {
        ErrorHandler errorHandler = parseProgram("void(55 > 55)");
        assertFalse(errorHandler.hasErrors());
    }

    // Should be caught in type checking
    @Test
    public void greaterThanOrEqualInVoidFuncCall() throws IOException {
        ErrorHandler errorHandler = parseProgram("void(55 >= 55)");
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void empty() throws IOException {
        ErrorHandler errorHandler = parseProgram("");
        assertFalse(errorHandler.hasErrors());
    }

    private ErrorHandler parseProgram(String program) throws IOException {
        CharStream stream = CharStreams.fromString(program);

        ErrorHandler errorHandler = new ErrorHandler();
        ErrorListener el = new ErrorListener(errorHandler);

        EzuinoLexer lexer = new EzuinoLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EzuinoParser parser = new EzuinoParser(tokens);

        lexer.removeErrorListeners();
        lexer.addErrorListener(el);
        parser.removeErrorListeners();
        parser.addErrorListener(el);
        parser.start();

        return errorHandler;
    }
}
