package ast;

import ast.expr.aexpr.AExpr;
import astvisitors.AstVisitor;

public class Assign_stmtNode extends StmtNode implements ITypeNode {
    private String id;
    private AExpr exprNode;
    private Type type;

    public Assign_stmtNode(String id, AExpr exprNode) {
        this.id = id;
        this.exprNode = exprNode;
    }

    public String getId() {
        return this.id;
    }

    public AExpr getExprNode() {
        return this.exprNode;
    }

    @Override
    public String toString() {
        return super.toString() + " { id: " + id + " type: " + type + " }";
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
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
