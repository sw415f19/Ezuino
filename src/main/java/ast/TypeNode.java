package ast;

import astvisitors.AstVisitor;

public class TypeNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
