package ast;

import ezuino.AstVisitor;

public class While_stmtNode extends StmtNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
