package ast;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class StartNode extends AstNode {
	private DclsNode dcls;
	private StmtsNode stmts;

    public StartNode(DclsNode dcls, StmtsNode stmts) {
        this.dcls = dcls;
        this.stmts = stmts;
    }

    public DclsNode getDcls() {
        return dcls;
    }

    public StmtsNode getStmts() {
        return stmts;
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
