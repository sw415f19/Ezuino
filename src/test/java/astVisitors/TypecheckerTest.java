package astVisitors;

import ast.AstNode;
import astvisitors.SymbolTableVisitor;
import astvisitors.Typechecker;
import cstvisitors.BuildAstVisitor;
import generated.EzuinoLexer;
import generated.EzuinoParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import java.io.IOException;

public class TypecheckerTest {
    /* return skal kunne være tom
den skal tjekke om der er en else stmt efter if, og hvis der ikke nogen else stmts skal der være en uden for nestede scopes ved func def.
*/
    SymbolTableVisitor symbolTableVisitor = new SymbolTableVisitor();
    BuildAstVisitor buildAstVisitor = new BuildAstVisitor();
    Typechecker typechecker = new Typechecker();

    @Test
    public void missingElseStmt() throws IOException {
        String testProgram = "func int hello(){\n" +
                "  int a\n" +
                "  if(TRUE){\n" +
                "        return a\n" +
                "  }\n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
    }

    /* Gives error type mismatch, return statements are not the same type */
    @Test
    public void returnTwoDifferentTypes() throws IOException {
        String testProgram = "func int hello(){\n" +
                "  if(TRUE){\n" +
                "    return 3.4\n" +
                "  }\n" +
                "  else {\n" +
                "    return 4\n" +
                "  }\n" +
                "}";
        testWithTableAndTypeChecker(testProgram);
    }



    private EzuinoParser createParser(String testString) throws IOException {
        CharStream stream = CharStreams.fromString(testString);
        EzuinoLexer lexer = new EzuinoLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        return parser;
    }

    public void testWithTableAndTypeChecker(String testProgram) throws IOException {
        EzuinoParser ep = createParser(testProgram);
        AstNode astNode = ep.start().accept(buildAstVisitor);
        astNode.accept(symbolTableVisitor);
        astNode.accept(typechecker);
    }
}
