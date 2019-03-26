package ezuino;

import ast.*;

import java.util.ArrayList;

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
    public void visit(Block_switchNode node) {

    }

    @Override
    public void visit(If_elseNode node) {

    }

    @Override
    public void visit(Return_stmtNode node) {

    }

    @Override
    public void visit(Boolean_exprNode node) {

    }

    @Override
    public void visit(If_stmtNode node) {

    }

    @Override
    public void visit(StartNode node) {
        BuildAstVisitor buildAstVisitor = new BuildAstVisitor();
        ArrayList<AstNode> ast =  buildAstVisitor.getAst();
        System.out.println("Size of ast: " + ast.size());
        for(AstNode thisNode : ast){
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
    public void visit(Comparator_operatorNode node) {

    }

    @Override
    public void visit(List_idNode node) {

    }

    @Override
    public void visit(StmtsNode node) {

    }

    @Override
    public void visit(ConditionNode node) {

    }

    @Override
    public void visit(ListNode node) {

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
    public void visit(List_sizeNode node) {

    }

    @Override
    public void visit(ValNode node) {

    }

    @Override
    public void visit(Else_stmtNode node) {

    }

    @Override
    public void visit(Logic_operatorNode node) {

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
    public void visit(Assign_booleanNode node) {
        System.out.println("Hallo world---------------");
    }

    @Override
    public void visit(Assign_stmtNode node) {

    }

    @Override
    public void visit(Assign_exprNode node) {

    }

    @Override
    public void visit(Assign_conditionNode node) {

    }
}
