package cstvisitors;

import static org.junit.Assert.*;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import ast.expr.AdditiveExprNode;
import ast.expr.ExprNode;
import ast.type.IntegerNode;
import generated.EzuinoLexer;
import generated.EzuinoParser;

public class BuildAstVisitorExprTest {
	BuildAstVisitor visitor = new BuildAstVisitor();
	@Test
	public void simplePlusExprTest() throws IOException {
		EzuinoParser ep = createParser("1 + 1");
		AdditiveExprNode topNode = (AdditiveExprNode)ep.expr().accept(visitor);
		
		assertEquals("1", ((IntegerNode)topNode.getLeftNode()).getVal());
		assertEquals("1", ((IntegerNode)topNode.getRightNode()).getVal());
		assertEquals("+", topNode.getOperator());
	}
	
	private EzuinoParser createParser(String testString) throws IOException {
        CharStream stream = CharStreams.fromString(testString);
        EzuinoLexer lexer = new EzuinoLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        return parser;
    }
}
