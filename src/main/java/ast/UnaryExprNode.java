package ast;

import ezuino.AstVisitor;

public class UnaryExprNode extends ExprNode {
    private String operator;
    private ParenthesisExprNode node;

    public UnaryExprNode(String operator, ParenthesisExprNode node) {
        this.operator = operator;
        this.node = node;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public String getOperator() {
        return operator;
    }

    public ParenthesisExprNode getNode() {
        return node;
    }
}
