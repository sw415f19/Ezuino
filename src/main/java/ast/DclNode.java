package ast;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class DclNode extends AstNode {
	
	private String id;
	
	public DclNode(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}

    @Override
    public String toString() {
        return "DclNode{ id: " + id + " }";
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
