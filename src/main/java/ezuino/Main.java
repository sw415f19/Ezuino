package ezuino;

import ast.*;
import astvisitors.IndentedPrintVisitor;
import generated.EzuinoLexer;
import generated.EzuinoParser;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import cstvisitors.*;

public class Main {
    public static ArrayList<String> numbers = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        CharStream cs = CharStreams.fromFileName("C:\\Users\\d3adl\\OneDrive\\Dokumenter\\example.txt");
        /*
        fromString(
            "int a "
                    + "func int print(int a, double b, int a) {int a}"
            );
         */

        EzuinoLexer lLexer = new EzuinoLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lLexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        ParseTree parseTree = parser.start();
        CSTPrinter cstp = new CSTPrinter();
        cstp.visit(parseTree);

        //showCST(parseTree, parser);

        /* Call of IndentedPrintVisitor
        BuildAstVisitor ezuinoVisitorForPrinting = new BuildAstVisitor();
        StartNode astNode = (StartNode) ezuinoVisitorForPrinting.visit(parseTree);
        IndentedPrintVisitor ipv = new IndentedPrintVisitor(); // Gives nullPointerException atm.
        ipv.visit(astNode);
        */

        //Initializes the Ezuiono Vistor
        BuildAstVisitor buildAstVisitor = new BuildAstVisitor();
        //Runs the three, filling up the AST array list attribute
        AstNode astNode = parseTree.accept(buildAstVisitor);

        IndentedPrintVisitor ipv = new IndentedPrintVisitor();
        astNode.acceptLevel(ipv, 0);


    }

    private static void showCST(ParseTree parseTree, EzuinoParser parser) {
        JFrame frame = new JFrame("CST Generated");
        JPanel panel = new JPanel();
        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), parseTree);
        viewer.setScale(0.6);// scale a little
        panel.add(viewer);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }

    /*
     * Legacy code that splits all lines given to the parser into tokens
     */
    private static void getTokenFromNode(ParseTree parseTree) {
        if (parseTree.getPayload() instanceof Token) {
            Token token = (Token) parseTree.getPayload();
            String caption = String.format("Token id : %s with value : %s", token.getType(),
                    token.getText().replace("\n", "\\n"));
            // System.out.println(caption);

            if (token.getType() == 42) {
                numbers.add(token.getText());
                System.out.println(token.getCharPositionInLine());
                System.out.println("Source" + token.getTokenSource().getCharPositionInLine());
            }
            // System.out.println(numbers.size());
        }
    }
}
