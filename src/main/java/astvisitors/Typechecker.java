package astvisitors;

import ast.*;
import ast.expr.*;
import ast.type.*;

public class Typechecker extends AstVisitor{
	public void visit(Func_callStmtNode node) {
		
	}
	public void visit(BlockNode node) {
		
	}
	public void visit(Func_defNode node) {
		
	}
	public void visit(Print_lNode node) {
		
	}
	public void visit(Return_stmtNode node) {
		
	}
	public void visit(If_stmtNode node) {
		
	}
	public void visit(StartNode node) {
		
	}
	public void visit(BooleantfNode node) {
		
	}
	public void visit(StmtNode node) {
		
	}
	public void visit(StmtsNode node) {
		
	}
	public void visit(DclNode node) {
		
	}
	public void visit(TypeNode node) {
		
	}
	public void visit(DclsNode node) {
		
	}
	public void visit(ValNode node) {
		
	}
	public void visit(While_stmtNode node) {
		
	}
	public void visit(ExprNode node) {
		
	}
	public void visit(ParametersNode node) {
		
	}

	//One added assignment nodes.
	public void visit(Assign_stmtNode node) {
		
	}

	@Override
	public void visit(Func_callExprNode node) {
		
	}

	@Override
	public void visit(Built_in_funcNode node) {

	}

	@Override
	public void visit(AdditiveExprNode node) {

	}

	@Override
	public void visit(MultiplicativeExprNode node) {

	}

	@Override
	public void visit(LogicalAndExprNode node) {

	}

	@Override
	public void visit(RelationalExprNode node) {

	}

	@Override
	public void visit(EqualityExprNode node) {

	}

	@Override
	public void visit(ParenthesisExprNode node) {

	}

	@Override
	public void visit(IntegerNode node) {

	}

	@Override
	public void visit(DoubleNode node) {

	}

	@Override
	public void visit(StringNode node) {

	}

	@Override
	public void visit(IdNode node) {

	}

	@Override
	public void visit(AstNode astNode) {
		super.visit(astNode);
	}
}
