package astvisitors;

import ast.*;
import ast.expr.*;
import ast.funcallstmt.CustomFuncCallStmtNode;
import ast.funcallstmt.PrintNode;
import ast.funcallstmt.cast.DoubleCastNode;
import ast.funcallstmt.cast.IntegerCastNode;
import ast.type.*;
import exceptions.ErrorHandler;
import symboltable.SymbolTableHandler;

public class ReturnStmtTypeCheckVisitor extends AstVisitor {

    private SymbolTableHandler symtable = new SymbolTableHandler(false);
    private final String FUNC_DEF_ID = "funcdef";
    private ErrorHandler errorHandler;

    public ReturnStmtTypeCheckVisitor(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    private void checkType(ITypeNode leftNode, ITypeNode rightNode) {
        Type leftType = leftNode.getType();
        Type rightType = rightNode.getType();
        if (leftType == null) {
            System.err.println("Left type null!");
            return;
        }
        if (rightType == null) {
            System.err.println("Right type null!");
            return;
        }
        if (!leftType.equals(rightType)) {
            errorHandler.typeMismatch(leftNode, rightNode);

        }
    }

    @Override
    public void visit(Func_callExprNode node) {

    }

    @Override
    public void visit(BlockNode node) {
        DclsNode dcls = node.getDclsNode();
        if (dcls != null) {
            dcls.accept(this);
        }
        StmtsNode stmts = node.getStmtsNode();
        if (stmts != null) {
            stmts.accept(this);
        }
        Return_stmtNode returnstmt = node.getReturnstmtNode();
        if (returnstmt != null) {
            returnstmt.accept(this);
        }

    }

    @Override
    public void visit(Func_defNode node) {
        symtable.openScope();
        symtable.enterSymbol(FUNC_DEF_ID, node);
        node.getBlockNode().accept(this);

        symtable.closeScope();

    }

    @Override
    public void visit(Return_stmtNode node) {
        Func_defNode funcdefnode = (Func_defNode) symtable.getSymbolNode(FUNC_DEF_ID);
        checkType(funcdefnode, node);

    }

    @Override
    public void visit(If_stmtNode node) {
        node.getIfBlock().accept(this);
        BlockNode elseblock = node.getElseBlock();
        if (elseblock != null) {
            elseblock.accept(this);
        }

    }

    @Override
    public void visit(StartNode node) {
        symtable.openScope();
        node.getDcls().accept(this);
        node.getStmts().accept(this);
        symtable.closeScope();

    }

    @Override
    public void visit(BooleanLiteral node) {

    }

    @Override
    public void visit(StmtsNode node) {
        int childCount = node.getChildCount();
        for (int i = 0; i < childCount; i++) {
            node.getChild(i).accept(this);
        }

    }

    @Override
    public void visit(DclNode node) {

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
        node.getBlockNode().accept(this);

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
    public void visit(UnaryExprNode node) {
    }

    @Override
    public void visit(Assign_stmtNode node) {
    }

    @Override
    public void visit(IntegerLiteral node) {
    }

    @Override
    public void visit(DoubleLiteral node) {
    }

    @Override
    public void visit(StringLiteral node) {
    }

    @Override
    public void visit(IdNode node) {
    }

    @Override
    public void visit(LogicalOrExprNode node) {
    }

    @Override
    public void visit(PrintNode node) {
    }

    @Override
    public void visit(CustomFuncCallStmtNode node) {
    }

    @Override
    public void visit(IntegerCastNode node) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(DoubleCastNode node) {
        // TODO Auto-generated method stub
        
    }

}
