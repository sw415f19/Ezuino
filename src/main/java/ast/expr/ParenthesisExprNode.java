package ast.expr;

import ast.expr.aexpr.AExpr;
import ast.expr.aexpr.AParenthesisExpr;
import astvisitors.AstVisitor;

public class ParenthesisExprNode extends AParenthesisExpr {
    private AExpr node;

    public ParenthesisExprNode(AExpr iExpr) {
        this.node = iExpr;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public AExpr getNode() {
        return this.node;
    }

    @Override
    public String toString() {
        return super.toString() + " { type: " + type + " }";
    }
}
