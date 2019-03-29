package ast.expr;

import ast.AstNode;
import ezuino.AstVisitor;
import ast.expr.iexpr.*;

public class UnaryExprNode extends AstNode implements IMultiplicativeExpr {
    private String operator;
    private IParenthesisExpr node;

    public UnaryExprNode(String operator, IParenthesisExpr parenthesisExprNode) {
        this.operator = operator;
        this.node = parenthesisExprNode;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public String getOperator() {
        return operator;
    }

    public IParenthesisExpr getNode() {
        return node;
    }

}
