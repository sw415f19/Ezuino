package ast;

import java.util.ArrayList;

public class DclsNode extends AstNode {
    private ArrayList<DclNode> ChildList = new ArrayList<DclNode>();

    public ArrayList<DclNode> getChildList() {
        return ChildList;
    }

    public void addChild(DclNode node) {
        ChildList.add(node);
    }
}
