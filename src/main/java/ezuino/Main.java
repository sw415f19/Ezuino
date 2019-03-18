package ezuino;
import org.antlr.runtime.tree.TreeWizard.Visitor;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import lexer.EzuinoLexer;
import parser.EzuinoParser;
import javax.swing.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class Main
{
    public static ArrayList<String> numbers = new ArrayList<String>();
    public static void main(String[] args)
    {
        CharStream cs = CharStreams.fromString(
                        "int a " +
                        "int b " +
                        "a := 5 " +
                        "b := a + 3.2" +
                        "print(b)");

        EzuinoLexer lLexer = new EzuinoLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lLexer);

        EzuinoParser parser = new EzuinoParser(tokens);

        ParseTree parseTree = parser.start();
        EzuinoBaseListener baseListener = new EzuinoBaseListener();
        EzuinoBaseVisitor baseVisitor = new EzuinoBaseVisitor();

        baseVisitor.visit(parseTree);

        ParseTreeWalker walker = new ParseTreeWalker();

        walker.walk(baseListener, parseTree);

        System.out.println(parseTree.toStringTree(parser));

       SymbolTable symbolTable = new SymbolTable();
       symbolTable.addSymbol(1, "x", "5");
       symbolTable.addSymbol(2, "y", "3");
       symbolTable.addSymbol(10, "qwe", "fuck mig");

       symbolTable.removeSymbol(10, "qwe", "fuck mig");
       symbolTable.removeSymbol(1, "x", "5");
       symbolTable.removeSymbol(2, "y", "3");
       symbolTable.removeSymbol(3, "y", "3");
       

    }

    private static void showCST(ParseTree parseTree, EzuinoParser parser)
    {
        JFrame frame = new JFrame("CST Generated");
        JPanel panel = new JPanel();
        TreeViewer viewer = new TreeViewer(Arrays.asList(
                parser.getRuleNames()),parseTree);
        viewer.setScale(1.5);//scale a little
        panel.add(viewer);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,200);
        frame.setVisible(true);
    }

    private static void PostOrderTraverse(ParseTree parseTree)
    {
       for (int i = 0; i < parseTree.getChildCount(); i++) {
         PostOrderTraverse(parseTree.getChild(i));  
       }    
       getTokenFromNode(parseTree);
    }

    private static void getTokenFromNode(ParseTree parseTree){
        if (parseTree.getPayload() instanceof Token) {
            Token token = (Token) parseTree.getPayload();
            String caption = String.format("Token id : %s with value : %s", token.getType(),
            token.getText().replace("\n", "\\n"));
           // System.out.println(caption);
            
            if (token.getType() == 42){
                numbers.add(token.getText());
                System.out.println(token.getCharPositionInLine());
                System.out.println("Source" + token.getTokenSource().getCharPositionInLine());
            }
           // System.out.println(numbers.size());
            if (numbers.size()==2) {
            AddictiveNode addictiveNode = new AddictiveNode(Integer.parseInt(numbers.get(0)), Integer.parseInt(numbers.get(1)));
            System.out.println(addictiveNode.toString());
         }
          }
    }
}


