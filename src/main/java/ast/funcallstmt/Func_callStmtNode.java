package ast.funcallstmt;

import ast.StmtNode;
import ast.expr.aexpr.AExpr;

import java.util.List;

public abstract class Func_callStmtNode extends StmtNode {
    private String Id;
    private List<AExpr> parameters;

    public Func_callStmtNode(String id, List<AExpr> parameters) {
        this.Id = id;
        this.parameters = parameters;
    }

    public List<AExpr> getParameters() {
        return parameters;
    }

    public String getId() {
        return Id;
    }
}