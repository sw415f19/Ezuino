package ast;

import ezuino.AstVisitor;

public class Int_dclNode extends SymDeclaring {
    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public Int_dclNode(String i) {
        id = i;
    }
}
