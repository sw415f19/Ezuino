package ast.type;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class IntegerNode extends ValNode {

    private String val;

    public IntegerNode(String val) {
        System.out.println("Created Integer Node");
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
		return "IntegerNode { val: " + val  + " }";
	}
}
