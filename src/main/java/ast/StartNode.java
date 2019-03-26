package ast;

import ezuino.AstVisitor;

import java.util.ArrayList;

public class StartNode extends AstNode {
    private ArrayList<AstNode> ast;
    private DclsNode dcls;
    private StmtsNode stmts;

    public StartNode(ArrayList<AstNode> astArray) {
        this.ast = astArray;
    }

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

    public ArrayList<AstNode> getAst() {
        return ast;
    }

    public void setAst(ArrayList<AstNode> ast) {
        this.ast = ast;
    }

    @Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
