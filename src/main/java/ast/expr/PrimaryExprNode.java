package ast.expr;

import ast.AstNode;
import ast.expr.iexpr.*;

import ezuino.AstVisitor;

public abstract class PrimaryExprNode extends AstNode implements IPrimaryExpr {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);

	}
}
