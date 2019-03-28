package ast;

import ezuino.AstVisitor;

public class Boolean_dclNode extends SymDeclaring {
    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public Boolean_dclNode(String i) {
        id = i;
    }

    public String getId(){
        return id;
    }
}