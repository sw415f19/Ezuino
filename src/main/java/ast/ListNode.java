package ast;

import ezuino.AstVisitor;

public class ListNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
