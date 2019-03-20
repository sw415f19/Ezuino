package ast;

import java.util.ArrayList;

public class StmtsNode extends AstNode {
    private ArrayList<StmtNode> childList = new ArrayList<StmtNode>();

    public ArrayList<StmtNode> getChildList() {
        return childList;
    }

    public void addChild(StmtNode node) {
        childList.add(node);
    }
}
