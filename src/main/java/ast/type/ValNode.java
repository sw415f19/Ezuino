package ast.type;

import ast.expr.PrimaryExprNode;
import astvisitors.AstVisitor;

public abstract class ValNode extends PrimaryExprNode {
	protected String val;

    public String getVal() {
        return this.val;
    }

	@Override
	public String toString() {
		return super.toString() + " { val: " + val + " type: " + type + " }";
	}
}
