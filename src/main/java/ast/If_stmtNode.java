package ast;

import ast.expr.iexpr.IExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class If_stmtNode extends StmtNode {

	private IExpr expr;
	private BlockNode trueBlock;
	private BlockNode falseBlock;

	public If_stmtNode(IExpr expr, BlockNode trueBlock, BlockNode falseBlock) {
		this.expr = expr;
		this.trueBlock = trueBlock;
		this.falseBlock = falseBlock;
	}

	public IExpr getExpr() {
		return this.expr;
	}

	public BlockNode getTrueBlock() {
		return this.trueBlock;
	}
	
	public BlockNode getFalseBlock() {
		return this.falseBlock;
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
