package ast;

import java.util.ArrayList;

import ast.expr.aexpr.AExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class Func_Call_ParamNode extends AstNode {

	private ArrayList<AExpr> expr = new ArrayList<AExpr>();

	public Func_Call_ParamNode(ArrayList<AExpr> expr) {
		this.expr = expr;
	}

	public ArrayList<AExpr> getExpr() {
		return expr;
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
