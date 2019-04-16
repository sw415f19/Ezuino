package ast;

import astvisitors.AstLevelVisitor;
import ast.expr.PrimaryExprNode;
import astvisitors.AstVisitor;

public class BooleanLiteral extends PrimaryExprNode {

	private String boolval;

	public BooleanLiteral(String boolval) {
		System.out.println("Created Boolean Value");
		this.boolval = boolval;
	}

	public String getBoolval() {
		return this.boolval;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);

	}
	
	@Override
	public void acceptLevel(AstLevelVisitor v, int level) {
		v.visitLevel(this, level);
	}
	
	@Override
	public String toString() {
		return super.toString() + " { boolval: " + boolval + " type: " + type + " }";
	}
}
