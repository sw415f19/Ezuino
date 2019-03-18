public abstract class InfixExpressionNode extends ExpressionNode
{
    public ExpressionNode Left;
    public ExpressionNode Right;

    public InfixExpressionNode() {
    }

    public InfixExpressionNode(ExpressionNode Left, ExpressionNode Right) {
        this.Left = Left;
        this.Right = Right;
    }

    public ExpressionNode getLeft() {
        return this.Left;
    }

    public void setLeft(ExpressionNode Left) {
        this.Left = Left;
    }

    public ExpressionNode getRight() {
        return this.Right;
    }

    public void setRight(ExpressionNode Right) {
        this.Right = Right;
    }

    public InfixExpressionNode Left(ExpressionNode Left) {
        this.Left = Left;
        return this;
    }

    public InfixExpressionNode Right(ExpressionNode Right) {
        this.Right = Right;
        return this;
    }
    
    @Override
    public String toString() {
        return "{" +
            " Left='" + getLeft() + "'" +
            ", Right='" + getRight() + "'" +
            "}";
    }


    
}