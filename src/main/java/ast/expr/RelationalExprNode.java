package ast.expr;

import ast.expr.aexpr.*;
import astvisitors.AstVisitor;

public class RelationalExprNode extends ARelationalExpr {
    private ARelationalExpr leftNode;
    private String operator;
    private AAddictiveExpr rightNode;

    public RelationalExprNode(ARelationalExpr relationalExprNode, String operator, AAddictiveExpr additiveExprNode) {
        this.leftNode = relationalExprNode;
        this.operator = operator;
        this.rightNode = additiveExprNode;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public ARelationalExpr getLeftNode() {
        return leftNode;
    }

    public String getOperator() {
        return operator;
    }

    public AAddictiveExpr getRightNode() {
        return rightNode;
    }

    @Override
    public String toString() {
        return super.toString() + "{ operator: " + operator + " type: " + type + " }";
    }
}
