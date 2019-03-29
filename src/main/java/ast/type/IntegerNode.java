package ast.type;
import ezuino.AstVisitor;
public class IntegerNode extends ValNode {

    private String val;

    public IntegerNode(String val) {
        System.out.println("Created Integer Node");
        this.val = val;
    }

    public String getVal() {
        return this.val;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
