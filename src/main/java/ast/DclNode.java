package ast;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class DclNode extends TypeNode {
	private String ID;
	private Boolean isList = false;

	public DclNode(Type type, String ID) {
		this.type = type;
		this.ID = ID;
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
	
	
	@Override
	public String toString() {
		return super.toString() + "{ ID: " + ID + " type: " + type + " }";
	}

	public DclNode(Type type, String ID, boolean isList) {
		this.type = type;
		this.ID = ID;
		this.isList = isList;
	}


 	public Boolean isList(){
		return isList;
	}
}
