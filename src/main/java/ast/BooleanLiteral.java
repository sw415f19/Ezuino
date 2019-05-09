package ast;

import ast.expr.PrimaryExprNode;
import astvisitors.AstVisitor;

public class BooleanLiteral extends PrimaryExprNode {

    private String boolval;

    public BooleanLiteral(String boolval) {
        this.boolval = boolval;
    }

    public String getBoolval() {
        return this.boolval;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);

    }

    @Override
    public String toString() {
        return super.toString() + " { boolval: " + boolval + " type: " + type + " }";
    }
}
