package ast.funcallstmt.cast;

import ast.expr.Func_callExprNode;
import ast.expr.aexpr.AExpr;
import astvisitors.AstVisitor;

import java.util.ArrayList;

public class DoubleCastNode extends Func_callExprNode {

    public DoubleCastNode(String ID, ArrayList<AExpr> parameters) {
        super(ID, parameters);
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

}