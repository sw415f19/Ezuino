package ast;

import ezuino.AstVisitor;

public class Func_callNode extends StmtNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}

}
