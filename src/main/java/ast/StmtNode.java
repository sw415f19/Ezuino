package ast;

import ezuino.AstVisitor;

public abstract class StmtNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}

}
