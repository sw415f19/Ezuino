package ast.type;
import ezuino.AstVisitor;

public class IdNode extends ValNode {

    private String val;

    public IdNode(String val) {
        System.out.println("Created ID Node");
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
