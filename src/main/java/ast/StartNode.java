package ast;

import ezuino.AstVisitor;

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
}
