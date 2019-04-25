package astvisitors;

import ast.*;
import ast.expr.*;
import ast.expr.aexpr.AExpr;
import ast.funcallstmt.CustomFuncCallStmtNode;
import ast.funcallstmt.ListAddNode;
import ast.funcallstmt.ListRemoveNode;
import ast.funcallstmt.PrintNode;
import ast.type.*;
import exceptions.ErrorHandler;
import symboltable.SymbolTableHandler;

public class ListVisitor extends AstVisitor {
    private SymbolTableHandler symbolTableHandler;

    public ListVisitor(boolean printDcl) {
        this.symbolTableHandler = new SymbolTableHandler(printDcl);
    }

    public ListVisitor() {
        this.symbolTableHandler = new SymbolTableHandler(false);
    }

    @Override
    public void visit(StartNode node) {
        symbolTableHandler.openScope();
        node.getDcls().accept(this);
        node.getStmts().accept(this);
        symbolTableHandler.closeScope();
    }

    @Override
    public void visit(BlockNode node) {
        symbolTableHandler.openScope();
        if (node.getDclsNode() != null) {
            node.getDclsNode().accept(this);
        }
        if (node.getStmtsNode() != null) {
            node.getStmtsNode().accept(this);
        }
        if (node.getReturnstmtNode() != null) {
            node.getReturnstmtNode().accept(this);
        }
        symbolTableHandler.closeScope();
    }

    @Override
    public void visit(DclNode node) {
        if (node.isList()) {
            symbolTableHandler.enterSymbol(node.getID(), node);
        }
    }

    @Override
    public void visit(Assign_stmtNode node) {
        node.getExprNode().accept(this);
        node.setType(symbolTableHandler.retrieveSymbol(node.getId()));
    }

    @Override
    public void visit(Func_callExprNode node) {
        node.setType(symbolTableHandler.retrieveSymbol(node.getID()));
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
        symbolTableHandler.enterSymbol(node.getId(), node);
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
        node.setType(symbolTableHandler.retrieveSymbol(node.getVal()));
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
        IdNode node2 = (IdNode) node.getParameters().get(0);
        ITypeNode listType = symbolTableHandler.getSymbolNode(node2.getVal());

        if (node.getParameters().size() != 2) {
            ErrorHandler.invalidListLength(node2.getVal());
        }

        if (isSameType(listType, node.getParameters().get(1))) {
            return;
        }
        ErrorHandler.listNotSameType(listType, node);
    }

    @Override
    public void visit(ListRemoveNode node) {
        IdNode node2 = (IdNode) node.getParameters().get(0);
        ITypeNode listType = symbolTableHandler.getSymbolNode(node2.getVal());

        if (node.getParameters().size() != 2) {
            ErrorHandler.invalidListLength(node2.getVal());
        }

        if (isSameType(listType, node.getParameters().get(1))) {
            return;
        }
        ErrorHandler.listNotSameType(listType, node);
    }

    private boolean isSameType(ITypeNode firstParam, ITypeNode secondParam) {
        return firstParam.getType() == secondParam.getType();
    }
}
