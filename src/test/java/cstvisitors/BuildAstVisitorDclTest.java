package cstvisitors;

import static org.junit.Assert.*;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import ast.AstNode;
import ast.DclNode;
import ast.DclsNode;
import ast.StartNode;
import ast.Type;
import cstvisitors.BuildAstVisitor;
import generated.EzuinoLexer;
import generated.EzuinoParser;

public class BuildAstVisitorDclTest {
	BuildAstVisitor visitor = new BuildAstVisitor();
	@Test
	public void intDclNodeTest() throws IOException {
		EzuinoParser ep = createParser("int a");
		DclNode topNode = (DclNode)ep.dcl().accept(visitor);
		assertEquals(Type.INT, topNode.getType());
		assertEquals("a", topNode.getID());
	}
	
	@Test
	public void doubleDclNodeTest() throws IOException {
		EzuinoParser ep = createParser("double a");
		DclNode topNode = (DclNode)ep.dcl().accept(visitor);
		assertEquals(Type.DOUBLE, topNode.getType());
		assertEquals("a", topNode.getID());
	}
	
	@Test
	public void stringDclNodeTest() throws IOException {
		EzuinoParser ep = createParser("string a");
		DclNode topNode = (DclNode)ep.dcl().accept(visitor);
		assertEquals(Type.STRING, topNode.getType());
		assertEquals("a", topNode.getID());
	}
	
	@Test
	public void booleanDclNodeTest() throws IOException {
		EzuinoParser ep = createParser("boolean a");
		DclNode topNode = (DclNode)ep.dcl().accept(visitor);
		assertEquals(Type.BOOL, topNode.getType());
		assertEquals("a", topNode.getID());
	}
	
	@Test
	public void twoDeclarationsNodeTest() throws IOException {
		EzuinoParser ep = createParser("int a double b");
		DclsNode topNode = (DclsNode)ep.dcls().accept(visitor);
		DclNode firstChild = topNode.getChild(0);
		DclNode secondChild = topNode.getChild(1);
		
		assertEquals(Type.INT, firstChild.getType());
		assertEquals("a", firstChild.getID());
		assertEquals(Type.DOUBLE, secondChild.getType());
		assertEquals("b", secondChild.getID());
	}

	private EzuinoParser createParser(String testString) throws IOException {
        CharStream stream = CharStreams.fromString(testString);
        EzuinoLexer lexer = new EzuinoLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        return parser;
    }
}
