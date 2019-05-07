package astvisitors;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
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
import ast.Type;
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
	private int labelCounter = 0;
	private int currentStackSize = 0;
	private int maxStackSize = 0;
	
	public JasminCodeGeneratorVisitor(PrintStream out) {
		this.out = out;
		currentVariableEnvironment = new ArrayList<String>();
		sb = new StringBuilder();
	}
	@Override
    public void visit(StartNode node) {
		appendLine(".class public program");
		appendLine(".super java/lang/Object");
		appendLine(".method public static main([Ljava/lang/String;)V");
        node.getDcls().accept(this);
        node.getStmts().accept(this);
        appendLine(".limit stack " + maxStackSize);
        appendLine(".end method");
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
    		incrementStack();
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
    		decrementStack();
    		
    }

    @Override
    public void visit(Assign_stmtNode node) {
        node.getExprNode().accept(this);
        switch(node.getExprNode().getType()) {
        case INT: case BOOL:
        	appendLine("istore " + currentVariableEnvironment.lastIndexOf(node.getId()));
        	
        	break;
        case DOUBLE:
        	appendLine("dstore " + currentVariableEnvironment.lastIndexOf(node.getId()));
        	break;
        case STRING:
        	appendLine("astore " + currentVariableEnvironment.lastIndexOf(node.getId()));
        }
        decrementStack();
    }

    @Override
    public void visit(Func_callExprNode node) {
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(IntegerLiteral node) {
    	incrementStack();
    	appendLine("bipush " + node.getVal());
    }

    @Override
    public void visit(DoubleLiteral node) {
    	incrementStack();
    	appendLine("ldc " + node.getVal());
    }

    @Override
    public void visit(BooleanLiteral node) {
    	incrementStack();
    	switch(node.getBoolval().toUpperCase()) {
    	case "FALSE":
    		appendLine("bipush 0");
    		break;
    	case "TRUE":
    		appendLine("bipush 1");
    		break;
    	}
    }

    @Override
    public void visit(StringLiteral node) {
    	incrementStack();
    	appendLine("ldc " + node.getVal());
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

    	BlockNode elseBlock = node.getElseBlock();
    	if(elseBlock == null) {
    		int skipLabel = getNextLabel();
    		node.getExpr().accept(this);
    		appendLine("ifeq " + skipLabel); //if condition is false, skip (eq checks top of stack for a 0)
    		decrementStack();
    		node.getIfBlock().accept(this);
    		append("skipLabel" +": ");
    	}
    	else {
    		int trueLabel = getNextLabel();
    		int endLabel = getNextLabel();
    		node.getExpr().accept(this);
    		appendLine("ifne " + trueLabel); //if condition is true, go to if block
    		decrementStack();
    		elseBlock.accept(this);
    		appendLine("goto " + endLabel);
    		append(trueLabel + ": ");
    		node.getIfBlock().accept(this);
    		append(endLabel + ": ");
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
    	int beginLabel = getNextLabel();
    	int enterLabel = getNextLabel();
    	appendLine("goto " + enterLabel);
    	append(beginLabel + ": ");
        node.getBlockNode().accept(this);
        append(enterLabel + ": ");
        node.getExprNode().accept(this);
        appendLine("ifne " + beginLabel);
        decrementStack();
        //There is no iftrue command, so we have to use ifne instead, which jumps if the stack head is not 0 (true is a non-zero value)
        //Per typechecker, this value should be a "boolean", and not an "integer".
    }

    @Override
    public void visit(ParametersNode node) {

    }

    @Override
    public void visit(IdNode node) {
    	incrementStack();
        switch(node.getType()) {
        case INT: case BOOL:
        	appendLine("iload " + currentVariableEnvironment.lastIndexOf(node.getVal()));
        	break;
        case DOUBLE:
        	appendLine("dload" + currentVariableEnvironment.lastIndexOf(node.getVal()));
        	break;
        case STRING:
        	appendLine("aload " + currentVariableEnvironment.lastIndexOf(node.getVal()));
        	break;
        	
        }
    }

    @Override
    public void visit(RelationalExprNode node) {
    	int trueLabel = getNextLabel();
    	int endLabel = getNextLabel();
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        Type comparedType = node.getLeftNode().getType();
        if(comparedType.equals(Type.INT)) {
        	appendLine("lcmp");
        }
        else {
        	appendLine("dcmpg");
        }
        switch(node.getOperator()) {
        case "<":
        	appendLine("iflt " + trueLabel);
        	break;
        case ">":
        	appendLine("ifgt" + trueLabel);
        	break;
        case "<=":
        	appendLine("ifle" + trueLabel);
        	break;
        case ">=":
        	appendLine("ifge " + trueLabel);
        	break;
        }
        appendLine("iconst_0");
        appendLine("goto " + endLabel);
        append(trueLabel + ": ");
        appendLine("iconst_1");
        append(endLabel + ": ");
        decrementStack();
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
	private void append(String s) {
		sb.append(s);
	}
	private int getNextLabel() {
		return labelCounter++;
	}
	private void incrementStack() {
		currentStackSize++;
		if(currentStackSize > maxStackSize) {
			maxStackSize = currentStackSize;
		}
	}
	private void decrementStack() {
		currentStackSize--;
		if (currentStackSize < 0) {
			currentStackSize = 0;
		}
	}
}
