package ast;

import ezuino.AstVisitor;

public class Block_switchNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
