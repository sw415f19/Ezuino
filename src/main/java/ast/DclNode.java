package ast;

import ast.declarations.DclTypeNode;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class DclNode extends AstNode {
	private DclTypeNode dclTypeNode;
	private String id;

	public DclNode(DclTypeNode dclTypeNode, String id) {
		this.dclTypeNode = dclTypeNode;
		this.id = id;
	}

	public DclTypeNode getDclTypeNode() {
		return dclTypeNode;
	}
	
	public String getId() {
		return this.id;
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
