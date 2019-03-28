package ast;

import ezuino.AstVisitor;

public class LogicalOrExprNode extends ExprNode {
    private LogicalOrExprNode leftNode;
    private LogicalAndExprNode rightNode;

    public LogicalOrExprNode(LogicalOrExprNode leftNode, LogicalAndExprNode rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public LogicalOrExprNode getLeftNode() {
        return leftNode;
    }

    public LogicalAndExprNode getRightNode() {
        return rightNode;
    }
}
