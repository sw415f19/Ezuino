package ast;

import ezuino.AstVisitor;

public abstract class AstNode {
	public abstract void accept(AstVisitor v);

}
