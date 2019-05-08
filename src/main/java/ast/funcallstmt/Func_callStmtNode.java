package ast.funcallstmt;

import ast.ITypeNode;
import ast.StmtNode;
import ast.Type;
import ast.expr.aexpr.AExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

import java.util.ArrayList;

public abstract class Func_callStmtNode extends StmtNode implements ITypeNode {

    protected ArrayList<AExpr> parameters = new ArrayList<AExpr>();
    private Type type;

    public ArrayList<AExpr> getParameters()
    {
        return parameters;
    }

    @Override
    public void accept(AstVisitor v)
    {
        v.visit(this);
    }

    @Override
    public void acceptLevel(AstLevelVisitor v, int level)
    {
        v.visitLevel(this, level);
    }
    public void setType(Type type) {
    	this.type = type;
    }
    public Type getType() {
    	return type;
    }

}