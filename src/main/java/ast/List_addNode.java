package ast;

import astvisitors.AstLevelVisitor;

import ast.type.ValNode;
import astvisitors.AstVisitor;

public class List_addNode extends AstNode {
	private String listID;
	private ValNode valNode;
	private String index;


	public List_addNode(String listID, ValNode valNode, String index) {
		this.listID = listID;
		this.valNode = valNode;
		this.index = index;
	}

	public String getListID() {
		return listID;
	}

	public ValNode getValNode() {
		return valNode;
	}

	public String getIndex() {
		return this.index;
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
		return "list_add { listID: " + listID + "index: " + index + "}";
	}
	
}
