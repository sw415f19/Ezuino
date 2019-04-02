package ast;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class DclNode extends AstNode {
	private Type type;
	private String ID;

	public DclNode(Type type, String ID) {
		this.type = type;
		this.ID = ID;
	}

	public Type getType() {
		return type;
	}

	public String getID() {
		return ID;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
	
	@Override
	public void acceptLevel(AstLevelVisitor v, int level) {
		v.visitLevel(this, level);
	}
}
