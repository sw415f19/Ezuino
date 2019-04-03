package ast;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class Func_callNode extends StmtNode {

	private String ID;
	private Func_Call_ParamNode paramNode;



	public Func_callNode(String ID, Func_Call_ParamNode paramNode)
	{
		this.ID = ID;
		this.paramNode = paramNode;
	}

	public String getID()
	{
		return ID;
	}

	public Func_Call_ParamNode getParamNode()
	{
		return paramNode;
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
		return super.toString() + " { ID: " + this.ID + " }";
	}

}