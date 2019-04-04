package ast.type;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class StringNode extends ValNode {

    private String val;

    public StringNode(String val) {
        this.val = val;
    }

    public String getVal() {
        return this.val;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
	
	@Override
	public void acceptLevel(AstLevelVisitor v, int level) {
		v.visitLevel(this, level);
	}
}
