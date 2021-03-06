package ast.expr.cast;

import ast.expr.aexpr.AExpr;
import astvisitors.AstVisitor;

import java.util.List;

public class IntegerCastNode extends CastNode {

    public IntegerCastNode(String ID, List<AExpr> parameters) {
        super(ID, parameters);
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

}