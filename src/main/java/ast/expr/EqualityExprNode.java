package ast.expr;

import ast.expr.aexpr.AEqualityExpr;
import ast.expr.aexpr.ARelationalExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class EqualityExprNode extends AEqualityExpr {
    private AEqualityExpr leftNode;
    private String operator;
    private ARelationalExpr relationalExprNode;

    public EqualityExprNode(AEqualityExpr iEqualityExpr, String operator, ARelationalExpr iRelationalExpr) {
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

    public AEqualityExpr getLeftNode() {
        return leftNode;
    }

    public String getOperator() {
        return operator;
    }

    public ARelationalExpr getRelationalExprNode() {
        return relationalExprNode;
    }

    @Override
    public String toString() {
        return super.toString() + "{ operator: "+ operator + " type: " + type +  " }";
    }
}
