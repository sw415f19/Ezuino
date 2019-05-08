package ast.funcallstmt;

import java.util.ArrayList;

import ast.expr.aexpr.AExpr;
import astvisitors.AstVisitor;

public class PrintNode extends Func_callStmtNode {

    public PrintNode(ArrayList<AExpr> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

}
