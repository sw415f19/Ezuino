package ast;

import ast.expr.iexpr.IExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class While_stmtNode extends AstNode {
    private IExpr exprNode;
    public While_stmtNode(IExpr exprNode)
    {
        this.exprNode = exprNode;
    }

    public IExpr getExprNode()
    {
        return exprNode;
    }

    @Override
	public void accept(AstVisitor v) {
		v.visit(this);
		
	}
	
	@Override
	public void acceptLevel(AstLevelVisitor v, int level) {
		v.visitLevel(this, level);
	}
}
