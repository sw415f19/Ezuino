package ezuino;

public abstract class Node
{
    Node parent;
    Node child;

    public Node(Node parent, Node child) {
        this.parent = parent;
        this.child = child;
    }

    public Node getParent() {
        return this.parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getChild() {
        return this.child;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public Node parent(Node parent) {
        this.parent = parent;
        return this;
    }

    public Node child(Node child) {
        this.child = child;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " parent='" + getParent() + "'" +
            ", child='" + getChild() + "'" +
            "}";
    }

}