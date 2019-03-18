package ezuino;

public class AddictiveNode
{
    int rightNode;
    int leftNode;

    public AddictiveNode(int rightNode, int leftNode) {
        this.rightNode = rightNode;
        this.leftNode = leftNode;
    }

    public int getRightNode() {
        return this.rightNode;
    }

    public void setRightNode(int rightNode) {
        this.rightNode = rightNode;
    }

    public int getLeftNode() {
        return this.leftNode;
    }

    public void setLeftNode(int leftNode) {
        this.leftNode = leftNode;
    }

    public AddictiveNode rightNode(int rightNode) {
        this.rightNode = rightNode;
        return this;
    }

    public AddictiveNode leftNode(int leftNode) {
        this.leftNode = leftNode;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AddictiveNode)) {
            return false;
        }
        AddictiveNode addictiveNode = (AddictiveNode) o;
        return rightNode == addictiveNode.rightNode && leftNode == addictiveNode.leftNode;
    }


    @Override
    public String toString() {
        return "{" +
            " rightNode='" + getRightNode() + "'" +
            ", leftNode='" + getLeftNode() + "'" +
            ", Total='" + (getRightNode()+getLeftNode()) + "'" +
            "}";
    }
}