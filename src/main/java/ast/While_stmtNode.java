package ast;

import astvisitors.AstVisitor;

public class While_stmtNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
