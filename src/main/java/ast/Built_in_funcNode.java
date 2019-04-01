package ast;

import astvisitors.AstVisitor;

public class Built_in_funcNode extends AstNode {
    @Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}

}
