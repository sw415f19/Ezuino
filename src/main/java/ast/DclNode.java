package ast;

import ezuino.AstVisitor;

public class DclNode extends AstNode {

    @Override
    public String toString() {
        return "DclNode{}";
    }

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
}
