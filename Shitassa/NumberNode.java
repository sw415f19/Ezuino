public class NumberNode extends ExpressionNode
{
    public double Value;


    public NumberNode() {
    }

    public NumberNode(double Value) {
        this.Value = Value;
    }

    public double getValue() {
        return this.Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }

    public NumberNode Value(double Value) {
        this.Value = Value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof NumberNode)) {
            return false;
        }
        NumberNode numberNode = (NumberNode) o;
        return Value == numberNode.Value;
    }
    @Override
    public String toString() {
        return "{" +
            " Value='" + getValue() + "'" +
            "}";
    }

}