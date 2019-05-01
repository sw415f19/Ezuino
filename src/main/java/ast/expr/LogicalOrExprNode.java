package ast.expr;

import ast.expr.aexpr.*;
import astvisitors.AstVisitor;

public class LogicalOrExprNode extends AlogicalOrExpr {
    private AlogicalOrExpr leftNode;
    private ALogicalAndExpr rightNode;

    public LogicalOrExprNode(AlogicalOrExpr ilogicalOrExpr, ALogicalAndExpr iLogicalAndExpr) {
        this.leftNode = ilogicalOrExpr;
        this.rightNode = iLogicalAndExpr;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public AlogicalOrExpr getLeftNode() {
        return leftNode;
    }

    public ALogicalAndExpr getRightNode() {
        return rightNode;
    }

    @Override
    public String toString() {
        return super.toString() + " { type: " + type + " }";
    }
}
