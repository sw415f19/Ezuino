package ast;

import astvisitors.AstVisitor;

public class Func_defNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}

}
