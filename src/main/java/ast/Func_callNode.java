package ast;

import astvisitors.AstVisitor;

public class Func_callNode extends StmtNode {

	private String ID;
	private Func_Call_ParamNode fNode;
	private Built_in_funcNode bNode;

	public Func_callNode(String ID, Func_Call_ParamNode fNode)
	{
		this.ID = ID;
		this.fNode = fNode;
	}

	public Func_callNode(Built_in_funcNode bNode)
	{
		this.bNode = bNode;
	}

	public String getID()
	{
		return ID;
	}

	public Func_Call_ParamNode getfNode()
	{
		return fNode;
	}

	public Built_in_funcNode getbNode()
	{
		return bNode;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}

}
