package ast.type;

import astvisitors.AstVisitor;

public class IdNode extends ValNode {

    public IdNode(String val) {
        this.val = val;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
