package ast;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;
import generated.EzuinoParser;

public class Built_in_funcNode extends AstNode {

	private Print_lNode print_lNode;
	private List_addNode list_addNode;
	private List_removeNode list_removeNode;


	public Built_in_funcNode(Print_lNode print_lNode) {
		this.print_lNode = print_lNode;
	}

	public Built_in_funcNode(List_addNode list_addNode) {
		this.list_addNode = list_addNode;
	}

	public Built_in_funcNode(List_removeNode list_removeNode) {
		this.list_removeNode = list_removeNode;
	}

	public Print_lNode getPrint_lNode() {
		return print_lNode;
	}

	public List_addNode getList_addNode() {
		return list_addNode;
	}

	public List_removeNode getList_removeNode() {
		return list_removeNode;
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
