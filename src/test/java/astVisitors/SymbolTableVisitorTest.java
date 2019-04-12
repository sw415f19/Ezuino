package astVisitors;

import ast.AstNode;
import ast.DclNode;
import ast.Type;
import astvisitors.SymbolTableVisitor;
import cstvisitors.BuildAstVisitor;
import generated.EzuinoLexer;
import generated.EzuinoParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SymbolTableVisitorTest {
    SymbolTableVisitor symbolTableVisitor = new SymbolTableVisitor();
    BuildAstVisitor buildAstVisitor = new BuildAstVisitor();

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

    private EzuinoParser createParser(String testString) throws IOException {
        CharStream stream = CharStreams.fromString(testString);
        EzuinoLexer lexer = new EzuinoLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        return parser;
    }
}