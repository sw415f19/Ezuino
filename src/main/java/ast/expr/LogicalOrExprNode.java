package ast.expr;

import ast.AstNode;
import ast.expr.iexpr.*;

import ezuino.AstVisitor;

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

    public IlogicalOrExpr getLeftNode() {
        return leftNode;
    }

    public ILogicalAndExpr getRightNode() {
        return rightNode;
    }
}
