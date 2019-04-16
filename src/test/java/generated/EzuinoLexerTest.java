package generated;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import exceptions.ErrorListener;

public class EzuinoLexerTest {

	@Test
	public void testNewLine() throws IOException {
		EzuinoParser ep = createParser("\n");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void testDclAfterStmt() throws IOException {
		EzuinoParser ep = createParser("a := 1 int a");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void testDcl() throws IOException {
		EzuinoParser ep = createParser("int a");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void testNoMultiDcl() throws IOException {
		EzuinoParser ep = createParser("int a, b");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void typeBoolean() throws IOException {
		EzuinoParser ep = createParser("boolean a");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeBooleanAssignment() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeBooleanAssignmentSpellingLowercase() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := true");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeBooleanAssignmentSpellingTitlecase() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := True");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeBooleanAssignmentWithANDOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE AND FALSE");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeBooleanAssignmentWith2ANDOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE AND FALSE AND FALSE");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeBooleanAssignmentWithOROperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE OR FALSE");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeBooleanAssignmentWith2OROperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE OR FALSE OR FALSE");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeBooleanAssignmentWithParanthesisOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE OR (FALSE OR FALSE)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeBooleanAssignmentWith2ParanthesisOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE OR (FALSE OR (TRUE AND TRUE))");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeBooleanAssignmentWithLessThanLogicOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := 1 < 2");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeBooleanAssignmentWithGreatherThanLogicOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := 1 > 2");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeBooleanAssignmentWithEqualLogicOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := 1 = 2");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeBooleanAssignmentWithNotEqualLogicOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := 1 != 2");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeBooleanAssignmentWithNotOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := !FALSE");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeBooleanAssignmentWithGreatherThanOrEqualLogicOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := 1 >= 2");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeBooleanAssignmentWithLessThanOrEqualLogicOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := 1 <= 2");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	/*
	 * TODO: Skal denne test virkelig kaste en RecognitionException? Dette er jo en
	 * typefejl, og ikke en genkendelsesfejl
	 */
	@Test
	public void typeBooleanAssignmentWith3WayLogicOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := 1 < 2 < 3");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	// Se ovenstående kommentar
	@Test
	public void typeBooleanAssignmentAdd() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE + TRUE");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	// Se ovenstående kommentar
	@Test
	public void typeBooleanAssignmentMinus() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE - TRUE");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	// Se ovenstående kommentar
	@Test
	public void typeBooleanAssignmentMult() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE * TRUE");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	// Se ovenstående kommentar
	@Test
	public void typeBooleanAssignmentDiv() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE / TRUE");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}
	@Test
	public void typeBooleanAssignExpression() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := (1 < 2)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}
	@Test
	public void typeBooleanAssignMultiExpression() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := (1 < 2) OR (func AND 3 < 4)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeBooleanShortName() throws IOException {
		EzuinoParser ep = createParser("bool a");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void typeInt() throws IOException {
		EzuinoParser ep = createParser("int a");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeIntLongName() throws IOException {
		EzuinoParser ep = createParser("integer a");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void typeIntLongNameTitleCase() throws IOException {
		EzuinoParser ep = createParser("Integer a");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void typeIntAssignment() throws IOException {
		EzuinoParser ep = createParser("int a\na := 12");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeIntAssignmentAdd() throws IOException {
		EzuinoParser ep = createParser("int a\na := 12 + 10");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeIntAssignment2Add() throws IOException {
		EzuinoParser ep = createParser("int a\na := 12 + 10 + 10");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeIntAssignmentAddOne() throws IOException {
		EzuinoParser ep = createParser("int a\na := 0\na := a + 1");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeIntPostFixIncrement() throws IOException {
		EzuinoParser ep = createParser("int a\na := 0\na++");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void typeIntShorthandIncrement() throws IOException {
		EzuinoParser ep = createParser("int a\na := 0\na += 1");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void typeIntShorthandIncrement2() throws IOException {
		EzuinoParser ep = createParser("int a\na := 0\na +:= 1");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void typeIntPreFixIncrement() throws IOException {
		EzuinoParser ep = createParser("int a\na := 0\n++a");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void typeIntAssignmentMinus() throws IOException {
		EzuinoParser ep = createParser("int a\na := 12 - 10");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeIntAssignment2Minus() throws IOException {
		EzuinoParser ep = createParser("int a\na := 12 - 10 - 10");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeIntAssignmentMult() throws IOException {
		EzuinoParser ep = createParser("int a\na := 12 * 10");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeIntAssignment2Mult() throws IOException {
		EzuinoParser ep = createParser("int a\na := 12 * 10 * 5");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeIntAssignmentDiv() throws IOException {
		EzuinoParser ep = createParser("int a\na := 20 / 10");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeIntAssignment2Div() throws IOException {
		EzuinoParser ep = createParser("int a\na := 20 / 10 / 1");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeDouble() throws IOException {
		EzuinoParser ep = createParser("double a");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeDoubleAssignment() throws IOException {
		EzuinoParser ep = createParser("double a\na := 1.0");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeDoubleAssignmentNoTrailingZeroes() throws IOException {
		EzuinoParser ep = createParser("double a\na := 1.");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeDoubleAssignmentNoLeadingZeroes() throws IOException {
		EzuinoParser ep = createParser("double a\na := .5");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeDoubleAssignmentAdd() throws IOException {
		EzuinoParser ep = createParser("double a\na := 1.0 + 1.0");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeDoubleAssignmentMinus() throws IOException {
		EzuinoParser ep = createParser("double a\na := 1.0 - 1.0");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeDoubleAssignmentMult() throws IOException {
		EzuinoParser ep = createParser("double a\na := 1.0 * 2.0");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeDoubleAssignmentDiv() throws IOException {
		EzuinoParser ep = createParser("double a\na := 1.0 / 1.0");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeString() throws IOException {
		EzuinoParser ep = createParser("string s");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeStringAssignment() throws IOException {
		EzuinoParser ep = createParser("string s\ns := \"string\"");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeStringAssignmentContainingSingleQuotes() throws IOException {
		EzuinoParser ep = createParser("string s\ns := \"string's string\"");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeStringAssignmentWrongQuotesSingleQuote() throws IOException {
		EzuinoParser ep = createParser("string s\ns := 'string'");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void typeStringAssignmentWrongQuotesGraveAccent() throws IOException {
		EzuinoParser ep = createParser("string s\ns := `string`");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void typeStringAssignmentDoubleQuotesInString() throws IOException {
		EzuinoParser ep = createParser("string s\ns := \"this is called a \"\"string\"\" \"");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void print() throws IOException {
		EzuinoParser ep = createParser("print(1)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeStringConcat() throws IOException {
		EzuinoParser ep = createParser("string s\ns := \"a\"+\"b\"");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeStringMinus() throws IOException {
		EzuinoParser ep = createParser("string s\ns := \"a\"-\"b\"");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeStringMult() throws IOException {
		EzuinoParser ep = createParser("string s\ns := \"a\"*\"b\"");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void typeStringDiv() throws IOException {
		EzuinoParser ep = createParser("string s\ns := \"a\"/\"b\"");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void printExpr() throws IOException {
		EzuinoParser ep = createParser("print(\"a\"+\"b\")");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void ifStmt() throws IOException {
		EzuinoParser ep = createParser("if(true){\nprint(\"YAS\")}");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void ifStmtNoBrackets() throws IOException {
		EzuinoParser ep = createParser("if(true)\nprint(\"YAS\")");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void ifStmtExpr() throws IOException {
		EzuinoParser ep = createParser("if(1<2){\nprint(\"YAS\")}");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void ifStmtExprWithNested() throws IOException {
		EzuinoParser ep = createParser("if(FALSE OR (1<2)){\nprint(\"YAS\")}");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void ifElseStmtExpr() throws IOException {
		EzuinoParser ep = createParser("if(TRUE){\nprint(\"YAS\")}else{print(\"NOOO\")}");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void ifElseStmtExprNoBrackets() throws IOException {
		EzuinoParser ep = createParser("if(TRUE){\nprint(\"YAS\")}else print(\"NOOO\")");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}
/*
	@Test
	public void ifShorthandIf() throws IOException {
		EzuinoParser ep = createParser("int a\na := TRUE? 1 : 2");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}
*/
	@Test
	public void whilestmt() throws IOException {
		EzuinoParser ep = createParser("int a\na := 1\nwhile(a < 4){print(a)\na := a + 1}");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void returnkeyword() throws IOException {
		EzuinoParser ep = createParser("return");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void voidFunctionTest() throws IOException {
		EzuinoParser ep = createParser("func f(){}");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void intFunctionTest() throws IOException {
		EzuinoParser ep = createParser("func int f(){}");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void doubleFunctionTest() throws IOException {
		EzuinoParser ep = createParser("func double f(){}");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void stringFunctionTest() throws IOException {
		EzuinoParser ep = createParser("func string f(){}");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void listFunctionTest() throws IOException {
		EzuinoParser ep = createParser("func list f(){}");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void unknownFunctionTest() throws IOException {
		EzuinoParser ep = createParser("func money f(){}");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void singleParamFunctionTest() throws IOException {
		EzuinoParser ep = createParser("func f(int a){}");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void multiParamFunctionTest() throws IOException {
		EzuinoParser ep = createParser("func f(int a, int b){}");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void multiParamDiffTypesFunctionTest() throws IOException {
		EzuinoParser ep = createParser("func f(int a, double b){}");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void voidReturnFunction() throws IOException {
		EzuinoParser ep = createParser("func f(){return 42}");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void returnFunction() throws IOException {
		EzuinoParser ep = createParser("func int f(){return 42}");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void functionInvokation() throws IOException {
		EzuinoParser ep = createParser("f()");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void assignFunctionInvokation() throws IOException {
		EzuinoParser ep = createParser("int a\na := f()");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void functionInvokationParam() throws IOException {
		EzuinoParser ep = createParser("f(b)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void assignFunctionInvokationParam() throws IOException {
		EzuinoParser ep = createParser("int a\na := f(b)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void functionInvokationMultiParam() throws IOException {
		EzuinoParser ep = createParser("f(b, c, d, e)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void assignFunctionInvokationMultiParam() throws IOException {
		EzuinoParser ep = createParser("int a\na := f(b, c, d, e)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}
/*
	@Test
	public void listAdd() throws IOException {
		EzuinoParser ep = createParser("list_add()");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void listAddParam() throws IOException {
		EzuinoParser ep = createParser("list_add(a)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void listAddParams() throws IOException {
		EzuinoParser ep = createParser("list_add(a, b, c)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void listRemove() throws IOException {
		EzuinoParser ep = createParser("list_remove()");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void listRemoveParam() throws IOException {
		EzuinoParser ep = createParser("list_remove(a)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void listRemoveParams() throws IOException {
		EzuinoParser ep = createParser("list_remove(a, b, c)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}
*/
	@Test
	public void intListDcl() throws IOException {
		EzuinoParser ep = createParser("list int myList 4 := (1, 2, 3)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void doubleListDcl() throws IOException {
		EzuinoParser ep = createParser("list double myList 4 := (1.0, 2.5, 3.3)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void stringListDcl() throws IOException {
		EzuinoParser ep = createParser("list string myList 4 := (\"aa\", \"bb\", \"cc\")");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void booleanListDcl() throws IOException {
		EzuinoParser ep = createParser("list boolean myList 4 := (TRUE, FALSE, TRUE)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}
/*
	@Test
	public void emptyBraches() throws IOException {
		EzuinoParser ep = createParser("{}");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	*/
	@Test
	public void switchStmt() throws IOException {
		String program = "switch(a)\n" + "  {case 1: return 1}" + "  {case 2: return 2}";
		EzuinoParser ep = createParser(program);
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void switchMultiStmt() throws IOException {
		String program = "switch(a)\n" + "  {case 1,3: return 1}" + "  {case 2: return 2}";
		EzuinoParser ep = createParser(program);
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void missingParamInFuncCall() throws IOException {
		EzuinoParser ep = createParser("a(");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertTrue(el.hasError());
	}

	@Test
	public void emptyVoidFuncCall() throws IOException {
		EzuinoParser ep = createParser("void()");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void valInVoidFuncCall() throws IOException {
		EzuinoParser ep = createParser("void(55)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void manyValInVoidFuncCall() throws IOException {
		EzuinoParser ep = createParser("void(55, 55, 55, 55)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void plusValInVoidFuncCall() throws IOException {
		EzuinoParser ep = createParser("void(55 + 55)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void minusValInVoidFuncCall() throws IOException {
		EzuinoParser ep = createParser("void(55 - 55)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void multipleValInVoidFuncCall() throws IOException {
		EzuinoParser ep = createParser("void(55 * 55)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void divideValInVoidFuncCall() throws IOException {
		EzuinoParser ep = createParser("void(55 / 55)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	// Should be caught in type checking
	@Test
	public void andInVoidFuncCall() throws IOException {
		EzuinoParser ep = createParser("void(55 AND 55)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	// Should be caught in type checking
	@Test
	public void orInVoidFuncCall() throws IOException {
		EzuinoParser ep = createParser("void(55 OR 55)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	// Should be caught in type checking
	@Test
	public void equalInVoidFuncCall() throws IOException {
		EzuinoParser ep = createParser("void(55 = 55)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	// Should be caught in type checking
	@Test
	public void notEqualInVoidFuncCall() throws IOException {
		EzuinoParser ep = createParser("void(55 != 55)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	// Should be caught in type checking
	@Test
	public void lessInVoidFuncCall() throws IOException {
		EzuinoParser ep = createParser("void(55 < 55)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	// Should be caught in type checking
	@Test
	public void lessThanOrEqualInVoidFuncCall() throws IOException {
		EzuinoParser ep = createParser("void(55 <= 55)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	// Should be caught in type checking
	@Test
	public void greaterInVoidFuncCall() throws IOException {
		EzuinoParser ep = createParser("void(55 > 55)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	// Should be caught in type checking
	@Test
	public void greaterThanOrEqualInVoidFuncCall() throws IOException {
		EzuinoParser ep = createParser("void(55 >= 55)");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	@Test
	public void empty() throws IOException {
		EzuinoParser ep = createParser("");
		ErrorListener el = setErrorHandler(ep);
		ep.start();
		assertFalse(el.hasError());
	}

	private EzuinoParser createParser(String testString) throws IOException {
		CharStream stream = CharStreams.fromString(testString);
		EzuinoLexer lexer = new EzuinoLexer(stream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		EzuinoParser parser = new EzuinoParser(tokens);
		return parser;
	}

	private ErrorListener setErrorHandler(EzuinoParser parser) {
		parser.removeErrorListeners();
		ErrorListener el = new ErrorListener();
		parser.addErrorListener(el);
		return el;
	}
}
