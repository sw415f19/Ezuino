package ast;

import ast.expr.aexpr.AExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class Assign_stmtNode extends StmtNode {
	private String id;
	private AExpr exprNode;

	public Assign_stmtNode(String id, AExpr exprNode) {
		this.id = id;
		this.exprNode = exprNode;
		System.out.println(this.id);
		// System.out.println(this.exprNode.getClass().getSimpleName());
	}

	public String getId() {
		return this.id;
	}

	public AExpr getExprNode() {
		return this.exprNode;
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
