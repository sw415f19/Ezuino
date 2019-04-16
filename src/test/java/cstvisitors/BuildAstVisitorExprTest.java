package cstvisitors;

import static org.junit.Assert.*;

import java.io.IOException;

import ast.AstNode;
import ast.expr.*;
import ast.expr.EqualityExprNode;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import ast.type.IntegerNode;
import generated.EzuinoLexer;
import generated.EzuinoParser;

public class BuildAstVisitorExprTest {
	BuildAstVisitor visitor = new BuildAstVisitor();
	@Test
	public void simplePlusExprTest() throws IOException {
		EzuinoParser ep = createParser("1 + 2");
		AdditiveExprNode topNode = (AdditiveExprNode)ep.expr().accept(visitor);
		
		assertTrue(topNode.getLeftNode() instanceof IntegerNode);
		assertTrue(topNode.getRightNode() instanceof IntegerNode);
		assertEquals("1", ((IntegerNode)topNode.getLeftNode()).getVal());
		assertEquals("2", ((IntegerNode)topNode.getRightNode()).getVal());
		assertEquals("+", topNode.getOperator());
	}

	@Test
	public void simpleMultiplicativeExprTest() throws IOException {
		EzuinoParser ep = createParser("1 * 10");
		MultiplicativeExprNode topNode = (MultiplicativeExprNode) ep.expr().accept(visitor);

		assertTrue(topNode.getLeftNode() instanceof IntegerNode);
		assertTrue(topNode.getRightNode() instanceof IntegerNode);
		assertEquals("1", ((IntegerNode)topNode.getLeftNode()).getVal());
		assertEquals("10", ((IntegerNode)topNode.getRightNode()).getVal());
		assertEquals("*", topNode.getOperator());
	}

	@Test
	public void simpleRelationalExprTest() throws IOException {
		EzuinoParser ep = createParser("1 <= 10");
		RelationalExprNode topNode = (RelationalExprNode) ep.expr().accept(visitor);

		assertTrue(topNode.getLeftNode() instanceof IntegerNode);
		assertTrue(topNode.getRightNode() instanceof IntegerNode);
		assertEquals("1", ((IntegerNode)topNode.getLeftNode()).getVal());
		assertEquals("10", ((IntegerNode)topNode.getRightNode()).getVal());
		assertEquals("<=", topNode.getOperator());
	}

	@Test
	public void simpleEqualityExprTest() throws IOException {
		EzuinoParser ep = createParser("1 = 1");
		EqualityExprNode topNode = (EqualityExprNode) ep.expr().accept(visitor);

		assertTrue(topNode.getLeftNode() instanceof IntegerNode);
		assertTrue(topNode.getRightNode() instanceof IntegerNode);
		assertEquals("1", ((IntegerNode)topNode.getLeftNode()).getVal());
		assertEquals("1", ((IntegerNode)topNode.getRightNode()).getVal());
		assertEquals("=", topNode.getOperator());
	}
	@Test
	public void simpleLogicalExprTest() throws IOException {
		EzuinoParser ep = createParser("1 AND 10");
		AstNode topNode = ep.expr().accept(visitor);
		assertTrue(topNode instanceof LogicalAndExprNode);
	}
	private EzuinoParser createParser(String testString) throws IOException {
        CharStream stream = CharStreams.fromString(testString);
        EzuinoLexer lexer = new EzuinoLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        return parser;
    }
}
