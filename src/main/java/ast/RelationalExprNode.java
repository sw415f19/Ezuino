package ast;

import ezuino.AstVisitor;

public class RelationalExprNode extends ExprNode {
    private RelationalExprNode leftNode;
    private String operator;
    private AdditiveExprNode rightNode;

    public RelationalExprNode(RelationalExprNode leftNode, String operator, AdditiveExprNode rightNode) {
        this.leftNode = leftNode;
        this.operator = operator;
        this.rightNode = rightNode;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public RelationalExprNode getLeftNode() {
        return leftNode;
    }

    public String getOperator() {
        return operator;
    }

    public AdditiveExprNode getRightNode() {
        return rightNode;
    }
}
