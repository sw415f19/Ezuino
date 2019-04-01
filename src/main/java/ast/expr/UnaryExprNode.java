package ast.expr;

import ast.AstNode;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;
import ast.expr.iexpr.*;

public class UnaryExprNode extends AstNode implements IMultiplicativeExpr {
    private String operator;
    private IParenthesisExpr node;

    public UnaryExprNode(String operator, IParenthesisExpr parenthesisExprNode) {
        this.operator = operator;
        this.node = parenthesisExprNode;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
	
	@Override
	public void acceptLevel(AstLevelVisitor v, int level) {
		v.visitLevel(this, level);
	}

    public String getOperator() {
        return operator;
    }

    public IParenthesisExpr getNode() {
        return node;
    }

}
