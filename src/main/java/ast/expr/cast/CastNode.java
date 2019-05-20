package ast.expr.cast;

import java.util.List;

import ast.Type;
import ast.expr.Func_callExprNode;
import ast.expr.aexpr.AExpr;

public abstract class CastNode extends Func_callExprNode {

    protected Type fromType;

    public CastNode(String ID, List<AExpr> parameters) {
        super(ID, parameters);
    }

    public Type getFromType() {
        return fromType;
    }

    public void setFromType(Type fromType) {
        this.fromType = fromType;
    }

}
