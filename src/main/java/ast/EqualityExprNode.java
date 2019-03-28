package ast;

import ezuino.AstVisitor;

public class EqualityExprNode extends ExprNode {
    private EqualityExprNode leftNode;
    private String operator;
    private RelationalExprNode relationalExprNode;

    public EqualityExprNode(EqualityExprNode leftNode, String operator, RelationalExprNode relationalExprNode) {
        this.leftNode = leftNode;
        this.operator = operator;
        this.relationalExprNode = relationalExprNode;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    public EqualityExprNode getLeftNode() {
        return leftNode;
    }

    public String getOperator() {
        return operator;
    }

    public RelationalExprNode getRelationalExprNode() {
        return relationalExprNode;
    }
}
