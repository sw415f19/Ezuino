package ast.funcallstmt;

import java.util.ArrayList;
import java.util.List;

import ast.expr.aexpr.AExpr;
import astvisitors.AstVisitor;

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
