package ast;

import ezuino.AstVisitor;

public class ParenthesisExprNode extends ExprNode {
    private ExprNode node;

    public ParenthesisExprNode(ExprNode node) {
        this.node = node;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public ExprNode getNode() {
        return node;
    }
}
