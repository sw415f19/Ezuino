package ast.expr;

import ast.expr.aexpr.*;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class LogicalOrExprNode extends AlogicalOrExpr {
    private AlogicalOrExpr leftNode;
    private ALogicalAndExpr rightNode;

    public LogicalOrExprNode(AlogicalOrExpr ilogicalOrExpr, ALogicalAndExpr iLogicalAndExpr) {
        this.leftNode = ilogicalOrExpr;
        this.rightNode = iLogicalAndExpr;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
	
	@Override
	public void acceptLevel(AstLevelVisitor v, int level) {
		v.visitLevel(this, level);
	}

    public AlogicalOrExpr getLeftNode() {
        return leftNode;
    }

    public ALogicalAndExpr getRightNode() {
        return rightNode;
    }
}
