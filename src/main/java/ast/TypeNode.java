package ast;


public abstract class TypeNode extends AstNode implements ITypeNode {

    protected Type type;

    public void setType(Type newType) {
        this.type = newType;
    }

    public Type getType() {
        return this.type;
    }
}
