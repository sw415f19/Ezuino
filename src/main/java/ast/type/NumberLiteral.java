package ast.type;

import astvisitors.AstVisitor;

public class NumberLiteral extends ValNode {

    public NumberLiteral(String val) {
        this.val = val;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
