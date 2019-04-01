package ast.declarations;

import ast.AstNode;
import astvisitors.AstVisitor;

public class String_dclNode extends DclTypeNode {

    public String_dclNode(String ID) {
        super(ID);
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
