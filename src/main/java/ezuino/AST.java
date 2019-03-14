package ezuino;

/*
 * Copyright (c) 2014 by Bart Kiers
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import lexer.EzuinoLexer;
import parser.EzuinoParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AST {

    /**
     * The payload will either be the name of the parser rule, or the token of a
     * leaf in the tree.
     */
    private final Object payload;

    /**
     * All child nodes of this AST.
     */
    private final List<AST> children;

    public AST(ParseTree tree) {
        this(null, tree);
    }

    private AST(AST ast, ParseTree tree) {
        this(ast, tree, new ArrayList<AST>());
    }

    private AST(AST parent, ParseTree tree, List<AST> children) {

        this.payload = getPayload(tree);
        this.children = children;

        if (parent == null) {
            // We're at the root of the AST, traverse down the parse tree to fill
            // this AST with nodes.
            walk(tree, this);
        } else {
            parent.children.add(this);
        }
    }

    public Object getPayload() {
        return payload;
    }

    public List<AST> getChildren() {
        return new ArrayList<AST>(children);
    }

    // Determines the payload of this AST: a string in case it's an inner node
    // (which
    // is the name of the parser rule), or a Token in case it is a leaf node.
    private Object getPayload(ParseTree tree) {
        if (tree.getChildCount() == 0) {
            // A leaf node: return the tree's payload, which is a Token.
            return tree.getPayload();
        } else {
            // The name for parser rule `foo` will be `FooContext`. Strip `Context` and
            // lower case the first character.
            String ruleName = tree.getClass().getSimpleName().replace("Context", "");
            return Character.toLowerCase(ruleName.charAt(0)) + ruleName.substring(1);
        }
    }

    // Fills this AST based on the parse tree.
    private static void walk(ParseTree tree, AST ast) {

        if (tree.getChildCount() == 0) {
            // We've reached a leaf. We must create a new instance of an AST because
            // the constructor will make sure this new instance is added to its parent's
            // child nodes.
            new AST(ast, tree);
        } else if (tree.getChildCount() == 1) {
            // We've reached an inner node with a single child: we don't include this in
            // our AST.
            walk(tree.getChild(0), ast);
        } else if (tree.getChildCount() > 1) {

            for (int i = 0; i < tree.getChildCount(); i++) {

                AST temp = new AST(ast, tree.getChild(i));

                if (!(temp.payload instanceof Token)) {
                    // Only traverse down if the payload is not a Token.
                    walk(tree.getChild(i), temp);
                }
            }
        }
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        AST ast = this;
        List<AST> firstStack = new ArrayList<AST>();
        firstStack.add(ast);

        List<List<AST>> childListStack = new ArrayList<List<AST>>();
        childListStack.add(firstStack);

        while (!childListStack.isEmpty()) {

            List<AST> childStack = childListStack.get(childListStack.size() - 1);

            if (childStack.isEmpty()) {
                childListStack.remove(childListStack.size() - 1);
            } else {
                ast = childStack.remove(0);
                String caption;

                if (ast.payload instanceof Token) {
                    Token token = (Token) ast.payload;
                   // caption = String.format("TOKEN[type: %s, text: %s]", token.getType(),
                   caption = String.format("%s;%s \n", token.getType(),
                            token.getText().replace("\n", "\\n"));
                } else {
                    caption = "";
                }

                String indent = "";
/** 
                for (int i = 0; i < childListStack.size() - 1; i++) {
                    indent += (childListStack.get(i).size() > 0) ? "|  " : "   ";
                }
**/
               // builder.append(indent).append(childStack.isEmpty() ? "'- " : "|- ").append(caption).append("\n");
               builder.append(caption);
               if (ast.children.size() > 0) {
                    List<AST> children = new ArrayList<AST>();
                    for (int i = 0; i < ast.children.size(); i++) {
                        children.add(ast.children.get(i));
                    }
                    childListStack.add(children);
                }
            }
        }

        return builder.toString();
    }

    public static void main(String[] args) {

        // Generate the parser and lexer classes below using the grammar available here:
        // https://github.com/bkiers/python3-parser
       
       /** CharStream cs = CharStreams
                .fromString("int a " 
                + "int b " 
                + "a := 1 " 
                + "func asd(int a) {" 
                + "print(a)" 
                + "return 42"
                + "}");
        */

        CharStream cs = CharStreams
                .fromString("int a " 
                + "string b " 
                + "a := 12124 + 123 "
                + "b := \"Hello worls\"" 
                + "print a+b"
                );

        EzuinoLexer lLexer = new EzuinoLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lLexer);

        EzuinoParser parser = new EzuinoParser(tokens);

        ParseTree parseTree = parser.start();
        EzuinoBaseListener baseListener = new EzuinoBaseListener();

        AST ast = new AST(parseTree);
        System.out.println(ast);

        List<AST> arrayList = ast.getChildren();

      //   showCST(parseTree, parser);

        System.out.println("ran");

    }

    private static void showCST(ParseTree parseTree, EzuinoParser parser) {
        JFrame frame = new JFrame("CST Generated");
        JPanel panel = new JPanel();
        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), parseTree);
        viewer.setScale(1.5);// scale a little
        panel.add(viewer);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }
}