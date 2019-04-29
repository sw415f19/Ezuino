package astvisitors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import ast.AstNode;
import cstvisitors.BuildAstVisitor;
import exceptions.ErrorHandler;
import generated.EzuinoLexer;
import generated.EzuinoParser;

public class MissingReturnStmtTest {

    @Test
    public void missingReturn() {
        String program = "func int main() {\n" + "}";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void hasReturn() {
        String program = "func int main() {\n" + "return 2\n" + "}";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void elseBlockMissingReturn() {
        String program = "func int main() {\n" + "if(1<2) { return 1 }\n" + "}";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void ifBlockMissingReturn() {
        String program = "func int main() {\n" + "if(1<2) { } else { return 2 }\n" + "}";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void ifElseBlockHasReturn() {
        String program = "func int main() {\n" + "if(1<2) { return 1 } else { return 2 }\n" + "}";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    private ErrorHandler parseProgram(String program) {
        CharStream stream = CharStreams.fromString(program);
        EzuinoLexer lexer = new EzuinoLexer(stream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        EzuinoParser parser = new EzuinoParser(tokenStream);
        ParseTree parseTree = parser.start();
        AstNode ast = parseTree.accept(new BuildAstVisitor());

        ErrorHandler errorHandler = new ErrorHandler();
        ast.accept(new SymbolTableVisitor(false, errorHandler));
        ast.accept(new Typechecker(errorHandler));
        ast.accept(new ReturnStmtTypeCheckVisitor(errorHandler));
        ast.accept(new MissingReturnStmtVisitor(errorHandler));
        return errorHandler;
    }

}
