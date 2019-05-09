package ast.type;

import astvisitors.AstVisitor;

public class IntegerLiteral extends ValNode {

    public IntegerLiteral(String val) {
        this.val = val;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
