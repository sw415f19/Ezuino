package ast;

import ast.expr.aexpr.AExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class Return_stmtNode extends TypeNode {

	private AExpr returnExpr;
	
	public Return_stmtNode(AExpr returnExpr) {
		this.returnExpr = returnExpr;
	}
	
	public AExpr getReturnExpr() {
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
