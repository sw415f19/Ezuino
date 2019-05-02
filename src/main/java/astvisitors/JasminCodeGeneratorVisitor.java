package astvisitors;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
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
import ast.expr.aexpr.AExpr;
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
	private List<String> currentVariableEnvironment;
	
	public JasminCodeGeneratorVisitor(PrintStream out) {
		this.out = out;
		currentVariableEnvironment = new ArrayList<String>();
		sb = new StringBuilder();
	}
	@Override
    public void visit(StartNode node) {
        node.getDcls().accept(this);
        node.getStmts().accept(this);
        out.print(sb);
    }

    @Override
    public void visit(BlockNode node) {
    	// Need to remember the state of the environment, as we cannot rollback the changes made in this block otherwise
    	List<String> oldVariableEnvironment = new ArrayList<String>(currentVariableEnvironment);
        if (node.getDclsNode() != null) {
            node.getDclsNode().accept(this);
        }
        if (node.getStmtsNode() != null) {
            node.getStmtsNode().accept(this);
        }
        if (node.getReturnstmtNode() != null) {
            node.getReturnstmtNode().accept(this);
        }
        currentVariableEnvironment = oldVariableEnvironment;
    }

    @Override
    public void visit(DclNode node) {
    		currentVariableEnvironment.add(node.getID());
    		switch(node.getType()){
    		case INT: case BOOL:
    			appendLine("iconst_0");
    			appendLine("istore " + currentVariableEnvironment.indexOf(node.getID()));
    			break;
    		case DOUBLE:
    			appendLine("dconst_0");
    			appendLine("dstore " + currentVariableEnvironment.indexOf(node.getID()));
    			break;
    		case STRING:
    			appendLine("new java/lang/String");
    			appendLine("astore " + currentVariableEnvironment.indexOf(node.getID()));
    		}
    }

    @Override
    public void visit(Assign_stmtNode node) {
        node.getExprNode().accept(this);
        switch(node.getExprNode().getType()) {
        case INT: case BOOL:
        	appendLine("istore " + currentVariableEnvironment.indexOf(node.getId()));
        	break;
        case DOUBLE:
        	appendLine("dstore " + currentVariableEnvironment.indexOf(node.getId()));
        	break;
        case STRING:
        	appendLine("astore " + currentVariableEnvironment.indexOf(node.getId()));
        }
    }

    @Override
    public void visit(Func_callExprNode node) {
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(IntegerLiteral node) {
    	appendLine("bipush " + node.getVal());
    }

    @Override
    public void visit(DoubleLiteral node) {
    	appendLine("ldc " + node.getVal());
    }

    @Override
    public void visit(BooleanLiteral node) {
    	switch(node.getBoolval().toUpperCase()) {
    	case "FALSE":
    		appendLine("bipush 1");
    		break;
    	case "TRUE":
    		appendLine("bipush 0");
    		break;
    	}
    }

    @Override
    public void visit(StringLiteral node) {
    	appendLine("ldc " + node.getVal() + "");
    }

    @Override
    public void visit(Func_defNode node) {
        for (DclNode parameter : node.getParameters()) {
            parameter.accept(this);
        }
        node.getBlockNode().accept(this);
    }

    @Override
    public void visit(Return_stmtNode node) {
        if (node.getReturnExpr() != null) {
            node.getReturnExpr().accept(this);
        }
    }

    @Override
    public void visit(If_stmtNode node) {
        node.getExpr().accept(this);
        node.getIfBlock().accept(this);
        BlockNode elseBlock = node.getElseBlock();
        if (elseBlock != null) {
            elseBlock.accept(this);
        }
    }

    @Override
    public void visit(StmtsNode node) {
        int childCount = node.getChildCount();
        for (int i = 0; i < childCount; i++) {
            node.getChild(i).accept(this);
        }
    }

    @Override
    public void visit(DclsNode node) {
        int childCount = node.getChildCount();
        for (int i = 0; i < childCount; i++) {
            node.getChild(i).accept(this);
        }
    }

    @Override
    public void visit(While_stmtNode node) {
        node.getExprNode().accept(this);
        node.getBlockNode().accept(this);
    }

    @Override
    public void visit(ParametersNode node) {

    }

    @Override
    public void visit(IdNode node) {
    }

    @Override
    public void visit(RelationalExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        
        if(node.getOperator().equals("<") || node.getOperator().equals(">=")) {
        	
        }
        else if(node.getOperator().equals(">") || node.getOperator().equals("<=")) {
        	
        }
        else {
        	throw new RuntimeException("Bad operator, should never get here");
        }
    }

    @Override
    public void visit(EqualityExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
    }

    @Override
    public void visit(ParenthesisExprNode node) {
        node.getNode().accept(this);
    }

    @Override
    public void visit(LogicalAndExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
    }

    @Override
    public void visit(AdditiveExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);

    }

    @Override
    public void visit(MultiplicativeExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);

    }

    @Override
    public void visit(UnaryExprNode node) {
        node.getNode().accept(this);

    }

    @Override
    public void visit(LogicalOrExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);

    }

    @Override
    public void visit(PrintNode node) {
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }

    }

    @Override
    public void visit(CustomFuncCallStmtNode node) {
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }

    }

	@Override
	public void visit(IntegerCastNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DoubleCastNode node) {
		// TODO Auto-generated method stub
		
	}
	
	private void appendLine(String s) {
		sb.append(s).append('\n');
	}

}
