package ast;

import ezuino.AstVisitor;

public class If_elseNode extends StmtNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
