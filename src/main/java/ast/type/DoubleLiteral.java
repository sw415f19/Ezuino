package ast.type;

import astvisitors.AstVisitor;

public class DoubleLiteral extends ValNode {

    public DoubleLiteral(String val) {
        this.val = val;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
