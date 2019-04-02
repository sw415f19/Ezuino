package ast.expr;

import ast.AstNode;
import ast.expr.iexpr.IExpr;
import ast.expr.iexpr.IParenthesisExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class ParenthesisExprNode extends AstNode implements IParenthesisExpr {
    private IExpr node;

    public ParenthesisExprNode(IExpr iExpr) {
        this.node = iExpr;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
	
	@Override
	public void acceptLevel(AstLevelVisitor v, int level) {
		v.visitLevel(this, level);
	}

    public IExpr getNode() {
        return this.node;
    }
}
