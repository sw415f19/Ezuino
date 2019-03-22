package ast;

import ezuino.AstVisitor;

public class FuncNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}

}
