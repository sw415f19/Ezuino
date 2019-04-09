package ast;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;
import symbolTable.SymbolTableCasper;

public abstract class AstNode {
	public abstract void accept(AstVisitor v);
	public abstract void acceptLevel(AstLevelVisitor v, int level);

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
