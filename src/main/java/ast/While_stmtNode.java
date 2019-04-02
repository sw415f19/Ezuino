package ast;

import ast.expr.iexpr.IExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class While_stmtNode extends AstNode {
    private IExpr exprNode;
    private BlockNode blockNode;
    public While_stmtNode(IExpr exprNode, BlockNode blockNode)
    {
        this.exprNode = exprNode;
        this.blockNode = blockNode;
    }

    public IExpr getExprNode()
    {
        return exprNode;
    }
    public BlockNode getBlockNode() {
    	return blockNode;
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
