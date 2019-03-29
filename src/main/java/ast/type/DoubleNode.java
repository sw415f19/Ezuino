package ast.type;
import ezuino.AstVisitor;

public class DoubleNode extends ValNode {

    private String val;

    public DoubleNode(String val) {
        System.out.println("Created Double Node " + val);
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
