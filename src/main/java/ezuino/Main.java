package ezuino;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import lexer.EzuinoLexer;
import parser.EzuinoParser;
import javax.swing.*;
import java.util.Arrays;

public class Main
{
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

        ParseTreeWalker walker = new ParseTreeWalker();

        walker.walk(baseListener, parseTree);
        
        System.out.println("Has this many children : " + parseTree.getChildCount());

        // Starts at leaf
        if (parseTree.getChildCount() == 0) {
           System.out.println("has 0 children");             
        } // Parent before leaf
         else if (parseTree.getChildCount() == 2) {
            System.out.println("has 1 children");
            parseTree = parseTree.getChild(0);
            System.out.println("Has this many children : " + parseTree.getChildCount());
            //System.out.println(parseTree.getChild(0).getChild(0));  
        }
        // multiple parents before leaf
         else if (parseTree.getChildCount() > 1) {
            System.out.println("more than 1 ");
        }

        //System.out.println(parseTree.toStringTree(parser));


       showCST(parseTree, parser);

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
}


