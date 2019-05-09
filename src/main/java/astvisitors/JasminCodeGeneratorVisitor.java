package astvisitors;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import ast.Assign_stmtNode;
import ast.AstNode;
import ast.BlockNode;
import ast.BooleanLiteral;
import ast.DclNode;
import ast.DclsNode;
import ast.Func_defNode;
import ast.If_stmtNode;
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
	private StringBuilder functionStringBuilder; //needed to remember the functions and declare them after main
	private List<String> currentVariableEnvironment;
	private List<String> currentLocalFunctions;
	private String programName;
	private int labelCounter = 0;
	private int currentStackSize = 0;
	private int maxStackSize = 1;
	private int maxLocals = 1;
	
	public JasminCodeGeneratorVisitor(PrintStream out) {
		this(out, "program");
	}
	
	public JasminCodeGeneratorVisitor(PrintStream out, String programName) {
		this.out = out;
		currentVariableEnvironment = new ArrayList<String>();
		currentLocalFunctions = new ArrayList<String>();
		sb = new StringBuilder();
		functionStringBuilder = new StringBuilder();
		this.programName = programName;
		
	}
	@Override
    public void visit(StartNode node) {
		generatePrefixSetupCode();
        node.getDcls().accept(this);
        node.getStmts().accept(this);
        generatePostfixSetupCode();
        out.print(sb);
    }

    @Override
    public void visit(BlockNode node) {
    	// Need to remember the state of the environment, as we cannot rollback the changes made in this block otherwise
    	List<String> oldVariableEnvironment = new ArrayList<String>(currentVariableEnvironment);
    	List<String> oldLocalFunctions = new ArrayList<String>(currentLocalFunctions);
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
        currentLocalFunctions = oldLocalFunctions;
    }

    @Override
    public void visit(DclNode node) {
    		currentVariableEnvironment.add(node.getID());
    		if(maxLocals < currentVariableEnvironment.size() + currentLocalFunctions.size()) {
    			maxLocals = currentVariableEnvironment.size() + currentLocalFunctions.size();
    		}
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
        append("invokestatic program/");
        generateFunctionSignature(node);
        if(!currentLocalFunctions.contains(node.getID())) {
        	currentLocalFunctions.add(node.getID());
        	maxLocals = Math.max(maxLocals, currentLocalFunctions.size() + currentVariableEnvironment.size());
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
    	//made under the assumption that there are no duplicate IDs
    	StringBuilder oldStringBuilder = sb;
    	sb = functionStringBuilder;
    	List<String> oldVariableEnvironment = currentVariableEnvironment;
    	int oldStackSize = currentStackSize;
    	int oldMaxStack = maxStackSize;
    	int oldMaxLocals = maxLocals;
    	currentStackSize = 0;
    	maxStackSize = 1;
    	maxLocals = 1;
    	currentVariableEnvironment = new ArrayList<String>();
    	
    	append(".method public static ");
    	generateFunctionSignature(node);
        for (DclNode parameter : node.getParameters()) {
            currentVariableEnvironment.add(parameter.getID());
            maxLocals++;
        }
        node.getBlockNode().accept(this);
        appendLine(".limit stack " + maxStackSize);
        appendLine(".limit locals " + maxLocals);
        appendLine(".end method");
        currentVariableEnvironment = oldVariableEnvironment;
        sb = oldStringBuilder;
        maxStackSize = oldMaxStack;
        currentStackSize = oldStackSize;
        maxLocals = oldMaxLocals;
    }

    @Override
    public void visit(Return_stmtNode node) {
        if (node.getReturnExpr() != null) {
            node.getReturnExpr().accept(this);
            switch(node.getType()) {
            case INT:
            	appendLine("ireturn");
            	break;
            case DOUBLE:
            	appendLine("dreturn");
            	break;
            case BOOL:
            	appendLine("ireturn");
            	break;
            case STRING:
            	appendLine("areturn");
            	break;
            }
        }
        else {
        	appendLine("return");
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
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        Type comparedType = node.getLeftNode().getType();
        
        appendComparisonBasedOnType(comparedType);
        appendConditionalJump(node.getOperator(), trueLabel);
        appendConditionalBooleanToStack(trueLabel);
    }

    @Override
    public void visit(EqualityExprNode node) {
    	int trueLabel = getNextLabel();
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        Type comparedType = node.getLeftNode().getType();
        appendComparisonBasedOnType(comparedType);
        appendConditionalJump(node.getOperator(), trueLabel);
        appendConditionalBooleanToStack(trueLabel);        
    }

    @Override
    public void visit(ParenthesisExprNode node) {
        node.getNode().accept(this);
    }

    @Override
    public void visit(LogicalAndExprNode node) {	
        node.getLeftNode().accept(this);
        generateCodeForLogicalNegation();
        node.getRightNode().accept(this);
        generateCodeForLogicalNegation();
        generateCodeForLogicalOr();
        generateCodeForLogicalNegation();
        //my group: You can't just use formal logic to rewrite code you dislike
        //DeMorgan's Laws: That's where you're wrong, kiddo
        
    }

    @Override
    public void visit(AdditiveExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        switch(node.getType()) {
        case INT:
        	if(node.getOperator().equals("+")) {
        		appendLine("iadd");
        	}
        	else {
        		appendLine("isub");
        	}
        	break;
        case DOUBLE:
        	if(node.getOperator().equals("+")) {
        		appendLine("dadd");
        	}
        	else{
        		appendLine("dsub");
        	}
        	break;
        case STRING:
        	appendLine("invokevirtual java/lang/String.concat(Ljava/lang/String;)Ljava/lang/String;");
        	break;
        }

    }

    @Override
    public void visit(MultiplicativeExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        switch(node.getType()) {
        case INT:
        	if(node.getOperator().equals("*")) {
        		appendLine("imul");
        	}
        	else {
        		appendLine("idiv");
        	}
        	break;
        case DOUBLE:
        	if(node.getOperator().equals("*")) {
        		appendLine("dmul");
        	}
        	else {
        		appendLine("ddiv");
        	}
        	break;
        }
    }

    @Override
    public void visit(UnaryExprNode node) {
        node.getNode().accept(this);
        if(node.getOperator().equals("-")) {
        	switch(node.getType()) {
        	case INT: case BOOL:
        		appendLine("ineg");
        		break;
        	case DOUBLE:
        		appendLine("dneg");
        		break;
        	}
        }
        else {
        	generateCodeForLogicalNegation();
        }

    }

    @Override
    public void visit(LogicalOrExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        generateCodeForLogicalOr();
    }

    @Override
    public void visit(PrintNode node) {
    	incrementStack();
    	appendLine("getstatic java/lang/System/out Ljava/io/PrintStream;");
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
        appendLine("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
        decrementStack();
        decrementStack();
    }

    @Override
    public void visit(CustomFuncCallStmtNode node) {
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
        
        append("invokestatic program/");
        generateFunctionSignature(node);
        if(!currentLocalFunctions.contains(node.getId())) {
        	currentLocalFunctions.add(node.getId());
        	maxLocals = Math.max(maxLocals, currentLocalFunctions.size() + currentVariableEnvironment.size());
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
	private void appendComparisonBasedOnType(Type comparedType) {
		switch(comparedType) {
		case INT:
        	appendLine("lcmp");
        	break;
		case DOUBLE:
			appendLine("dcmpg");
			break;
		case STRING:
			appendLine("java/lang/String.compareTo:(Ljava/lang/String;)I");
			break;
        }
		decrementStack();
	}
	private void appendConditionalJump(String conditionOperator, int label) {
		switch(conditionOperator) {
        case "<":
        	appendLine("iflt " + label);
        	break;
        case ">":
        	appendLine("ifgt" + label);
        	break;
        case "<=":
        	appendLine("ifle" + label);
        	break;
        case ">=":
        	appendLine("ifge " + label);
        	break;
        case "=":
        	appendLine("ifeq " + label);
        	break;
        case "!=":
        	appendLine("ifne " + label);
        	break;
        }
		decrementStack();
	}
	private void appendConditionalBooleanToStack(int trueLabel){
		//Jump to truelabel to get a true value on stack, otherwise just get a false falue
		int endLabel = getNextLabel();
		appendLine("iconst_0");
        appendLine("goto " + endLabel);
        append(trueLabel + ": ");
        appendLine("iconst_1");
        append(endLabel + ": ");
        incrementStack();
	}
	private void generateCodeForLogicalOr() {
		int trueLabel = getNextLabel();
    	int endLabel = getNextLabel();
		appendLine("ior");
        decrementStack();
        appendLine("iconst_0");
        incrementStack();
        appendLine("if_icmpne " + trueLabel); //if the ior doesn't result in a zero-byte
        decrementStack();
        decrementStack();
        appendLine("iconst_0");
        appendLine("goto " + endLabel);
        append(trueLabel + ": ");
        appendLine("iconst_1");
        append(endLabel + ": ");
        incrementStack();
	}
	private void generateCodeForLogicalNegation() {
		int trueLabel = getNextLabel();
		int endLabel = getNextLabel();
		appendLine("ifne " + trueLabel);
		appendLine("iconst_0");
		appendLine("goto " + endLabel);
		append(trueLabel + ": ");
		appendLine("iconst_1");
		append(endLabel + ": ");
		
	}
	private void generateFunctionSignature(AstNode functionNode) {
		if(functionNode instanceof Func_callExprNode) {
			generateSignatureForExprFunc((Func_callExprNode)functionNode);
		}
		else if(functionNode instanceof Func_defNode) {
			generateSignatureForFuncDef((Func_defNode)functionNode);
		}
		else if(functionNode instanceof CustomFuncCallStmtNode) {
			generateSignatureForStmtFunc((CustomFuncCallStmtNode)functionNode);
		}
	}
	private void generateSignatureForStmtFunc(CustomFuncCallStmtNode node) {
		append(node.getId());
		append("(");
		for (AExpr parameter : node.getParameters()) {
            switch(parameter.getType()) {
            case INT: case BOOL:
            	append("I");
            	break;
            case DOUBLE:
            	append("D");
            	break;
            case STRING:
            	append("Ljava/lang/String;");
            	break;
            }
            ;
        }
        append(")");
        switch(node.getType()) {
        case INT: case BOOL:
        	appendLine("I");
        	break;
        case DOUBLE:
        	appendLine("D");
        	break;
        case STRING:
        	appendLine("Ljava/lang/String;");
        	break;
        case VOID:
        	appendLine("V");
        	break;
        }
 
	}
	private void generateSignatureForFuncDef(Func_defNode node) {
		append(node.getId());
		append("(");
		for (DclNode parameter : node.getParameters()) {
            switch(parameter.getType()) {
            case INT: case BOOL:
            	append("I");
            	break;
            case DOUBLE:
            	append("D");
            	break;
            case STRING:
            	append("Ljava/lang/String;");
            	break;
            }
            ;
        }
        append(")");
        switch(node.getType()) {
        case INT: case BOOL:
        	appendLine("I");
        	break;
        case DOUBLE:
        	appendLine("D");
        	break;
        case STRING:
        	appendLine("Ljava/lang/String;");
        	break;
        case VOID:
        	appendLine("V");
        	break;
        }
		
	}
	private void generateSignatureForExprFunc(Func_callExprNode node) {
		append(node.getID());
		append("(");
		for (AExpr parameter : node.getParameters()) {
            switch(parameter.getType()) {
            case INT: case BOOL:
            	append("I");
            	break;
            case DOUBLE:
            	append("D");
            	break;
            case STRING:
            	append("Ljava/lang/String;");
            	break;
            }
            ;
        }
        append(")");
        switch(node.getType()) {
        case INT: case BOOL:
        	appendLine("I");
        	break;
        case DOUBLE:
        	appendLine("D");
        	break;
        case STRING:
        	appendLine("Ljava/lang/String;");
        	break;
        case VOID:
        	appendLine("V");
        	break;
        }
	}
	private void generatePrefixSetupCode() {
		appendLine(".class public " + programName);
		appendLine(".super java/lang/Object");
		appendLine(".method public static main([Ljava/lang/String;)V");
	}
	private void generatePostfixSetupCode() {
        appendLine("return");
        appendLine(".limit stack " + maxStackSize);
        appendLine(".limit locals " + maxLocals);
        appendLine(".end method");
        sb.append(functionStringBuilder);
	}
}
