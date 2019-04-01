package ast;

import astvisitors.AstVisitor;

public class Func_callNode extends StmtNode {

	private String ID;
	private Func_Call_ParamNode fNode;

	public Func_callNode(String ID, Func_Call_ParamNode fNode) {
		this.ID = ID;
		this.fNode = fNode;
	}

	public Func_callNode(Func_Call_ParamNode fNode)
	{
		this.fNode = fNode;
	}

	public String getID() {
		return this.ID;
	}

	public Func_Call_ParamNode getFNode() {
		return this.fNode;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}

}
