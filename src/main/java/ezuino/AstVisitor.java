package ezuino;

import ast.AstNode;
import ast.DclsNode;
import ast.StartNode;
import ast.StmtsNode;

public abstract class AstVisitor<T> {

    public abstract T visit(StartNode startNode);
    public abstract T visit(DclsNode dclsNode);
    public abstract T visit(StmtsNode stmtsNode);

    public T visit(AstNode astNode) {
        if(astNode instanceof StartNode) {
            return visit((StartNode) astNode);
        }
        else if(astNode instanceof DclsNode) {
            return visit((DclsNode) astNode);
        }
        else if(astNode instanceof StmtsNode) {
            return visit((StmtsNode) astNode);
        }
        return null;
    }

}
