import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

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
                        "b := a + 3.2 " +
                        "priant(b)");

        ezuinoLexer lLexer = new ezuinoLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lLexer);

        ezuinoParser parser = new ezuinoParser(tokens);

        ParseTree parseTree = parser.start();
        ezuinoBaseListener baseListener = new ezuinoBaseListener();

        ParseTreeWalker walker = new ParseTreeWalker();

        walker.walk(baseListener, parseTree);

        System.out.println(parseTree.toStringTree(parser));
        JFrame frame = new JFrame("Antlr AST");
        JPanel panel = new JPanel();
        TreeViewer viewr = new TreeViewer(Arrays.asList(
                parser.getRuleNames()),parseTree);
        viewr.setScale(1.5);//scale a little
        panel.add(viewr);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,200);
        frame.setVisible(true);
    }
}
