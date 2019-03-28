package ast;

import ezuino.AstVisitor;

public class AdditiveExprNode extends ExprNode {
    private AdditiveExprNode leftNode;
    private String operator;
    private MultiplicativeExprNode rightNode;

    public AdditiveExprNode(AdditiveExprNode leftNode, String operator, MultiplicativeExprNode rightNode) {
        this.leftNode = leftNode;
        this.operator = operator;
        this.rightNode = rightNode;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public AdditiveExprNode getLeftNode() {
        return leftNode;
    }

    public String getOperator() {
        return operator;
    }

    public MultiplicativeExprNode getRightNode() {
        return rightNode;
    }
}
