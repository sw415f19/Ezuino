package ast;

import java.util.ArrayList;
import java.util.List;

import ezuino.AstVisitor;

public class DclsNode extends AstNode {
	
	List<DclNode> childList = new ArrayList<DclNode>();
	
	
	public void addChild(DclNode child) {
		this.childList.add(child);
	}
	
	public int getChildCount() {
		return childList.size();
	}
	
	public DclNode getChild(int index) {
		return this.childList.get(index);
	}


	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
