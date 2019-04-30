package ast;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class ParametersNode extends AstNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);
	}
	
	@Override
	public void acceptLevel(AstLevelVisitor v, int level) {
		v.visitLevel(this, level);
	}
}
