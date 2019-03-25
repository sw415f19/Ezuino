package ast;

import ezuino.AstVisitor;

public class Switch_stmtNode extends StmtNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
