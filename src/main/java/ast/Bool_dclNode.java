package ast;

import ezuino.AstVisitor;

public class Bool_dclNode extends SymDeclaring {
    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public Bool_dclNode(String i) {
        id = i;
    }
}
