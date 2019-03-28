package ast;

import ezuino.AstVisitor;

public class Assign_stmtNode extends StmtNode {
	private String id;
	private ExprNode exprNode;
	
	public Assign_stmtNode(String id, ExprNode exprNode) {
		this.id = id;
		this.exprNode = exprNode;
		System.out.println(this.id);
		//System.out.println(this.exprNode.getClass().getSimpleName());
	}

	public String getId() {
		return this.id;
	}
 
	public ExprNode getExprNode(){
		return this.exprNode;
	}

    @Override
	public void accept(AstVisitor v) {
		v.visit(this);	
	}
}
