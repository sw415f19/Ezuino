package ast;

import ezuino.AstVisitor;

public class SymDeclaring extends AstNode {
    String id;
    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }


}
