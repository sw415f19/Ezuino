package ast.functions;

import java.util.ArrayList;

import ast.Func_callStmtNode;
import ast.expr.aexpr.AExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class PrintNode extends Func_callStmtNode {

    public PrintNode(String id, ArrayList<AExpr> parameters)
    {
        super(id, parameters);
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

    @Override
    public String toString()
    {
        return "motafuka";
    }

}