package ast;

import ezuino.AstVisitor;

public class StartNode extends AstNode {
    private DclsNode dcls;
    private StmtsNode stmts;

    public DclsNode getDcls() {
        return dcls;
    }

    public void setDcls(DclsNode dcls) {
        this.dcls = dcls;
    }

    public StmtsNode getStmts() {
        return stmts;
    }

    public void setStmts(StmtsNode stmts) {
        this.stmts = stmts;
    }

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
