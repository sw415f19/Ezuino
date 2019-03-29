package ast.expr;

import ast.AstNode;
import ast.expr.iexpr.IExpr;
import ast.expr.iexpr.IParenthesisExpr;
import ezuino.AstVisitor;

public class ParenthesisExprNode extends AstNode implements IParenthesisExpr {
    private IExpr node;

    public ParenthesisExprNode(IExpr iExpr) {
        this.node = iExpr;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public IExpr getNode() {
        return this.node;
    }
}
