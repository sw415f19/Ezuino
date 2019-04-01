package ast;

import ast.expr.iexpr.IExpr;
import ast.type.IntegerNode;
import ast.type.ValNode;
import astvisitors.AstVisitor;
import generated.EzuinoParser;

public class List_removeNode extends AstNode {

	private String id;
	private IntegerNode interger;
	private ValNode val;

	public List_removeNode(String id, IntegerNode interger, ValNode val) {
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
}
