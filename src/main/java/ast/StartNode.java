package ast;

import ezuino.AstVisitor;

import java.util.ArrayList;

public class StartNode extends AstNode {
    private ArrayList<AstNode> ast;


    public StartNode(ArrayList<AstNode> astArray) {
        this.ast = astArray;
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
