package ast.expr;

import ast.expr.aexpr.*;
import astvisitors.AstVisitor;

public class UnaryExprNode extends AMultiplicativeExpr {
    private String operator;
    private AParenthesisExpr node;

    public UnaryExprNode(String operator, AParenthesisExpr parenthesisExprNode) {
        this.operator = operator;
        this.node = parenthesisExprNode;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public String getOperator() {
        return operator;
    }

    public AParenthesisExpr getNode() {
        return node;
    }

    @Override
    public String toString() {
        return super.toString() + "{ operator: " + operator + " type: " + type + " }";
    }
}
