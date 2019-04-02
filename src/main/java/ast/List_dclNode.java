package ast;

import ast.type.IntegerNode;
import astvisitors.AstVisitor;

public class List_dclNode extends DclNode {
    private String length;

    public List_dclNode(Type type, String ID, String length) {
        super(type, ID);
        this.length = length;
        System.out.println("Type " + type);
        System.out.println("ID " + ID);
        System.out.println("Length " + length);
    }

    @Override
    public Type getType() {
        return super.getType();
    }

    @Override
    public String getID() {
        return super.getID();
    }

    public String getLength() {
        return length;
    }

    @Override
    public void accept(AstVisitor v) {

    }
}
