package ast;

import ezuino.AstVisitor;

public class Double_dclNode extends AstNode {

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
