package ast;

import ezuino.AstVisitor;

public class IntegerNode extends ValNode {

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
