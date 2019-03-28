package ast;

import ezuino.AstVisitor;

import java.util.ArrayList;

public class StartNode extends AstNode {
    private ArrayList<AstNode> ast;
	private DclsNode dcls;
	private StmtsNode stmts;

    public StartNode(ArrayList<AstNode> ast) {
        this.ast = ast;
    }

    public DclsNode getDcls() {
        return dcls;
    }

    public StmtsNode getStmts() {
        return stmts;
    }

    public ArrayList<AstNode> getAst() {
        return ast;
    }

    @Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
