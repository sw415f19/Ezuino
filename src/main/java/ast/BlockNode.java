package ast;

import astvisitors.AstVisitor;

public class BlockNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);

	}
}
