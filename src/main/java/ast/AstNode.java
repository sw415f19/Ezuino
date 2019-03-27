package ast;

import ezuino.AstVisitor;

public abstract class AstNode {
	public abstract void accept(AstVisitor v);
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
