package ast.funcallstmt;

import java.util.ArrayList;

import ast.expr.aexpr.AExpr;
import astvisitors.AstVisitor;

public class CustomFuncCallStmtNode extends Func_callStmtNode {

    private String Id;

    public CustomFuncCallStmtNode(String Id, ArrayList<AExpr> parameters) {
        this.parameters = parameters;
        this.Id = Id;
    }

    public String getId() {
        return Id;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return super.toString() + " { ID: " + this.Id + " }";
    }

}
