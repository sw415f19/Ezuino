import org.antlr.runtime.tree.TreeWizard.Visitor;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import javax.swing.*;
import java.util.Arrays;
import java.util.Vector;

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
        EzuinoBaseVisitor baseVisitor = new EzuinoBaseVisitor();

        baseVisitor.visit(parseTree);

        ParseTreeWalker walker = new ParseTreeWalker();

        walker.walk(baseListener, parseTree);

        System.out.println(parseTree.toStringTree(parser));

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
            System.out.println(caption);
          }
    }
}


