package ezuino;

import ast.StartNode;
import generated.EzuinoLexer;
import generated.EzuinoParser;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static ArrayList<String> numbers = new ArrayList<String>();

    public static void main(String[] args) {
        CharStream cs = CharStreams.fromString( "int a " + "a := TRUE AND TRUE");

        EzuinoLexer lLexer = new EzuinoLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lLexer);
        EzuinoParser parser = new EzuinoParser(tokens);
        ParseTree parseTree = parser.start();
        // showCST(parseTree, parser);
        CSTPrinter cstp = new CSTPrinter();
        cstp.visit(parseTree);

        SymbolTable symbolTable = new SymbolTable();
        symbolTable.addSymbol(1, "x", "5");
        symbolTable.addSymbol(2, "y", "3");
        symbolTable.addSymbol(10, "qwe", "fuck mig");
        symbolTable.addSymbol(10, "qwe", "fuck me 2");
        symbolTable.getSymbolTable();

        symbolTable.removeSymbol(10, "qwe", "fuck me 2");
        symbolTable.removeSymbol(1, "x", "5");
        symbolTable.removeSymbol(2, "y", "3");
        symbolTable.removeSymbol(3, "y", "3");

        BuildAstVisitor buildAstVisitor = new BuildAstVisitor();
        StartNode astNode = (StartNode) buildAstVisitor.visit(parseTree);
        showCST(parseTree, parser);
        //System.out.println(astNode.getDcls().getChildList().get(0).toString());
        
        IndentedPrintVisitor ipv = new IndentedPrintVisitor();
        ipv.visit(astNode);
    }

    private static void showCST(ParseTree parseTree, EzuinoParser parser) {
        JFrame frame = new JFrame("CST Generated");
        JPanel panel = new JPanel();
        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), parseTree);
        viewer.setScale(1.0);// scale a little
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
