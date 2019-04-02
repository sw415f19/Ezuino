package ast;

import ast.expr.iexpr.IExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class Return_stmtNode extends AstNode {

	private IExpr returnExpr;
	
	public Return_stmtNode(IExpr returnExpr) {
		this.returnExpr = returnExpr;
	}
	
	public IExpr getReturnExpr() {
		return returnExpr;
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
