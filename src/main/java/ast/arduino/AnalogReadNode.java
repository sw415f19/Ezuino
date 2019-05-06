package ast.arduino;

import ast.expr.Func_callExprNode;
import ast.expr.aexpr.AExpr;
import ast.funcallstmt.Func_callStmtNode;
import astvisitors.AstVisitor;

import java.util.ArrayList;
import java.util.List;

public class AnalogReadNode extends Func_callExprNode {

    public AnalogReadNode(String ID, List<AExpr> parameters) {
        super(ID, parameters);
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
