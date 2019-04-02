package ast;

import ast.declarations.DclTypeNode;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class DclNode extends AstNode {
	private DclTypeNode dclTypeNode;

	public DclNode(DclTypeNode dclTypeNode) {
		this.dclTypeNode = dclTypeNode;
	}

	public DclTypeNode getDclTypeNode() {
		return dclTypeNode;
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
