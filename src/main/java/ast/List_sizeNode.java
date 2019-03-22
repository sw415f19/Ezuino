package ast;

import ezuino.AstVisitor;

public class List_sizeNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
