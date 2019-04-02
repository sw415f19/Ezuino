package ast.declarations;

import ast.AstNode;
import astvisitors.AstVisitor;

public class Double_dclNode extends DclTypeNode {

    public Double_dclNode(String ID) {
        super(ID);
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
