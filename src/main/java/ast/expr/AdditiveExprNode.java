package ast.expr;

import ast.expr.aexpr.AAddictiveExpr;
import ast.expr.aexpr.AMultiplicativeExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class AdditiveExprNode extends AAddictiveExpr {
    private AAddictiveExpr leftNode;
    private String operator;
    private AMultiplicativeExpr rightNode;

    public AdditiveExprNode(AAddictiveExpr iAddictiveExpr, String operator, AMultiplicativeExpr iMultiplicativeExpr) {
        this.leftNode = iAddictiveExpr;
        this.operator = operator;
        this.rightNode = iMultiplicativeExpr;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
	
	@Override
	public void acceptLevel(AstLevelVisitor v, int level) {
		v.visitLevel(this, level);
	}

    public AAddictiveExpr getLeftNode() {
        return leftNode;
    }

    public String getOperator() {
        return operator;
    }

    public AMultiplicativeExpr getRightNode() {
        return rightNode;
    }
    
    @Override
    public String toString() {
    	return super.toString() + "{ operator: "+ operator + " }";
    }
}
