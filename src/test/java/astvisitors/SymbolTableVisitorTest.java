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

    @Test
    public void intDclNodeTest() throws IOException {
        String testProgram = "int number\n" +
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
                "}";
        ErrorHandler e = testStuff(testProgram);
        assertTrue(e.hasErrors());
    }

    private ErrorHandler testStuff(String testString) throws IOException {
        ErrorHandler errorhandler = new ErrorHandler();
        SymbolTableVisitor symbolTableVisitor = new SymbolTableVisitor(false, errorhandler);
        BuildAstVisitor buildAstVisitor = new BuildAstVisitor();
        EzuinoParser ezuinoParser = createParser(testString);
        AstNode astNode = ezuinoParser.start().accept(buildAstVisitor);
        astNode.accept(symbolTableVisitor);
        return errorhandler;
    }

    private EzuinoParser createParser(String testString) {
        CharStream stream = CharStreams.fromString(testString);
        EzuinoLexer lexer = new EzuinoLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        return parser;
    }
}