package ast;

import ast.expr.iexpr.IExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class If_stmtNode extends StmtNode {

	private IExpr expr;
	private BlockNode ifBlock;
	private BlockNode elseBlock;

	public If_stmtNode(IExpr expr, BlockNode ifBlock, BlockNode elseBlock) {
		this.expr = expr;
		this.ifBlock = ifBlock;
		this.elseBlock = elseBlock;
	}

	public IExpr getExpr() {
		return this.expr;
	}

	public BlockNode getIfBlock() {
		return this.ifBlock;
	}
	
	public BlockNode getElseBlock() {
		return this.elseBlock;
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
