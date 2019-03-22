package ast;

import ezuino.AstVisitor;

public class List_idNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
