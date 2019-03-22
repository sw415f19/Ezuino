package ast;

import ezuino.AstVisitor;

public class Comparator_operatorNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
