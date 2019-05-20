package ast.expr.cast;

import java.util.List;

import ast.expr.aexpr.AExpr;
import astvisitors.AstVisitor;

public class StringCastNode extends CastNode {

    public StringCastNode(String ID, List<AExpr> parameters) {
        super(ID, parameters);
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

}
