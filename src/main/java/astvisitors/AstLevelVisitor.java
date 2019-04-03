package astvisitors;


import ast.*;
import ast.expr.AdditiveExprNode;
import ast.expr.ExprNode;
import ast.expr.MultiplicativeExprNode;
import ast.type.*;

public abstract class AstLevelVisitor {
	
	public abstract void visitLevel(Func_callNode node, int level);
	public abstract void visitLevel(BlockNode node, int level);
	public abstract void visitLevel(Func_defNode node, int level);
	public abstract void visitLevel(Func_Call_ParamNode node, int level);
	public abstract void visitLevel(Print_lNode node, int level);
	public abstract void visitLevel(Return_stmtNode node, int level);
	public abstract void visitLevel(If_stmtNode node, int level);
	public abstract void visitLevel(StartNode node, int level);
	public abstract void visitLevel(BooleantfNode node, int level);
	public abstract void visitLevel(StmtsNode node, int level);
	public abstract void visitLevel(DclNode node, int level);
	public abstract void visitLevel(TypeNode node, int level);
	public abstract void visitLevel(DclsNode node, int level);
	public abstract void visitLevel(While_stmtNode node, int level);
	public abstract void visitLevel(ExprNode node, int level);
	public abstract void visitLevel(ParametersNode node, int level);
	public abstract void visitLevel(Built_in_funcNode node, int level);
	public abstract void visitLevel(AdditiveExprNode node, int level);
	public abstract void visitLevel(MultiplicativeExprNode node, int level);
	
	//One added assignment nodes.
	public abstract void visitLevel(Assign_stmtNode node, int level);

	
	//"Type"
	public abstract void visitLevel(IntegerNode node, int level);
	public abstract void visitLevel(DoubleNode node, int level);
	public abstract void visitLevel(StringNode node, int level);
	public abstract void visitLevel(IdNode node, int level);
	
	
	
	public void visitLevel(AstNode astNode, int level) {
		System.out.println("In ASTNode visit:\t" + astNode);
		astNode.acceptLevel(this, level);
	}

}
