package ast.funcallstmt;

import ast.expr.aexpr.AExpr;
import astvisitors.AstVisitor;

import java.util.List;

public class PrintNode extends Func_callStmtNode {

    public PrintNode(String id, List<AExpr> parameters) {
        super(id, parameters);
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

}
