package ast;

import ezuino.AstVisitor;

public class MultiplicativeExprNode extends ExprNode {
    private MultiplicativeExprNode leftNode;
    private String operator;
    private UnaryExprNode rightNode;

    public MultiplicativeExprNode(MultiplicativeExprNode leftNode, String operator, UnaryExprNode rightNode) {
        this.leftNode = leftNode;
        this.operator = operator;
        this.rightNode = rightNode;
    }

    @Override
    public void accept(AstVisitor v) {
        super.accept(v);
    }

    public MultiplicativeExprNode getLeftNode() {
        return leftNode;
    }

    public String getOperator() {
        return operator;
    }

    public UnaryExprNode getRightNode() {
        return rightNode;
    }
}
