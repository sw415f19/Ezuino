package ast;

import ast.expr.iexpr.IExpr;
import astvisitors.AstVisitor;

public class Print_lNode extends AstNode {

	private IExpr exprNode;

	public Print_lNode(IExpr exprNode)
	{
		this.exprNode = exprNode;
	}

	public IExpr getExprNode()
	{
		return exprNode;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
	}

}
