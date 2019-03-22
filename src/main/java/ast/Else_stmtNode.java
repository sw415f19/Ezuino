package ast;

import ezuino.AstVisitor;

public class Else_stmtNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
