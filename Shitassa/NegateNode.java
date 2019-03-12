public class NegateNode extends ExpressionNode
{
    public ExpressionNode InnerNode;


    public NegateNode() {
    }

    public NegateNode(ExpressionNode InnerNode) {
        this.InnerNode = InnerNode;
    }

    public ExpressionNode getInnerNode() {
        return this.InnerNode;
    }

    public void setInnerNode(ExpressionNode InnerNode) {
        this.InnerNode = InnerNode;
    }

    public NegateNode InnerNode(ExpressionNode InnerNode) {
        this.InnerNode = InnerNode;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " InnerNode='" + getInnerNode() + "'" +
            "}";
    }

}