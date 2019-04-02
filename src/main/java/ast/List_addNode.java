package ast;

import astvisitors.AstLevelVisitor;
import ast.type.IntegerNode;
import ast.type.ValNode;
import astvisitors.AstVisitor;

public class List_addNode extends AstNode {
	private String listID;
	private ValNode valNode;
	private IntegerNode integerNode;


	public List_addNode(String listID, ValNode valNode, IntegerNode integerNode) {
		this.listID = listID;
		this.valNode = valNode;
		this.integerNode = integerNode;
		System.out.println(listID + " " + valNode + " " + integerNode);
	}

	public String getListID() {
		return listID;
	}

	public ValNode getValNode() {
		return valNode;
	}

	public IntegerNode getIntegerNode() {
		return integerNode;
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
