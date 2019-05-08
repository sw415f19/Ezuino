package astvisitors;

import ast.*;
import ast.expr.*;
import ast.expr.aexpr.AExpr;
import ast.funcallstmt.CustomFuncCallStmtNode;
import ast.funcallstmt.PrintNode;
import ast.funcallstmt.cast.DoubleCastNode;
import ast.funcallstmt.cast.IntegerCastNode;
import ast.type.*;
import exceptions.ErrorHandler;
import symboltable.SymbolTableHandler;

public class SymbolTableVisitor extends AstVisitor {
    private ErrorHandler errorHandler;
    private SymbolTableHandler stVariables;
    private SymbolTableHandler stFunctions;

    public SymbolTableVisitor(boolean printDcl, ErrorHandler errorhandler) {
        this.stVariables = new SymbolTableHandler(printDcl);
        this.stFunctions = new SymbolTableHandler(printDcl);
        this.errorHandler = errorhandler;
    }

    private void enterVariableSymbol(String id, ITypeNode node) {
        if (!stVariables.enterSymbol(id, node)) {
            errorHandler.varAlreadyDeclared(id);
        }
    }

    private void enterFunctionSymbol(String id, ITypeNode node) {
        if (!stFunctions.enterSymbol(id, node)) {
            errorHandler.funcAlreadyDeclared(id);
        }
    }

    private Type getVariableType(String id) {
        Type result = stVariables.retrieveSymbol(id);
        if (result == null) {
            errorHandler.notDeclaredVar(id);
        }
        return result;
    }

    private Type getFunctionType(String id) {
        Type result = stFunctions.retrieveSymbol(id);
        if (result == null) {
            errorHandler.notDeclaredFunc(id);
        }
        return result;
    }

    @Override
    public void visit(StartNode node) {
        openScope();
        node.getDcls().accept(this);
        node.getStmts().accept(this);
        closeScope();
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
        enterVariableSymbol(node.getID(), node);
    }

    @Override
    public void visit(Assign_stmtNode node) {
        node.getExprNode().accept(this);
        node.setType(getVariableType(node.getId()));
    }

    @Override
    public void visit(Func_callExprNode node) {
        node.setType(getFunctionType(node.getID()));
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
        enterFunctionSymbol(node.getId(), node);

        openScope();
        for (DclNode parameter : node.getParameters()) {
            parameter.accept(this);
        }
        node.getBlockNode().accept(this);
        closeScope();
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
        openScope();
        node.getIfBlock().accept(this);
        closeScope();
        openScope();
        BlockNode elseBlock = node.getElseBlock();
        if (elseBlock != null) {
            elseBlock.accept(this);
        }
        closeScope();
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
        openScope();
        node.getBlockNode().accept(this);
        closeScope();
    }

    @Override
    public void visit(IdNode node) {
        node.setType(getVariableType(node.getVal()));
    }

    @Override
    public void visit(RelationalExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
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
        getFunctionType(node.getId());
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }

    }

    private void openScope() {
        stVariables.openScope();
        stFunctions.openScope();
    }

    private void closeScope() {
        stVariables.closeScope();
        stFunctions.closeScope();
    }

    @Override
    public void visit(IntegerCastNode node) {
        for (AExpr var : node.getParameters()) {
            var.accept(this);
        }
    }

    @Override
    public void visit(DoubleCastNode node) {
        for (AExpr var : node.getParameters()) {
            var.accept(this);
        }
    }
}
