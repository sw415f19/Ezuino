package ast;

import ezuino.AstVisitor;

public class Switch_stmtNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
