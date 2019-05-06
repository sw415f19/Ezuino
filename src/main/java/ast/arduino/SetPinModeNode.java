package ast.arduino;

import ast.expr.aexpr.AExpr;
import ast.funcallstmt.Func_callStmtNode;
import astvisitors.AstVisitor;

import java.util.List;

public class SetPinModeNode extends Func_callStmtNode {

    public SetPinModeNode(String id, List<AExpr> parameters) {
        super(id, parameters);
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
