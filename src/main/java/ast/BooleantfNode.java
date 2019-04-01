package ast;

import ast.expr.PrimaryExprNode;
import astvisitors.AstVisitor;

public class BooleantfNode extends PrimaryExprNode {

	private String boolval;

	public BooleantfNode(String boolval) {
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
}
