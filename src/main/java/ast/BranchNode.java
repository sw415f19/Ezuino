package ast;

import java.util.ArrayList;
import java.util.List;

import ezuino.AstVisitor;

public abstract class BranchNode<T extends AstNode> extends AstNode {
	
	private List<T> childList = new ArrayList<T>();

	@Override
	public void accept(AstVisitor v) {
		System.err.println("Not implemented.");
	}
	
	public T getChild(int index) {
		return childList.get(index);
	}
	
	public int getChildCount() {
		return childList.size();
	}
	
	public void addChild(T newChild) {
		childList.add(newChild);
	}
	
	public List<T> getChildList() {
		return new ArrayList<T>(childList);
	}

}
