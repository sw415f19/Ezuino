package ast.type;

import ast.expr.PrimaryExprNode;
import ezuino.AstVisitor;

public abstract class ValNode extends PrimaryExprNode {

	@Override
	public void accept(AstVisitor v) {
		v.visit(this);

	}
}
