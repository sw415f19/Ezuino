package ast;

import astvisitors.AstVisitor;
import ast.expr.iexpr.IExpr;

public class Assign_stmtNode extends StmtNode {
	private String id;
	private IExpr exprNode;

	public Assign_stmtNode(String id, IExpr exprNode) {
		this.id = id;
		this.exprNode = exprNode;
		System.out.println(this.id);
		// System.out.println(this.exprNode.getClass().getSimpleName());
	}

	public String getId() {
		return this.id;
	}

	public IExpr getExprNode() {
		return this.exprNode;
	}

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
	}
}
