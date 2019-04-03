package ast;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;
import generated.EzuinoParser;

public class Built_in_funcNode extends AstNode {

	private Print_lNode print_lNode;


	public Built_in_funcNode(Print_lNode print_lNode) {
		this.print_lNode = print_lNode;
	}


	public Print_lNode getPrint_lNode() {
		return print_lNode;
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
