package astvisitors;

import ast.Assign_stmtNode;
import ast.BlockNode;
import ast.BooleantfNode;
import ast.DclNode;
import ast.DclsNode;
import ast.Func_callStmtNode;
import ast.Func_defNode;
import ast.If_stmtNode;
import ast.ParametersNode;
import ast.Print_lNode;
import ast.Return_stmtNode;
import ast.StartNode;
import ast.StmtNode;
import ast.StmtsNode;
import ast.Type;
import ast.TypeNode;
import ast.While_stmtNode;
import ast.expr.ExprNode;
import ast.type.ValNode;

public class Typechecker extends AstVisitor{
	public void visit(Func_callStmtNode node) {
		
	}
	public void visit(BlockNode node) {
		if(node.getDclsNode() != null) {
			node.getDclsNode().accept(this);
		}
		
		if(node.getStmtsNode() != null) {
			node.getStmtsNode().accept(this);
		}
		
		if(node.getReturnstmtNode() != null) {
			node.getReturnstmtNode().accept(this);
		}
	}
	public void visit(Func_defNode node) {
		node.getBlockNode().accept(this);
		Return_stmtNode returnNode = node.getBlockNode().getReturnstmtNode();
		String funcType = node.getType();
		
		/* Okay, jeg kom ikke videre end hertil, da vi skal have en måde at tjekke på, om retur har samme type som
		 * funktionsdefinitionen. At tjekke på enum er en mulighed, men det bliver nok også træls, hvis vi vil
		 * have sammensatte typer med i vores sprog.
		 */
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
}
