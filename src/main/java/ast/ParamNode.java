package ast;

import astvisitors.AstVisitor;

public class ParamNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
