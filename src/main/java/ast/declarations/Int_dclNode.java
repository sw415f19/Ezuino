package ast.declarations;

import ast.AstNode;
import astvisitors.AstVisitor;

public class Int_dclNode extends DclTypeNode {

    public Int_dclNode(String ID) {
        super(ID);
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
