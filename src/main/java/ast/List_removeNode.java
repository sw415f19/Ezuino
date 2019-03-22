package ast;

import ezuino.AstVisitor;

public class List_removeNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
