package ast.expr;

import ast.AstNode;
import ast.expr.iexpr.IMultiplicativeExpr;
import ast.expr.iexpr.IUnaryExpr;
import astvisitors.AstVisitor;

public class MultiplicativeExprNode extends AstNode implements IMultiplicativeExpr {
    private IMultiplicativeExpr leftNode;
    private String operator;
    private IUnaryExpr rightNode;

    public MultiplicativeExprNode(IMultiplicativeExpr iMultiplicativeExpr, String operator, IUnaryExpr iUnaryExpr) {
        this.leftNode = iMultiplicativeExpr;
        this.operator = operator;
        this.rightNode = iUnaryExpr;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public IMultiplicativeExpr getLeftNode() {
        return leftNode;
    }

    public String getOperator() {
        return operator;
    }

    public IUnaryExpr getRightNode() {
        return rightNode;
    }
}
