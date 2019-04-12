package astvisitors;

import ast.*;
import ast.expr.*;
import ast.type.*;

public abstract class AstVisitor {

    public abstract void visit(Func_callStmtNode node);
    public abstract void visit(Func_callExprNode node);
    public abstract void visit(BlockNode node);
    public abstract void visit(Func_defNode node);
    public abstract void visit(Return_stmtNode node);
    public abstract void visit(If_stmtNode node);
    public abstract void visit(StartNode node);
    public abstract void visit(BooleantfNode node);
    public abstract void visit(StmtsNode node);
    public abstract void visit(DclNode node);
    public abstract void visit(TypeNode node);
    public abstract void visit(DclsNode node);
    public abstract void visit(While_stmtNode node);
    public abstract void visit(ExprNode node);
    public abstract void visit(ParametersNode node);
    public abstract void visit(AdditiveExprNode node);
    public abstract void visit(MultiplicativeExprNode node);
    public abstract void visit(LogicalAndExprNode node);
    public abstract void visit(RelationalExprNode node);
    public abstract void visit(EqualityExprNode node);
    public abstract void visit(ParenthesisExprNode node);


    //One added assignment nodes.
    public abstract void visit(Assign_stmtNode node);


    //"Type"
    public abstract void visit(IntegerNode node);
    public abstract void visit(DoubleNode node);
    public abstract void visit(StringNode node);
    public abstract void visit(IdNode node);



    public void visit(AstNode astNode) {
        System.out.println("In ASTNode visit:\t" + astNode);
        astNode.accept(this);
    }

}