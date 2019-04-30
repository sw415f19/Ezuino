package astvisitors;

import java.io.PrintStream;

import ast.Assign_stmtNode;
import ast.BlockNode;
import ast.BooleanLiteral;
import ast.DclNode;
import ast.DclsNode;
import ast.Func_defNode;
import ast.If_stmtNode;
import ast.ParametersNode;
import ast.Return_stmtNode;
import ast.StartNode;
import ast.StmtsNode;
import ast.While_stmtNode;
import ast.expr.AdditiveExprNode;
import ast.expr.EqualityExprNode;
import ast.expr.Func_callExprNode;
import ast.expr.LogicalAndExprNode;
import ast.expr.LogicalOrExprNode;
import ast.expr.MultiplicativeExprNode;
import ast.expr.ParenthesisExprNode;
import ast.expr.RelationalExprNode;
import ast.expr.UnaryExprNode;
import ast.funcallstmt.CustomFuncCallStmtNode;
import ast.funcallstmt.PrintNode;
import ast.funcallstmt.cast.DoubleCastNode;
import ast.funcallstmt.cast.IntegerCastNode;
import ast.type.DoubleLiteral;
import ast.type.IdNode;
import ast.type.IntegerLiteral;
import ast.type.StringLiteral;

public class JasminCodeGeneratorVisitor extends AstVisitor{

	private PrintStream out;
	private StringBuilder sb;
	
	public JasminCodeGeneratorVisitor(PrintStream out) {
		this.out = out;
	}
	@Override
	public void visit(Func_callExprNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BlockNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Func_defNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Return_stmtNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(If_stmtNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(StartNode node) {
		// TODO Auto-generated method stub
		// Make the entire code for string builder, then print it as the last thing
		out.print(sb);
	}

	@Override
	public void visit(BooleanLiteral node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(StmtsNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DclNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DclsNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(While_stmtNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ParametersNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(AdditiveExprNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(MultiplicativeExprNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(LogicalAndExprNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(LogicalOrExprNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(RelationalExprNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(EqualityExprNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ParenthesisExprNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(UnaryExprNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(PrintNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(CustomFuncCallStmtNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IntegerCastNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DoubleCastNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Assign_stmtNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IntegerLiteral node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DoubleLiteral node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(StringLiteral node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IdNode node) {
		// TODO Auto-generated method stub
		
	}

}
