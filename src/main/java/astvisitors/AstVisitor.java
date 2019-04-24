package astvisitors;

import ast.*;
import ast.expr.*;
import ast.funcallstmt.CustomFuncCallStmtNode;
import ast.funcallstmt.Func_callStmtNode;
import ast.funcallstmt.PrintNode;
import ast.type.*;

public abstract class AstVisitor {

    public abstract void visit(Func_callExprNode node);
    public abstract void visit(BlockNode node);
    public abstract void visit(Func_defNode node);
    public abstract void visit(Return_stmtNode node);
    public abstract void visit(If_stmtNode node);
    public abstract void visit(StartNode node);
    public abstract void visit(BooleanLiteral node);
    public abstract void visit(StmtsNode node);
    public abstract void visit(DclNode node);
    public abstract void visit(DclsNode node);
    public abstract void visit(While_stmtNode node);
    public abstract void visit(ParametersNode node);
    public abstract void visit(AdditiveExprNode node);
    public abstract void visit(MultiplicativeExprNode node);
    public abstract void visit(LogicalAndExprNode node);
    public abstract void visit(LogicalOrExprNode node);
    public abstract void visit(RelationalExprNode node);
    public abstract void visit(EqualityExprNode node);
    public abstract void visit(ParenthesisExprNode node);
    public abstract void visit(UnaryExprNode node);
    public abstract void visit(PrintNode node);
    public abstract void visit(CustomFuncCallStmtNode node);


    //One added assignment nodes.
    public abstract void visit(Assign_stmtNode node);


    //"Type"
    public abstract void visit(IntegerLiteral node);
    public abstract void visit(DoubleLiteral node);
    public abstract void visit(StringLiteral node);
    public abstract void visit(IdNode node);



    public void visit(AstNode astNode) {
		System.err.println("Compiler implementation error: Dont know how to visit: " + astNode.toString());
    }

}