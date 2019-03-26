package ast;

import ezuino.AstVisitor;

public class String_dclNode extends SymDeclaring {
    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public String_dclNode(String i) {
        id = i;
    }


}
