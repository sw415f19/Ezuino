package ast;

import ast.expr.iexpr.IExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class If_stmtNode extends AstNode {

	IExpr expr;
	BlockNode block;

	public If_stmtNode(IExpr expr, BlockNode block) {
		this.expr = expr;
		this.block = block;
	}

	public IExpr getExpr() {
		return this.expr;
	}


	public BlockNode getBlock() {
		return this.block;
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
