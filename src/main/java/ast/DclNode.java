package ast;

import astvisitors.AstVisitor;

public class DclNode extends TypeNode {
    private String ID;

    public DclNode(Type type, String ID) {
        this.type = type;
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);

    }

    @Override
    public String toString() {
        return super.toString() + "{ ID: " + ID + " type: " + type + " }";
    }
}
