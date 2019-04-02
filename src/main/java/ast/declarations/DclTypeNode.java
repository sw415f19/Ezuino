package ast.declarations;

import ast.AstNode;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class DclTypeNode extends AstNode {
    private String ID;

    public DclTypeNode(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    @Override
    public void accept(AstVisitor v) {

    }

	@Override
	public void acceptLevel(AstLevelVisitor v, int level) {
		v.visitLevel(this, level);
		
	}
}
