package ast.funcallstmt;

import java.util.ArrayList;

import ast.expr.aexpr.AExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class ListAddNode extends Func_callStmtNode
{
    public ListAddNode(ArrayList<AExpr> parameters)
    {
        this.parameters = parameters;
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
