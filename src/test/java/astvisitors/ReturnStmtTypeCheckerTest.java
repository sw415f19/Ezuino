package astvisitors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

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
    public void wrongReturnType() {
        String program = "func int main() { return 2.0 }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void correctReturnType() {
        String program = "func int main() { return 2 }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void wrongNestedIfReturnType() {
        String program = "func int main() { if(1<2) { return 2.0 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void wrongNestedElseReturnType() {
        String program = "func int main() { if(1<2) { } else { return 2.0 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void wrongNestedElseOtherScopeReturnType() {
        String program = "func int main() { if(1<2) { if (2<2) { } } else { return 2.0 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void correctNestedIfReturnType() {
        String program = "func int main() { if(1<2) { return 2 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void correctNestedElseReturnType() {
        String program = "func int main() { if(1<2) { } else { return 2 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void correctNestedElseOtherScopeReturnType() {
        String program = "func int main() { if(1<2) { if (2<2) { } } else { return 2 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void wrongTypeWhileStmt() {
        String program = "func int main() { while(1<2) { return 2.0 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void correctTypeWhileStmt() {
        String program = "func int main() { while(1<2) { return 2 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void mixedBlockWrongType() {
        String program = "func int main() { while(1<2) { if(1>2) { return 2.0 } } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void mixedBlockCorrectType() {
        String program = "func int main() { while(1<2) { if(1>2) { return 2 } } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void reversedMixedBlockWrongType() {
        String program = "func int main() { if(1<2) { while(1>2) { return 2.0 } } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void reversedMixedBlockCorrectType() {
        String program = "func int main() { if(1<2) { while(1>2) { return 2 } } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void siblingBlockWrongType() {
        String program = "func int main() { if(1<2) { return 1 } while (1>2) { return 2.0 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void siblingBlockCorrectType() {
        String program = "func int main() { if(1<2) { return 1 } while (1>2) { return 2 } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void implicitVoid() {
        String program = "func main() { return }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void implicitInIfVoid() {
        String program = "func main() { if(1<2) { return } }";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void whileTestTypeNotTheSame() throws IOException {
        String testProgram = "func int main() { while(1<2){ return \"hi\" } return \"sup\"}";
        ErrorHandler e = parseProgram(testProgram);
        assertTrue(e.hasErrors());
    }

    @Test
    public void returnTwoDifferentTypesError() throws IOException {
        String testProgram = "func int hello(){ if(true){ return 3.4 } else { return 4 }}";
        ErrorHandler e = parseProgram(testProgram);
        assertTrue(e.hasErrors());
    }

    @Test
    public void returnAsBreakReturnTypeError() throws IOException {
        String testProgram = "func hello(){ if(true){ return 1 } return }";
        ErrorHandler e = parseProgram(testProgram);
        assertTrue(e.hasErrors());
    }

    @Test
    public void whileTest() throws IOException {
        String testProgram = "func int main(){ while(1<2) { return 1 } return 1 }";
        ErrorHandler e = parseProgram(testProgram);
        assertFalse(e.hasErrors());
    }

    @Test
    public void whileTestVoidFunc() throws IOException {
        String testProgram = "func main(){ while(1<2){ int a } }";
        ErrorHandler e = parseProgram(testProgram);
        assertFalse(e.hasErrors());
    }

    @Test
    public void ifStmtAfterWhile() throws IOException {
        String testProgram = "func int main(){ while(1<2){ } if(1>2) { } return 1 }";
        ErrorHandler e = parseProgram(testProgram);
        assertFalse(e.hasErrors());
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
