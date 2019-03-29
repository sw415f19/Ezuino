package ast.expr;
import ast.*;
import ast.expr.iexpr.IEqualityExpr;
import ast.expr.iexpr.IRelationalExpr;
import ezuino.AstVisitor;

public class EqualityExprNode extends AstNode implements IEqualityExpr{
    private IEqualityExpr leftNode;
    private String operator;
    private IRelationalExpr relationalExprNode;

    public EqualityExprNode(IEqualityExpr iEqualityExpr, String operator, IRelationalExpr iRelationalExpr) {
        this.leftNode = iEqualityExpr;
        this.operator = operator;
        this.relationalExprNode = iRelationalExpr;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public IEqualityExpr getLeftNode() {
        return leftNode;
    }

    public String getOperator() {
        return operator;
    }

    public IRelationalExpr getRelationalExprNode() {
        return relationalExprNode;
    }
}
