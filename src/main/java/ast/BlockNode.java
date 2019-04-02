package ast;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class BlockNode extends AstNode {

	private DclsNode dclsNode;
	private StmtsNode stmsNode;
	private Return_stmtNode return_stmtNode;

	public BlockNode(DclsNode dclsNode, StmtsNode stmsNode, Return_stmtNode return_stmtNode) {
		this.dclsNode = dclsNode;
		this.stmsNode = stmsNode;
		this.return_stmtNode = return_stmtNode;
	}

	public BlockNode(DclsNode dclsNode, StmtsNode stmsNode) {
		this.dclsNode = dclsNode;
		this.stmsNode = stmsNode;
	}

	public DclsNode getDclsNode() {
		return dclsNode;
	}

	public StmtsNode getStmsNode() {
		return stmsNode;
	}

	public Return_stmtNode getReturn_stmtNode() {
		return return_stmtNode;
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
