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

public class MissingReturnStmtVisitor extends AstVisitor {

    private SymbolTableHandler symtable;
    private final String BLOCK_RETURN_STMT = "hasreturn";
    private ErrorHandler errorHandler;

    public MissingReturnStmtVisitor(ErrorHandler errorHandler) {
        this.symtable = new SymbolTableHandler(false);
        this.errorHandler = errorHandler;
    }

    private ITypeNode getBlockReturnStmtNode() {
        return symtable.getSymbolCurrentScope(BLOCK_RETURN_STMT);
    }

    private boolean blockNodeHasReturnStmt() {
        ITypeNode returnstmt = getBlockReturnStmtNode();
        return returnstmt != null;
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
        node.getBlockNode().accept(this);

        if (node.getType() != Type.VOID && (!blockNodeHasReturnStmt())) {
            errorHandler.returnNotGuaranteed();
        }

        symtable.closeScope();
    }

    @Override
    public void visit(Return_stmtNode node) {
        symtable.enterSymbol(BLOCK_RETURN_STMT, node);

    }

    @Override
    public void visit(If_stmtNode node) {
        symtable.openScope();
        node.getIfBlock().accept(this);
        boolean ifBlockHasReturnStmt = blockNodeHasReturnStmt();
        symtable.closeScope();

        BlockNode elseblock = node.getElseBlock();
        if (elseblock != null) {
            symtable.openScope();

            elseblock.accept(this);
            boolean elseBlockHasReturnStmt = blockNodeHasReturnStmt();
            ITypeNode returnStmt = getBlockReturnStmtNode();
            symtable.closeScope();

            if (ifBlockHasReturnStmt && elseBlockHasReturnStmt) {
                symtable.enterSymbol(BLOCK_RETURN_STMT, returnStmt);
            }
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
        symtable.openScope();
        node.getBlockNode().accept(this);
        symtable.closeScope();

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
    public void visit(LogicalOrExprNode node) {
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
    public void visit(PrintNode node) {
    }

    @Override
    public void visit(CustomFuncCallStmtNode node) {
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
    public void visit(IntegerCastNode node) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(DoubleCastNode node) {
        // TODO Auto-generated method stub
        
    }

}
