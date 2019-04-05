package ast;

import ast.expr.aexpr.AExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

import java.util.ArrayList;

public class Func_callStmtNode extends StmtNode {

	private String ID;
	private ArrayList<AExpr> parameters = new ArrayList<AExpr>();


	public Func_callStmtNode(String ID, ArrayList<AExpr> parameters) {
		this.ID = ID;
		this.parameters = parameters;
	}

	public String getID()
	{
		return ID;
	}

	public ArrayList<AExpr> getParameters() {
		return parameters;
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