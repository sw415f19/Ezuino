package ast;

import ezuino.AstVisitor;

public class ValNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
