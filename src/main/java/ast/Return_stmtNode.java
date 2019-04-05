package ast;

import ast.expr.aexpr.AExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class Return_stmtNode extends AstNode {

	private AExpr returnExpr;
	private Type type;
	public Return_stmtNode(AExpr returnExpr) {
		this.returnExpr = returnExpr;
	}
	
	public AExpr getReturnExpr() {
		return returnExpr;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
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
