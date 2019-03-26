package ast;

import ezuino.AstVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

public class Assign_booleanNode extends AstNode {
    private String id;
    private AstNode child;

    public Assign_booleanNode(String id, AstNode child) {
        this.id = id;
        this.child = child;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);

    }
}