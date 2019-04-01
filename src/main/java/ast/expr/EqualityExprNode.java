package ast.expr;

import ast.AstNode;
import ast.expr.iexpr.IEqualityExpr;
import ast.expr.iexpr.IRelationalExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class EqualityExprNode extends AstNode implements IEqualityExpr {
    private IEqualityExpr leftNode;
    private String operator;
    private IRelationalExpr relationalExprNode;

    public EqualityExprNode(IEqualityExpr iEqualityExpr, String operator, IRelationalExpr iRelationalExpr) {
        this.leftNode = iEqualityExpr;
        this.operator = operator;
        this.relationalExprNode = iRelationalExpr;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
	
	@Override
	public void acceptLevel(AstLevelVisitor v, int level) {
		v.visitLevel(this, level);
	}

    public IEqualityExpr getLeftNode() {
        return leftNode;
    }

    public String getOperator() {
        return operator;
    }

    public IRelationalExpr getRelationalExprNode() {
        return relationalExprNode;
    }
}
