package astvisitors;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import ast.AstNode;
import cstvisitors.BuildAstVisitor;
import generated.EzuinoLexer;
import generated.EzuinoParser;

public class JavaBytecodeGeneratorVisitorTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	private String getJBCode(String input) throws IOException {
        // ANTLR
        CharStream cs = CharStreams.fromString(input);
        EzuinoLexer lLexer = new EzuinoLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lLexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        ParseTree parseTree = parser.start();

        // Custom AST
        BuildAstVisitor buildAstVisitor = new BuildAstVisitor();
        AstNode astNode = parseTree.accept(buildAstVisitor);
        SymbolTableVisitor symbolTableFillingVisitor = new SymbolTableVisitor(true);
        astNode.accept(symbolTableFillingVisitor);
        Typechecker tc = new Typechecker();
        astNode.accept(tc);

        // Custom Code generation
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        JavaBytecodeGeneratorVisitor jBCodeGenerationVisitor = new JavaBytecodeGeneratorVisitor(ps);
        astNode.accept(jBCodeGenerationVisitor);

        // Return ByteArrayOutputStream String
        return os.toString("UTF8");
    }
}
