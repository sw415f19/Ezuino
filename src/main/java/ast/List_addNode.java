package ast;

import astvisitors.AstVisitor;

public class List_addNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
