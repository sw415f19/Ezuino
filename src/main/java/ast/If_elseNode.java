package ast;

import ezuino.AstVisitor;

public class If_elseNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
