package ast.expr;

import ast.AstNode;
import ast.expr.iexpr.*;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class LogicalAndExprNode extends AstNode implements ILogicalAndExpr {
    private ILogicalAndExpr leftNode;
    private IEqualityExpr rightNode;

    public LogicalAndExprNode(ILogicalAndExpr iLogicalAndExpr, IEqualityExpr iEqualityExpr) {
        this.leftNode = iLogicalAndExpr;
        this.rightNode = iEqualityExpr;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
	
	@Override
	public void acceptLevel(AstLevelVisitor v, int level) {
		v.visitLevel(this, level);
	}

    public ILogicalAndExpr getLeftNode() {
        return leftNode;
    }

    public IEqualityExpr getRightNode() {
        return rightNode;
    }
}
