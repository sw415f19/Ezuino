package ast;

import ezuino.AstVisitor;

public class ExprNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
