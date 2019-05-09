package ast.expr;

import ast.expr.aexpr.*;
import astvisitors.AstVisitor;

public class LogicalAndExprNode extends ALogicalAndExpr {
    private ALogicalAndExpr leftNode;
    private AEqualityExpr rightNode;

    public LogicalAndExprNode(ALogicalAndExpr iLogicalAndExpr, AEqualityExpr iEqualityExpr) {
        this.leftNode = iLogicalAndExpr;
        this.rightNode = iEqualityExpr;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public ALogicalAndExpr getLeftNode() {
        return leftNode;
    }

    public AEqualityExpr getRightNode() {
        return rightNode;
    }
}
