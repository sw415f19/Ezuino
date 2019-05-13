package ast.arduino;

import ast.type.ValNode;
import astvisitors.AstVisitor;

public class PinModeNode extends ValNode {

    public PinModeNode(String val) {
        this.val = val;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
