package ast.expr;

import ast.expr.aexpr.AMultiplicativeExpr;
import ast.expr.aexpr.AUnaryExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class MultiplicativeExprNode extends AMultiplicativeExpr {
    private AMultiplicativeExpr leftNode;
    private String operator;
    private AUnaryExpr rightNode;

    public MultiplicativeExprNode(AMultiplicativeExpr iMultiplicativeExpr, String operator, AUnaryExpr iUnaryExpr) {
        this.leftNode = iMultiplicativeExpr;
        this.operator = operator;
        this.rightNode = iUnaryExpr;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
	
	@Override
	public void acceptLevel(AstLevelVisitor v, int level) {
		v.visitLevel(this, level);
	}

    public AMultiplicativeExpr getLeftNode() {
        return leftNode;
    }

    public String getOperator() {
        return operator;
    }

    public AUnaryExpr getRightNode() {
        return rightNode;
    }
    
    @Override
    public String toString() {
    	return super.toString() + "{ operator: " + operator + " type: " + type +  " }";
    }
}
