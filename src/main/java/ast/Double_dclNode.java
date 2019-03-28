package ast;

import ezuino.AstVisitor;

public class Double_dclNode extends SymDeclaring {
    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public Double_dclNode(String i) {
        id = i;
    }
}
