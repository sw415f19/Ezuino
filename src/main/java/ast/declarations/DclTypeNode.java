package ast.declarations;

import ast.AstNode;
import astvisitors.AstVisitor;

public class DclTypeNode extends AstNode {
    private String ID;

    public DclTypeNode(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    @Override
    public void accept(AstVisitor v) {

    }
}
