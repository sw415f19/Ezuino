package ast;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public abstract class AstNode {
	protected Type type;

	public void setType(Type newType) {
		this.type = newType;
	}

	public Type getType() {
		return this.type;
	}


	public abstract void accept(AstVisitor v);
	public abstract void acceptLevel(AstLevelVisitor v, int level);

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
