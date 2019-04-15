package ezuino;

import ast.AstNode;
import astvisitors.IndentedPrintVisitor;
import astvisitors.SymbolTableVisitor;
import astvisitors.Typechecker;
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

        ErrorListener errorListener = new ErrorListener();

        EzuinoLexer lLexer = new EzuinoLexer(cs);
        lLexer.removeErrorListeners();
        lLexer.addErrorListener(errorListener);

        CommonTokenStream tokens = new CommonTokenStream(lLexer);

        EzuinoParser parser = new EzuinoParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        ParseTree parseTree = parser.start();
        
        if (errorListener.hasError())
        {
            System.err.println("## Scanner/Paser Error - Please correct the following errors and try again. ##");
            errorListener.printErrors();
            return;
        }
        
        CSTPrinter cstp = new CSTPrinter();
        cstp.visit(parseTree);

        //showCST(parseTree, parser);

        /*
         * Call of IndentedPrintVisitor BuildAstVisitor ezuinoVisitorForPrinting = new
         * BuildAstVisitor(); StartNode astNode = (StartNode)
         * ezuinoVisitorForPrinting.visit(parseTree); IndentedPrintVisitor ipv = new
         * IndentedPrintVisitor(); // Gives nullPointerException atm.
         * ipv.visit(astNode);
         */

        // Initializes the Ezuiono Vistor
        BuildAstVisitor buildAstVisitor = new BuildAstVisitor();
        // Runs the three, filling up the AST array list attribute
        AstNode astNode = parseTree.accept(buildAstVisitor);

        IndentedPrintVisitor ipv = new IndentedPrintVisitor();
        astNode.acceptLevel(ipv, 0);

        SymbolTableVisitor symbolTableFillingVisitor = new SymbolTableVisitor(true);
        astNode.accept(symbolTableFillingVisitor);
        astNode.acceptLevel(ipv, 0);
        Typechecker tc = new Typechecker();
        astNode.accept(tc);
        astNode.acceptLevel(ipv, 0);
        //System.out.println(SymbolTableVisitor.symbolTableManager.getSymbolTableSize());

        ErrorHandler.printErrorList();
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
