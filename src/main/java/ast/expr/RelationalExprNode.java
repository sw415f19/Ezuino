package ast.expr;

import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;
import ast.AstNode;
import ast.expr.iexpr.*;

public class RelationalExprNode extends AstNode implements IRelationalExpr {
    private IRelationalExpr leftNode;
    private String operator;
    private IAddictiveExpr rightNode;

    public RelationalExprNode(IRelationalExpr relationalExprNode, String operator, IAddictiveExpr additiveExprNode) {
        this.leftNode = relationalExprNode;
        this.operator = operator;
        this.rightNode = additiveExprNode;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
	
	@Override
	public void acceptLevel(AstLevelVisitor v, int level) {
		v.visitLevel(this, level);
	}

    public IRelationalExpr getLeftNode() {
        return leftNode;
    }

    public String getOperator() {
        return operator;
    }

    public IAddictiveExpr getRightNode() {
        return rightNode;
    }
}
