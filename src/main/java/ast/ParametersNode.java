package ast;

import astvisitors.AstVisitor;

public class ParametersNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
