package ast.funcallstmt.cast;

import ast.expr.aexpr.AExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

import java.util.ArrayList;

public class DoubleCastNode extends IntegerCastNode {

    public DoubleCastNode(String ID, ArrayList<AExpr> parameters) {
        super(ID, parameters);
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