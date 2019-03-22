package ast;

import java.util.ArrayList;

import ezuino.AstVisitor;

public class DclsNode extends AstNode {
    private ArrayList<DclNode> ChildList = new ArrayList<DclNode>();

    public ArrayList<DclNode> getChildList() {
        return ChildList;
    }

    public void addChild(DclNode node) {
        ChildList.add(node);
    }

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
