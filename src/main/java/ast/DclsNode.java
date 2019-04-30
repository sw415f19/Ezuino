package ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import astvisitors.AstVisitor;

public class DclsNode extends AstNode {
    private List<DclNode> childList = new ArrayList<DclNode>();

    public void addChild(DclNode child) {
        this.childList.add(child);
    }

    public int getChildCount() {
        return childList.size();
    }

    public DclNode getChild(int index) {
        return this.childList.get(index);
    }

    public Iterator<DclNode> getChildIterator() {
        return childList.iterator();
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
