package astvisitors;

import ast.*;
import ast.expr.*;
import ast.type.*;

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
		for(DclNode parameter: node.getParameters()) {
			parameter.accept(this);
		}
		
	}
	public void visit(Print_lNode node) {
		node.getExprNode().accept(this);
		
	}
	public void visit(Return_stmtNode node) {
		node.getReturnExpr().accept(this);
		
	}
	public void visit(If_stmtNode node) {
		node.getExpr().accept(this);
		node.getIfBlock().accept(this);
		if(node.getElseBlock() != null) {
			node.getElseBlock().accept(this);
		}
		checkSpecificType(node.getExpr(), Type.BOOL);
		
	}
	public void visit(StartNode node) {
		node.getDcls().accept(this);
		node.getStmts().accept(this);
		
	}
	public void visit(BooleantfNode node) {
		node.setType(Type.BOOL);
	}
	public void visit(StmtNode node) {
	
		
	}
	public void visit(StmtsNode node) {
		for(int i = 0; i < node.getChildCount(); i++) {
			node.getChild(i).accept(this);
		}
		
	}
	public void visit(DclNode node) {
		
	}
	public void visit(TypeNode node) {
		
	}
	public void visit(DclsNode node) {
		for(int i = 0; i < node.getChildCount(); i++) {
			node.getChild(i).accept(this);
		}
		
	}
	public void visit(ValNode node) {
		
	}
	public void visit(While_stmtNode node) {
		node.getExprNode().accept(this);
		node.getBlockNode().accept(this);
		checkSpecificType(node.getExprNode(), Type.BOOL);
		
	}
	public void visit(ExprNode node) {
		
	}
	public void visit(ParametersNode node) {
		
	}

	//One added assignment nodes.
	public void visit(Assign_stmtNode node) {
		node.getExprNode().accept(this);
		checkType(node, node.getExprNode());
		
	}

	@Override
	public void visit(Func_callExprNode node) {
		
	}

	@Override
	public void visit(Built_in_funcNode node) {

	}

	@Override
	public void visit(AdditiveExprNode node) {
		node.getLeftNode().accept(this);
		node.getRightNode().accept(this);
		checkType(node.getLeftNode(), node.getRightNode());
		node.setType(node.getLeftNode().getType());
		System.out.println("Checked AdditiveExprNode type!!");
	}

	@Override
	public void visit(MultiplicativeExprNode node) {
		node.getLeftNode().accept(this);
		node.getRightNode().accept(this);
		checkType(node.getLeftNode(), node.getRightNode());
		node.setType(node.getLeftNode().getType());
		System.out.println("Checked LogicalAndExprNode type!!");
	}

	@Override
	public void visit(LogicalAndExprNode node) {
		node.getLeftNode().accept(this);
		node.getRightNode().accept(this);
		checkSpecificType(node.getLeftNode(), Type.BOOL);
		checkSpecificType(node.getRightNode(), Type.BOOL);
		node.setType(Type.BOOL);
		System.out.println("Checked LogicalAndExprNode type!!");
	}

	@Override
	public void visit(RelationalExprNode node) {
		node.getLeftNode().accept(this);
		node.getRightNode().accept(this);
		checkType(node.getLeftNode(), node.getRightNode());
		node.setType(Type.BOOL);
		System.out.println("Checked RelationalExprNode type!!");
	}

	@Override
	public void visit(EqualityExprNode node) {
		node.getLeftNode().accept(this);
		node.getRelationalExprNode().accept(this);
		checkType(node.getLeftNode(), node.getRelationalExprNode());
		node.setType(Type.BOOL);
		System.out.println("Checked EqualityExprNode type!!");
	}

	private void checkType(ITypeNode leftNode, ITypeNode rightNode) {
		Type leftType = leftNode.getType();
		Type rightType = rightNode.getType();
		if(leftType == null) {
			System.err.println("left type null!");
			return;
		}
		if(rightType == null) {
			System.err.println("right type null!");
			return;
		}
		if (leftType != rightType) {
			System.err.println("Type mismatch! Left type: " + leftType.name() + " Right type: " + rightType.name() + "\nLeft node: " + leftNode + " Right node: " + rightNode);
		}	
	}
	
	private void checkSpecificType(ITypeNode node, Type expectedType) {
		Type nodeType = node.getType();
		if(nodeType == null) {
			System.err.println("Null!! :( ");
		}
		if(nodeType != expectedType) {
			System.err.println("Unexpeced type! Expected: " + expectedType.name() + ", was " + nodeType.name() + " - Node: " + node );
		}
	}
	@Override
	public void visit(ParenthesisExprNode node) {
		node.setType(node.getNode().getType());
		System.out.println("Checked ParenthesisExprNode type!!");
	}

	@Override
	public void visit(IntegerNode node) {
		node.setType(Type.INT);
	}

	@Override
	public void visit(DoubleNode node) {
		node.setType(Type.DOUBLE);
	}

	@Override
	public void visit(StringNode node) {
		node.setType(Type.STRING);
	}

	@Override
	public void visit(IdNode node) {
	}

	@Override
	public void visit(AstNode astNode) {
		super.visit(astNode);
	}
}
