package ast;

import astvisitors.AstVisitor;

public class Switch_blockNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
