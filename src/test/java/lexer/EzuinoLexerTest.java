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
	
	private EzuinoParser createParser(String testString) throws IOException {
        CharStream stream = CharStreams.fromString(testString);
        EzuinoLexer lexer = new EzuinoLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        return parser;
    }
}
