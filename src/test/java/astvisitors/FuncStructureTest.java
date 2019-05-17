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

public class FuncStructureTest {

    @Test
    public void correctFunctionCall() {
        String program = "func main(int a, string b) {} func Setup() {main(1, \"123\")}";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }

    @Test
    public void wrongTypeFunctionCall() {
        String program = "func main(int a, string b) {} func Setup() {main(1, 123)}";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void tooFewParametersFunctionCall() {
        String program = "func main(int a, string b) {} func Setup() {main(1)}";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void tooManyParametersFunctionCall() {
        String program = "func main(int a, string b) {} func Setup() {main(1, 1, 2)}";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void printWrongType() {
        String program = "func Setup() {Print(1321)}";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void printWrongLength() {
        String program = "func Setup() {Print(1321, 2)}";
        ErrorHandler errorHandler = parseProgram(program);
        assertTrue(errorHandler.hasErrors());
    }

    @Test
    public void printCorrectSignature() {
        String program = "func Setup() {Print(\"test\")}";
        ErrorHandler errorHandler = parseProgram(program);
        assertFalse(errorHandler.hasErrors());
    }
    
    @Test
    public void castDoubleToDouble() {
        String program = "func Setup() {double a a := Double(2.0)}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }
    
    @Test
    public void castBooleanToDouble() {
        String program = "func Setup() {double a a := Double(true)}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }
    
    @Test
    public void castStringToDouble() {
        String program = "func Setup() {double a a := Double(\"test\")}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }
    
    @Test
    public void castIntToDouble() {
        String program = "func Setup() {double a a := Double(1)}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }
    
    @Test
    public void castDoubleToString() {
        String program = "func Setup() {string a a := String(2.0)}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }
    
    @Test
    public void castBooleanToString() {
        String program = "func Setup() {string a a := String(true)}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }
    
    @Test
    public void castStringToString() {
        String program = "func Setup() {string a a := String(\"test\")}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }
    
    @Test
    public void castIntToString() {
        String program = "func Setup() {string a a := String(1)}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }
    
    @Test
    public void castDoubleToBoolean() {
        String program = "func Setup() {boolean a a := Boolean(2.0)}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }
    
    @Test
    public void castBooleanToBoolean() {
        String program = "func Setup() {boolean a a := Boolean(true)}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }
    
    @Test
    public void castStringToBoolean() {
        String program = "func Setup() {boolean a a := Boolean(\"test\")}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }
    
    @Test
    public void castIntToBoolean() {
        String program = "func Setup() {boolean a a := Boolean(1)}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }
    
    @Test
    public void castDoubleToInt() {
        String program = "func Setup() {int a a := Integer(2.0)}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }
    
    @Test
    public void castBooleanToInt() {
        String program = "func Setup() {int a a := Integer(true)}";
        ErrorHandler e = parseProgram(program);
        assertFalse(e.hasErrors());
    }
    
    @Test
    public void castStringToInt() {
        String program = "func Setup() {int a a := Integer(\"test\")}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
    }
    
    @Test
    public void castIntToInt() {
        String program = "func Setup() {int a a := Integer(1)}";
        ErrorHandler e = parseProgram(program);
        assertTrue(e.hasErrors());
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
        ast.accept(new TypeChecker(errorHandler));
        ast.accept(new FuncStructureVisitor(errorHandler));
        return errorHandler;
    }
}
