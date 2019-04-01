package ast.expr;

import ast.*;
import ast.expr.iexpr.IAddictiveExpr;
import ast.expr.iexpr.IMultiplicativeExpr;
import astvisitors.AstVisitor;

public class AdditiveExprNode extends AstNode implements IAddictiveExpr {
    private IAddictiveExpr leftNode;
    private String operator;
    private IMultiplicativeExpr rightNode;

    public AdditiveExprNode(IAddictiveExpr iAddictiveExpr, String operator, IMultiplicativeExpr iMultiplicativeExpr) {
        this.leftNode = iAddictiveExpr;
        this.operator = operator;
        this.rightNode = iMultiplicativeExpr;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public IAddictiveExpr getLeftNode() {
        return leftNode;
    }

    public String getOperator() {
        return operator;
    }

    public IMultiplicativeExpr getRightNode() {
        return rightNode;
    }
}
