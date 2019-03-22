package ezuino;

import ast.*;

public abstract class AstVisitor {

	public abstract void visit(Func_callNode node);
	public abstract void visit(ParamNode node);
	public abstract void visit(BlockNode node);
	public abstract void visit(FuncNode node);
	public abstract void visit(Print_lNode node);
	public abstract void visit(Block_switchNode node);
	public abstract void visit(If_elseNode node);
	public abstract void visit(Return_stmtNode node);
	public abstract void visit(Boolean_exprNode node);
	public abstract void visit(If_stmtNode node);
	public abstract void visit(StartNode node);
	public abstract void visit(BooleantfNode node);
	public abstract void visit(List_addNode node);
	public abstract void visit(StmtNode node);
	public abstract void visit(Comparator_operatorNode node);
	public abstract void visit(List_idNode node);
	public abstract void visit(StmtsNode node);
	public abstract void visit(ConditionNode node);
	public abstract void visit(ListNode node);
	public abstract void visit(Switch_stmtNode node);
	public abstract void visit(DclNode node);
	public abstract void visit(List_removeNode node);
	public abstract void visit(TypeNode node);
	public abstract void visit(DclsNode node);
	public abstract void visit(List_sizeNode node);
	public abstract void visit(ValNode node);
	public abstract void visit(Else_stmtNode node);
	public abstract void visit(Logic_operatorNode node);
	public abstract void visit(While_stmtNode node);
	public abstract void visit(ExprNode node);
	public abstract void visit(ParametersNode node);


    public void visit(AstNode astNode) {
    	System.out.println("In ASTNode visit:\t" + astNode);
    	astNode.accept(this);
    }

}
