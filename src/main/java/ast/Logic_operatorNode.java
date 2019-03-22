package ast;

import ezuino.AstVisitor;

public class Logic_operatorNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
