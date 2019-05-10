package ast.arduino;

import ast.type.ValNode;
import astvisitors.AstVisitor;

public class PinLevelNode extends ValNode {

    public PinLevelNode(String val) {
        this.val = val;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
