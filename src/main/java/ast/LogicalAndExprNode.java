package ast;

import ezuino.AstVisitor;

public class LogicalAndExprNode extends ExprNode {
    private LogicalAndExprNode leftNode;
    private EqualityExprNode rightNode;

    public LogicalAndExprNode(LogicalAndExprNode leftNode, EqualityExprNode rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public LogicalAndExprNode getLeftNode() {
        return leftNode;
    }

    public EqualityExprNode getRightNode() {
        return rightNode;
    }
}
