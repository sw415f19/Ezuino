package ast;

import java.util.ArrayList;
import java.util.List;

import ezuino.AstVisitor;

public class StmtsNode extends AstNode {
	
	private List<StmtNode> childList = new ArrayList<StmtNode>();
	
	public void addChild(StmtNode child) {
		childList.add(child);
	}
	
	public int getChildCount() {
		return childList.size();
	}
	
	public StmtNode getChild(int index) {
		return childList.get(index);
	}

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
