package ast;

import java.util.Iterator;
import java.util.List;
import astvisitors.AstVisitor;

public class Func_defNode extends StmtNode implements ITypeNode {

    private List<DclNode> parameters;
    private BlockNode blockNode;
    private String ID;
    private Type type;

    public Func_defNode(String ID, Type type, List<DclNode> parameters, BlockNode blockNode) {
        this.ID = ID;
        this.type = type;
        this.parameters = parameters;
        this.blockNode = blockNode;
    }

    public String getId() {
        return ID;
    }

    public List<DclNode> getParameters() {
        return parameters;
    }

    public Iterator<DclNode> getParameterIterator() {
        return parameters.iterator();
    }

    public BlockNode getBlockNode() {
        return blockNode;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public String toString() {
        return "Func_defNode{" +
                "ID: \"" + ID + "\" " +
                "type: " + type + " " +
                '}';
    }

    @Override
    public void setType(Type type) {
        this.type = type;

    }

    @Override
    public Type getType() {
        return this.type;
    }
}
