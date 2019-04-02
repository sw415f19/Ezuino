package ast;

import astvisitors.AstLevelVisitor;
import ast.type.IntegerNode;
import ast.type.ValNode;
import astvisitors.AstVisitor;

public class List_removeNode extends AstNode {

	private String id;
	private ValNode val;
	private IntegerNode interger;


	public List_removeNode(String id, ValNode val, IntegerNode interger) {
		this.id = id;
		this.interger = interger;
		this.val = val;
	}

	public String getId() {
		return id;
	}

	public IntegerNode getInterger() {
		return interger;
	}

	public ValNode getVal() {
		return val;
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
