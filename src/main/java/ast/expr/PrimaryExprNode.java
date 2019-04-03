package ast.expr;

import ast.expr.aexpr.*;
import astvisitors.AstVisitor;

public abstract class PrimaryExprNode extends APrimaryExpr {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);

	}
}
