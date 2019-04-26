package astvisitors;

import ast.AstNode;
import astvisitors.SymbolTableVisitor;
import cstvisitors.BuildAstVisitor;
import exceptions.ErrorHandler;
import generated.EzuinoLexer;
import generated.EzuinoParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


import java.io.IOException;

public class SymbolTableVisitorTest {
    private SymbolTableVisitor symbolTableVisitor = new SymbolTableVisitor();
    private BuildAstVisitor buildAstVisitor = new BuildAstVisitor();

    /* Test with several scopes and one duplicate declaration (int a) */
    @Test
    public void intDclNodeTest() throws IOException {
        EzuinoParser ep = createParser("int number\n" +
                " \n" +
                "func main() {\n" +
                "    int a\n" +
                "    int a\n" +
                "    a := 1\n" +
                "    if (number < 100) {\n" +
                "        int a\n" +
                "        b := 2\n" +
                "    }\n" +
                "    if (number = 100) {\n" +
                "       int b\n" +
                "       c := 2\n" +
                "    }\n" +
                "}");
        AstNode astNode = ep.start().accept(buildAstVisitor);
        astNode.accept(symbolTableVisitor);
    }


    @Test
    public void doesNotViewVariableDeclarationAsFunction() throws IOException {
        EzuinoParser ep = createParser("int b\n" +
                "int a\n" +
                "b := a()");
        AstNode astNode = ep.start().accept(buildAstVisitor);
        astNode.accept(symbolTableVisitor);
        assertTrue(ErrorHandler.hasErrors());
    }


    private EzuinoParser createParser(String testString) throws IOException {
        CharStream stream = CharStreams.fromString(testString);
        EzuinoLexer lexer = new EzuinoLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        return parser;
    }
}