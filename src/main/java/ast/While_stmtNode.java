package ast;

import ast.expr.aexpr.AExpr;
import astvisitors.AstLevelVisitor;
import astvisitors.AstVisitor;

public class While_stmtNode extends StmtNode {
    private AExpr exprNode;
    private BlockNode blockNode;

    public While_stmtNode(AExpr exprNode, BlockNode blockNode) {
        this.exprNode = exprNode;
        this.blockNode = blockNode;
    }

    public AExpr getExprNode() {
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
