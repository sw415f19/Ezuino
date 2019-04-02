package ast.expr;

import ast.AstNode;
import ast.expr.iexpr.*;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class LogicalOrExprNode extends AstNode implements IlogicalOrExpr {
    private IlogicalOrExpr leftNode;
    private ILogicalAndExpr rightNode;

    public LogicalOrExprNode(IlogicalOrExpr ilogicalOrExpr, ILogicalAndExpr iLogicalAndExpr) {
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

    public IlogicalOrExpr getLeftNode() {
        return leftNode;
    }

    public ILogicalAndExpr getRightNode() {
        return rightNode;
    }
}
