package ezuino;

import ast.AstNode;
import astvisitors.*;
import cstvisitors.BuildAstVisitor;
import cstvisitors.CSTPrinter;
import exceptions.ErrorHandler;
import exceptions.ErrorListener;
import generated.EzuinoLexer;
import generated.EzuinoParser;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static ArrayList<String> numbers = new ArrayList<String>();

    public static void main(String[] args) throws IOException {

        CharStream cs = CharStreams.fromFileName("src/main/code.ezuino");

        ErrorHandler errorHandler = new ErrorHandler();
        AstNode ast = syntaxAnalysis(cs, errorHandler);
        if (errorHandler.hasErrors()) {
            errorHandler.printErrors("Syntax errors");
            return;
        }
        contextualAnalysis(ast, errorHandler);
        if (errorHandler.hasErrors()) {
            errorHandler.printErrors("Semantic errors");
            return;
        }
        codeGeneration(ast);
    }

    private static AstNode syntaxAnalysis(CharStream charStream, ErrorHandler errorHandler) {
        ErrorListener errorListener = new ErrorListener(errorHandler);
        EzuinoLexer lLexer = new EzuinoLexer(charStream);
        lLexer.removeErrorListeners();
        lLexer.addErrorListener(errorListener);

        CommonTokenStream tokens = new CommonTokenStream(lLexer);

        EzuinoParser parser = new EzuinoParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        ParseTree parseTree = parser.start();

        if (parseTree.getChildCount() == 0) {
            errorHandler.invalidKeyword();
            return null;
        }

        CSTPrinter cstp = new CSTPrinter();
        cstp.visit(parseTree);

        BuildAstVisitor buildAstVisitor = new BuildAstVisitor();
        return parseTree.accept(buildAstVisitor);

    }

    private static void contextualAnalysis(AstNode ast, ErrorHandler errorHandler) {

        boolean printDcl = true;
        boolean hasNoError = !errorHandler.hasErrors();

        ast.accept(new IndentedPrintVisitor());

        if(hasNoError) {
            ast.accept(new ReservedKeywordsVisitor(errorHandler));
            ast.accept(new SymbolTableVisitor(printDcl, errorHandler));
            ast.accept(new IndentedPrintVisitor());
        }
        hasNoError = !errorHandler.hasErrors();
        if(hasNoError) {
            ast.accept(new TypeChecker(errorHandler));
            ast.accept(new IndentedPrintVisitor());
            ast.accept(new ReturnStmtTypeCheckVisitor(errorHandler));
            ast.accept(new MissingReturnStmtVisitor(errorHandler));
            ast.accept(new FuncStructureVisitor(errorHandler));
        }
    }

    private static void codeGeneration(AstNode ast) {

        ast.accept(new ArduinoCodeGenerationVisitor(System.out));
    }

    private static void showCST(ParseTree parseTree, EzuinoParser parser) {
        JFrame frame = new JFrame("CST Generated");
        JPanel panel = new JPanel();
        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), parseTree);
        viewer.setScale(1);// scale a little
        panel.add(viewer);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }
}
