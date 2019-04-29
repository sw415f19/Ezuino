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

public class ReturnStmtTypeCheckerTest {

    @Test
    public void WrongReturnType() {
        String program = "func int main() { return 2.0 }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void CorrectReturnType() {
        String program = "func int main() { return 2 }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void WrongNestedIfReturnType() {
        String program = "func int main() { if(1<2) { return 2.0 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void WrongNestedElseReturnType() {
        String program = "func int main() { if(1<2) { } else { return 2.0 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void WrongNestedElseOtherScopeReturnType() {
        String program = "func int main() { if(1<2) { if (2<2) { } } else { return 2.0 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void CorrectNestedIfReturnType() {
        String program = "func int main() { if(1<2) { return 2 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void CorrectNestedElseReturnType() {
        String program = "func int main() { if(1<2) { } else { return 2 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void CorrectNestedElseOtherScopeReturnType() {
        String program = "func int main() { if(1<2) { if (2<2) { } } else { return 2 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void WrongTypeWhileStmt() {
        String program = "func int main() { while(1<2) { return 2.0 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void CorrectTypeWhileStmt() {
        String program = "func int main() { while(1<2) { return 2 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void MixedBlockWrongType() {
        String program = "func int main() { while(1<2) { if(1>2) { return 2.0 } } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void MixedBlockCorrectType() {
        String program = "func int main() { while(1<2) { if(1>2) { return 2 } } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void ReversedMixedBlockWrongType() {
        String program = "func int main() { if(1<2) { while(1>2) { return 2.0 } } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void ReversedMixedBlockCorrectType() {
        String program = "func int main() { if(1<2) { while(1>2) { return 2 } } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void SiblingBlockCorrectType() {
        String program = "func int main() { if(1<2) { return 1 } while (1>2) { return 2.0 } } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void ImplicitVoid() {
        String program = "func main() { return }";
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
        return errorHandler;
    }
}
