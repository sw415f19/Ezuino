package astvisitors;

import java.io.ByteArrayOutputStream;
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
import ast.expr.aexpr.AExpr;
import ast.funcallstmt.CustomFuncCallStmtNode;
import ast.funcallstmt.ListAddNode;
import ast.funcallstmt.ListRemoveNode;
import ast.funcallstmt.PrintNode;
import ast.type.DoubleLiteral;
import ast.type.IdNode;
import ast.type.IntegerLiteral;
import ast.type.StringLiteral;

public class JavaBytecodeGeneratorVisitor extends AstVisitor {

	private StringBuilder stringBuilder;
	private PrintStream out;
	public JavaBytecodeGeneratorVisitor(PrintStream out) {
		this.out = out;
	}
	
    @Override
    public void visit(StartNode node) {
        node.getDcls().accept(this);
        node.getStmts().accept(this);
        out.print(stringBuilder);
    }

    @Override
    public void visit(BlockNode node) {
        if (node.getDclsNode() != null) {
            node.getDclsNode().accept(this);
        }
        if (node.getStmtsNode() != null) {
            node.getStmtsNode().accept(this);
        }
        if (node.getReturnstmtNode() != null) {
            node.getReturnstmtNode().accept(this);
        }
    }

    @Override
    public void visit(DclNode node) {

    }

    @Override
    public void visit(Assign_stmtNode node) {
        node.getExprNode().accept(this);
    }

    @Override
    public void visit(Func_callExprNode node) {
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(IntegerLiteral node) {

    }

    @Override
    public void visit(DoubleLiteral node) {

    }

    @Override
    public void visit(BooleanLiteral node) {

    }

    @Override
    public void visit(StringLiteral node) {

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
    }

    @Override
    public void visit(EqualityExprNode node) {
        node.getLeftNode().accept(this);
        node.getRelationalExprNode().accept(this);
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
    public void visit(ListAddNode node) {
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(ListRemoveNode node) {
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
    }
}
