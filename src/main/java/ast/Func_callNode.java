package ast;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class Func_callNode extends StmtNode {

	private String ID;
	private Func_Call_ParamNode paramNode;
	private Built_in_funcNode builtinNode;

	public Func_callNode(String ID, Func_Call_ParamNode paramNode)
	{
		this.ID = ID;
		this.paramNode = paramNode;
	}

	public Func_callNode(Built_in_funcNode builtinNode)
	{
		this.builtinNode = builtinNode;
	}

	public String getID()
	{
		return ID;
	}

	public Func_Call_ParamNode getParamNode()
	{
		return paramNode;
	}

	public Built_in_funcNode getBuiltinNode()
	{
		return builtinNode;
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
		String name = super.toString();
		return this.builtinNode != null ? name : name + " { ID: " + this.ID + " }";
	}

}