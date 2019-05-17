package ast.type;

import astvisitors.AstVisitor;

public class FloatLiteral extends ValNode {

    public FloatLiteral(String val) {
        this.val = val;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
