package ast;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;
import symbolTable.SymbolTable;

public class BlockNode extends AstNode {

	private DclsNode dclsNode;
	private StmtsNode stmtsNode;
	private Return_stmtNode return_stmtNode;

	public BlockNode(DclsNode dclsNode, StmtsNode stmtsNode, Return_stmtNode return_stmtNode) {
		this.dclsNode = dclsNode;
		this.stmtsNode = stmtsNode;
		this.return_stmtNode = return_stmtNode;
	}

	public DclsNode getDclsNode() {
		return dclsNode;
	}

	public StmtsNode getStmtsNode() {
		return stmtsNode;
	}

	public Return_stmtNode getReturnstmtNode() {
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
