package ast;

import ezuino.AstVisitor;

public class Boolean_exprNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
