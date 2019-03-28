package ezuino;

import ast.AstNode;
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
        CSTPrinter cstp = new CSTPrinter();
        cstp.visit(parseTree);


        /* Call of IndentedPrintVisitor
        BuildAstVisitor ezuinoVisitorForPrinting = new BuildAstVisitor();
        StartNode astNode = (StartNode) ezuinoVisitorForPrinting.visit(parseTree);
        IndentedPrintVisitor ipv = new IndentedPrintVisitor(); // Gives nullPointerException atm.
        ipv.visit(astNode);
        */

        //Initializes the Ezuiono Vistor
        BuildAstVisitor buildAstVisitor = new BuildAstVisitor();
        //Runs the three, filling up the AST array list attribute
        buildAstVisitor.visit(parseTree);
        System.out.println("Number of nodes in AST: " + buildAstVisitor.getAst().size());
        // Creates a AST startNode object that contains the AST Array
        AstNode ast = new StartNode(buildAstVisitor.getAst());
        //Use accept on the startNode. startNode iterates through the AST array using accept on each entry.
        //accept() does what is defined in the input class (ex. Prettyprinting), on each object depending on the object type.
        ast.accept(new Prettyprinting());
        showCST(parseTree, parser);


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
