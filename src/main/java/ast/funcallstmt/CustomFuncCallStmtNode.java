package ast.funcallstmt;

import ast.expr.aexpr.AExpr;
import astvisitors.AstVisitor;

import java.util.List;

public class CustomFuncCallStmtNode extends Func_callStmtNode {

    public CustomFuncCallStmtNode(String id, List<AExpr> parameters) {
        super(id, parameters);
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return super.toString() + " { ID: " + this.getId() + " }";
    }

}
