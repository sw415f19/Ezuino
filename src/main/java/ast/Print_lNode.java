package ast;

import ast.expr.aexpr.AExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class Print_lNode extends AstNode {

	private AExpr exprNode;

	public Print_lNode(AExpr exprNode)
	{
		this.exprNode = exprNode;
	}

	public AExpr getExprNode()
	{
		return exprNode;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
	}
	
	@Override
	public void acceptLevel(AstLevelVisitor v, int level) {
		v.visitLevel(this, level);
	}

}
