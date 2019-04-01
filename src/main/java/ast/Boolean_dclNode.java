package ast;

import astvisitors.AstVisitor;

public class Boolean_dclNode extends AstNode {

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}