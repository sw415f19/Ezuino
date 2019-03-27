package ast;

import ezuino.AstVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

public class Assign_exprNode extends AstNode {
    private String id;
    private ParseTree child;

    //Warning : Pure copy pasta
    public Assign_exprNode(String id, ParseTree child) {
        this.id = id;
        this.child = child;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);

    }
}