package lexer;

import static org.junit.Assert.*;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.junit.Test;

import parser.EzuinoParser;

public class EzuinoLexerTest {

	@Test
	public void testNewLine() throws IOException{
		EzuinoParser ep = createParser("\n");
		ep.start();
	}
	
	@Test(expected=RecognitionException.class)
	public void testDclAfterStmt() throws IOException{
		EzuinoParser ep = createParser("a := 1 int a");
		ep.start();
		//currently passes though it should fail
		//This is due to the lack of end character in our program
		//Our parser simply ignores any declarations after our statements
	}
	
	@Test
	public void testDcl() throws IOException {
		EzuinoParser ep = createParser("int a");
		ep.start();
	}
	
	//Denne test burde faktisk kaste en fejl, men den gør det ikke
	//Det har højst sandsynligt noget med grammatikken at gøre.
	@Test(expected=RecognitionException.class)
	public void testNoMultiDcl() throws IOException {
		EzuinoParser ep = createParser("int a, b");
		ep.start();
	}
	
	@Test
	public void typeBoolean() throws IOException {
		EzuinoParser ep = createParser("boolean a");
		ep.start();
	}
	
	@Test
	public void typeBooleanAssignment() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE");
		ep.start();
	}
	
	@Test
	public void typeBooleanAssignmentSpellingLowercase() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := true");
		ep.start();
	}
	
	@Test
	public void typeBooleanAssignmentSpellingTitlecase() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := True");
		ep.start();
	}
	
	@Test
	public void typeBooleanAssignmentWithANDOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE AND FALSE");
		ep.start();
	}
	
	@Test
	public void typeBooleanAssignmentWith2ANDOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE AND FALSE AND FALSE");
		ep.start();
	}
	
	@Test
	public void typeBooleanAssignmentWithOROperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE OR FALSE");
		ep.start();
	}
	
	@Test
	public void typeBooleanAssignmentWith2OROperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE OR FALSE OR FALSE");
		ep.start();
	}
	
	@Test
	public void typeBooleanAssignmentWithParanthesisOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE OR (FALSE OR FALSE)");
		ep.start();
	}
	
	@Test
	public void typeBooleanAssignmentWith2ParanthesisOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE OR (FALSE OR (TRUE AND TRUE))");
		ep.start();
	}
	
	@Test
	public void typeBooleanAssignmentWithLessThanLogicOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := 1 < 2");
		ep.start();
	}
	
	@Test
	public void typeBooleanAssignmentWithGreatherThanLogicOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := 1 > 2");
		ep.start();
	}
	
	@Test
	public void typeBooleanAssignmentWithEqualLogicOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := 1 = 2");
		ep.start();
	}
	
	@Test
	public void typeBooleanAssignmentWithNotEqualLogicOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := 1 != 2");
		ep.start();
	}

	@Test
	public void typeBooleanAssignmentWithNotOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := !FALSE");
		ep.start();
	}
	
	@Test
	public void typeBooleanAssignmentWithGreatherThanOrEqualLogicOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := 1 >= 2");
		ep.start();
	}
	
	@Test
	public void typeBooleanAssignmentWithLessThanOrEqualLogicOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := 1 <= 2");
		ep.start();
	}
	/*TODO: Skal denne test virkelig kaste en RecognitionException?
	 * Dette er jo en typefejl, og ikke en genkendelsesfejl
	 */
	@Test(expected=RecognitionException.class)
	public void typeBooleanAssignmentWith3WayLogicOperator() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := 1 < 2 < 3");
		ep.start();
	}
	//Se ovenstående kommentar
	@Test(expected=RecognitionException.class)
	public void typeBooleanAssignmentAdd() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE + TRUE");
		ep.start();
	}
	//Se ovenstående kommentar
	@Test(expected=RecognitionException.class)
	public void typeBooleanAssignmentMinus() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE - TRUE");
		ep.start();
	}
	//Se ovenstående kommentar
	@Test(expected=RecognitionException.class)
	public void typeBooleanAssignmentMult() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE * TRUE");
		ep.start();
	}
	//Se ovenstående kommentar
	@Test(expected=RecognitionException.class)
	public void typeBooleanAssignmentDiv() throws IOException {
		EzuinoParser ep = createParser("boolean a\na := TRUE / TRUE");
		ep.start();
	}
	//Dette er igen ikke et spørgsmål om hvorvidt vi genkender input eller ej
	//Det gør vi nemlig, da det sagtens kan være et variabelnavn, hvilket gør dette
	//til to udtryk frem for en deklaration. Dette er altså en semantisk fejl.
	@Test(expected=RecognitionException.class)
	public void typeBooleanShortName() throws IOException {
		EzuinoParser ep = createParser("bool a");
		ep.start();
	}

	@Test
	public void typeInt() throws IOException {
		EzuinoParser ep = createParser("int a");
		ep.start();
	}

	@Test
	public void typeIntLongName() throws IOException {
		EzuinoParser ep = createParser("integer a");
		ep.start();
	}

	@Test
	public void typeIntLongNameTitleCase() throws IOException {
		EzuinoParser ep = createParser("Integer a");
		ep.start();
	}

	@Test
	public void typeIntAssignment() throws IOException {
		EzuinoParser ep = createParser("int a\na := 12");
		ep.start();
	}

	@Test
	public void typeIntAssignmentAdd() throws IOException {
		EzuinoParser ep = createParser("int a\na := 12 + 10");
		ep.start();
	}

	@Test
	public void typeIntAssignmentAddOne() throws IOException {
		EzuinoParser ep = createParser("int a\na := 0\na := a + 1");
		ep.start();
	}

	@Test
	public void typeIntPostFixIncrement() throws IOException {
		EzuinoParser ep = createParser("int a\na := 0\na++");
		ep.start();
	}

	@Test
	public void typeIntShorthandIncrement() throws IOException {
		EzuinoParser ep = createParser("int a\na := 0\na += 1");
		ep.start();
	}

	@Test
	public void typeIntShorthandIncrement2() throws IOException {
		EzuinoParser ep = createParser("int a\na := 0\na +:= 1");
		ep.start();
	}

	@Test
	public void typeIntPreFixIncrement() throws IOException {
		EzuinoParser ep = createParser("int a\na := 0\n++a");
		ep.start();
	}

	@Test
	public void typeIntAssignmentMinus() throws IOException {
		EzuinoParser ep = createParser("int a\na := 12 - 10");
		ep.start();
	}

	@Test
	public void typeIntAssignmentMult() throws IOException {
		EzuinoParser ep = createParser("int a\na := 12 * 10");
		ep.start();
	}

	@Test
	public void typeIntAssignmentDiv() throws IOException {
		EzuinoParser ep = createParser("int a\na := 20 / 10");
		ep.start();
	}

	@Test
	public void typeDouble() throws IOException {
		EzuinoParser ep = createParser("double a");
		ep.start();
	}

	@Test
	public void typeDoubleAssignment() throws IOException {
		EzuinoParser ep = createParser("double a\na := 1.0");
		ep.start();
	}

	@Test
	public void typeDoubleAssignmentNoTrailingZeroes() throws IOException {
		EzuinoParser ep = createParser("double a\na := 1.");
		ep.start();
	}

	@Test
	public void typeDoubleAssignmentNoLeadingZeroes() throws IOException {
		EzuinoParser ep = createParser("double a\na := .5");
		ep.start();
	}

	@Test
	public void typeDoubleAssignmentAdd() throws IOException {
		EzuinoParser ep = createParser("double a\na := 1.0 + 1.0");
		ep.start();
	}

	@Test
	public void typeDoubleAssignmentMinus() throws IOException {
		EzuinoParser ep = createParser("double a\na := 1.0 - 1.0");
		ep.start();
	}

	@Test
	public void typeDoubleAssignmentMult() throws IOException {
		EzuinoParser ep = createParser("double a\na := 1.0 * 2.0");
		ep.start();
	}

	@Test
	public void typeDoubleAssignmentDiv() throws IOException {
		EzuinoParser ep = createParser("double a\na := 1.0 / 1.0");
		ep.start();
	}

	@Test
	public void typeString() throws IOException {
		EzuinoParser ep = createParser("string s");
		ep.start();
	}
	
	@Test
	public void typeStringAssignment() throws IOException {
		EzuinoParser ep = createParser("string s\ns := \"string\"");
		ep.start();
	}
	
	@Test
	public void typeStringAssignmentContainingSingleQuotes() throws IOException {
		EzuinoParser ep = createParser("string s\ns := \"string's string\"");
		ep.start();
	}
	//Tror der er en fejl med grammatikken i denne test, da det lader til
	//at vores parser ignorerer single quotes frem for at kaste en fejl.
	//Enten det, eller også skal vi lade 'string' være et gyldigt variabelnavn
	@Test(expected=RecognitionException.class)
	public void typeStringAssignmentWrongQuotesSingleQuote() throws IOException {
		EzuinoParser ep = createParser("string s\ns := 'string'");
		ep.start();
	}
	//Se ovenstående kommentar
	@Test(expected=RecognitionException.class)
	public void typeStringAssignmentWrongQuotesGraveAccent() throws IOException {
		EzuinoParser ep = createParser("string s\ns := `string`");
		ep.start();
	}

	@Test
	public void typeStringAssignmentDoubleQuotesInString() throws IOException {
		EzuinoParser ep = createParser("string s\ns := \"this is called a \"\"string\"\" \"");
		ep.start();
	}

	@Test
	public void print() throws IOException {
		EzuinoParser ep = createParser("print(1)");
		ep.start();
	}

	@Test
	public void typeStringConcat() throws IOException {
		EzuinoParser ep = createParser("string s\ns := \"a\"+\"b\"");
		ep.start();
	}

	//Semantisk fejl, ikke genkendelse
	@Test(expected=RecognitionException.class)
	public void typeStringMinus() throws IOException {
		EzuinoParser ep = createParser("string s\ns := \"a\"-\"b\"");
		ep.start();
	}

	@Test(expected=RecognitionException.class)
	public void typeStringMult() throws IOException {
		EzuinoParser ep = createParser("string s\ns := \"a\"*\"b\"");
		ep.start();
	}

	//Semantisk fejl, ikke en genkendelsesfejl
	@Test(expected=RecognitionException.class)
	public void typeStringDiv() throws IOException {
		EzuinoParser ep = createParser("string s\ns := \"a\"/\"b\"");
		ep.start();
	}

	@Test
	public void printExpr() throws IOException {
		EzuinoParser ep = createParser("print(\"a\"+\"b\")");
		ep.start();
	}
	
	@Test
	public void ifStmt() throws IOException {
		EzuinoParser ep = createParser("if(true){\nprint(\"YAS\")}");
		ep.start();
	}
	
	@Test
	public void ifStmtNoBrackets() throws IOException {
		EzuinoParser ep = createParser("if(true)\nprint(\"YAS\")");
		ep.start();
	}
	
	@Test
	public void ifStmtExpr() throws IOException {
		EzuinoParser ep = createParser("if(1<2){\nprint(\"YAS\")}");
		ep.start();
	}
	
	@Test
	public void ifStmtExprWithNested() throws IOException {
		EzuinoParser ep = createParser("if(FALSE OR (1<2)){\nprint(\"YAS\")}");
		ep.start();
	}
	
	@Test
	public void ifElseStmtExpr() throws IOException {
		EzuinoParser ep = createParser("if(TRUE){\nprint(\"YAS\")}else{print(\"NOOO\")}");
		ep.start();
	}
	
	@Test
	public void ifElseStmtExprNoBrackets() throws IOException {
		EzuinoParser ep = createParser("if(TRUE){\nprint(\"YAS\")}else print(\"NOOO\")");
		ep.start();
	}
	
	@Test
	public void ifShorthandIf() throws IOException {
		EzuinoParser ep = createParser("int a\na := TRUE? 1 : 2");
		ep.start();
	}
	
	@Test
	public void whilestmt() throws IOException {
		EzuinoParser ep = createParser("int a\na := 1\nwhile(a < 4){print(a)\na := a + 1}");
		ep.start();
	}
	
	@Test
	public void empty() throws IOException {
		EzuinoParser ep = createParser("");
		ep.start();
	}
	
	private EzuinoParser createParser(String testString) throws IOException {
        CharStream stream = CharStreams.fromString(testString);
        EzuinoLexer lexer = new EzuinoLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        return parser;
    }
}
