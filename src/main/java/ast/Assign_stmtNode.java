package ast;

import ezuino.AstVisitor;

public class Assign_stmtNode extends StmtNode {
	private String id;
	


	public Assign_stmtNode(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

    @Override
	public void accept(AstVisitor v) {
		v.visit(this);	
	}
}
