package ast.arduino;

import java.util.List;

import ast.BlockNode;
import ast.DclNode;
import ast.Func_defNode;
import ast.Type;
import astvisitors.AstVisitor;

public class LoopNode extends Func_defNode {

    public LoopNode(String ID, Type type, List<DclNode> parameters, BlockNode blockNode) {
        super(ID, type, parameters, blockNode);
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);

    }

}
