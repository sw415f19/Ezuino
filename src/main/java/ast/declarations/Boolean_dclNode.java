package ast.declarations;

import ast.AstNode;
import astvisitors.AstVisitor;

public class Boolean_dclNode extends DclTypeNode {

    public Boolean_dclNode(String ID) {
        super(ID);
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}