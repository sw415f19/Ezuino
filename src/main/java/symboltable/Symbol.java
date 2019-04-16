package symboltable;

import ast.ITypeNode;

public class Symbol {

    String id;
    int count;
    ITypeNode typeNode;

    public Symbol(String id, int count, ITypeNode typeNode) {
        this.id = id;
        this.count = count;
        this.typeNode = typeNode;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ITypeNode getTypeNode() {
        return this.typeNode;
    }

    public void setTypeNode(ITypeNode typeNode) {
        this.typeNode = typeNode;
    }

    public Symbol id(String id) {
        this.id = id;
        return this;
    }

    public Symbol count(int count) {
        this.count = count;
        return this;
    }

    public Symbol typeNode(ITypeNode typeNode) {
        this.typeNode = typeNode;
        return this;
    }


}