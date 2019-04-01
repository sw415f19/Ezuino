package ast.type;

import astvisitors.AstVisitor;

public class StringNode extends ValNode {

    private String val;

    public StringNode(String val) {
        System.out.println("Created String Node");
        this.val = val;
    }

    public String getVal() {
        return this.val;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
