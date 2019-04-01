package ast;

import astvisitors.AstVisitor;

public class StmtNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}

}
