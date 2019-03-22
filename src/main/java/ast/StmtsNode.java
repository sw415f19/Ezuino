package ast;

import java.util.ArrayList;

import ezuino.AstVisitor;

public class StmtsNode extends AstNode {
    private ArrayList<StmtNode> childList = new ArrayList<StmtNode>();

    public ArrayList<StmtNode> getChildList() {
        return childList;
    }

    public void addChild(StmtNode node) {
        childList.add(node);
    }

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
