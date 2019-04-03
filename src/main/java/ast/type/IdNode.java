package ast.type;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class IdNode extends ValNode {

    private String val;

    public IdNode(String val) {
        System.out.println("Created ID Node");
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
	
	@Override
	public String toString() {
		return super.toString() + " { val: " + val + " }";
	}
}
