package ast;

import ezuino.AstVisitor;

public class ConditionNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
