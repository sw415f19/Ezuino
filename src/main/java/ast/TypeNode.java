package ast;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public abstract class TypeNode extends AstNode implements ITypeNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
	
	@Override
	public void acceptLevel(AstLevelVisitor v, int level) {
		v.visitLevel(this, level);
	}
}
