package ast;

import ast.expr.aexpr.AExpr;
import astvisitors.AstVisitor;

public class Return_stmtNode extends TypeNode {

    private AExpr returnExpr;

    public Return_stmtNode(AExpr returnExpr) {
        this.returnExpr = returnExpr;
    }

    public AExpr getReturnExpr() {
        return returnExpr;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);

    }

    @Override
    public String toString() {
        return super.toString() + " { type: " + type + " }";
    }
}
