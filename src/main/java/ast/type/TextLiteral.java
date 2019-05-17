package ast.type;

import astvisitors.AstVisitor;

public class TextLiteral extends ValNode {

    public TextLiteral(String val) {
        this.val = val;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
