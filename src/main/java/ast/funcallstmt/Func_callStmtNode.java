package ast.funcallstmt;

import ast.StmtNode;
import ast.expr.aexpr.AExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

import java.util.ArrayList;

public abstract class Func_callStmtNode extends StmtNode {

    protected ArrayList<AExpr> parameters = new ArrayList<AExpr>();

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

}