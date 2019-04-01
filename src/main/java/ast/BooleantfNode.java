package ast;

import astvisitors.AstVisitor;

public class BooleantfNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
