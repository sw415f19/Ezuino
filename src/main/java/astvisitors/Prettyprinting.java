package astvisitors;

import ast.*;
import ast.expr.ExprNode;
import ast.type.ValNode;

public class Prettyprinting extends AstVisitor {

    @Override
    public void visit(Func_callNode node) {

    }

    @Override
    public void visit(ParamNode node) {

    }

    @Override
    public void visit(BlockNode node) {

    }

    @Override
    public void visit(Func_defNode node) {

    }

    @Override
    public void visit(Print_lNode node) {

    }

    @Override
    public void visit(Switch_blockNode node) {

    }

    @Override
    public void visit(Return_stmtNode node) {

    }

    @Override
    public void visit(If_stmtNode node) {

    }

    @Override
    public void visit(StartNode node) {
        for (AstNode thisNode : node.getAst()) {
            System.out.println("It loops");
            thisNode.accept(this);
        }
    }

    @Override
    public void visit(BooleantfNode node) {

    }

    @Override
    public void visit(List_addNode node) {

    }

    @Override
    public void visit(StmtNode node) {

    }

    @Override
    public void visit(StmtsNode node) {

    }

    @Override
    public void visit(Switch_stmtNode node) {

    }

    @Override
    public void visit(DclNode node) {

    }

    @Override
    public void visit(List_removeNode node) {

    }

    @Override
    public void visit(TypeNode node) {

    }

    @Override
    public void visit(DclsNode node) {

    }

    @Override
    public void visit(ValNode node) {

    }

    @Override
    public void visit(While_stmtNode node) {

    }

    @Override
    public void visit(ExprNode node) {

    }

    @Override
    public void visit(ParametersNode node) {

    }

    @Override
    public void visit(AstNode astNode) {
        super.visit(astNode);
    }

    @Override
    public void visit(Assign_stmtNode node) {

    }

    @Override
    public void visit(Int_dclNode node) {

    }

    @Override
    public void visit(Double_dclNode node) {

    }

    @Override
    public void visit(Boolean_dclNode node) {

    }

    @Override
    public void visit(String_dclNode node) {

    }
}