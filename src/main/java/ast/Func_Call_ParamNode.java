package ast;

import java.util.ArrayList;

import ast.expr.iexpr.IExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class Func_Call_ParamNode extends AstNode {

	private ArrayList<IExpr> expr = new ArrayList<IExpr>();

	public Func_Call_ParamNode(ArrayList<IExpr> expr) {
		this.expr = expr;
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
