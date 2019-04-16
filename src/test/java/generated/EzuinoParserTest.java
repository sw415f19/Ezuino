package generated;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.junit.Before;
import org.junit.Test;

public class EzuinoParserTest {
	static PrintStream originalOut = System.out;
	static PrintStream originalErr = System.err;
	@Before //this is to ignore all the garbage output from failing functions
	//use in conjunction with redirectOutputsToNothing()
	public void reassignPrintStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}
	
	@Test
	public void intParseTest() throws IOException{
		EzuinoParser ep = createParser("int");
		Token resultToken = (Token)ep.type().getChild(0).getPayload();
		assertEquals(EzuinoParser.INTDCL, resultToken.getType());
	}
	@Test
	public void doubleParseTest() throws IOException{
		EzuinoParser ep = createParser("double");
		Token resultToken = (Token)ep.type().getChild(0).getPayload();
		assertEquals(EzuinoParser.DOUBLEDCL, resultToken.getType());
	}

	@Test
	public void stringParseTest() throws IOException{
		EzuinoParser ep = createParser("string");
		Token resultToken = (Token)ep.type().getChild(0).getPayload();
		assertEquals(EzuinoParser.STRINGDCL, resultToken.getType());
	}
	
	private EzuinoParser createParser(String testString) throws IOException {
        CharStream stream = CharStreams.fromString(testString);
        EzuinoLexer lexer = new EzuinoLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        return parser;
	}
}
