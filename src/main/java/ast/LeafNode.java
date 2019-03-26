package ast;

import ezuino.AstVisitor;

public abstract class LeafNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		System.err.println("Not implemented.");
	}

}
