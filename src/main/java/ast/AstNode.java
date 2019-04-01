package ast;

import astvisitors.AstVisitor;

public abstract class AstNode {
	public abstract void accept(AstVisitor v);

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
