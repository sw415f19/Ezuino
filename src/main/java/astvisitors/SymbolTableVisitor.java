package astvisitors;


import ast.*;
import ast.expr.*;
import ast.expr.aexpr.AExpr;
import ast.funcallstmt.CustomFuncCallStmtNode;
import ast.funcallstmt.ListAddNode;
import ast.funcallstmt.ListRemoveNode;
import ast.funcallstmt.PrintNode;
import ast.type.*;
import symboltable.SymbolTableHandler;

public class SymbolTableVisitor extends AstVisitor {
    private SymbolTableHandler symbolTableHandlerVariables;
    private SymbolTableHandler symbolTableHandlerFunctions;   /* Maybe needs better name */

    public SymbolTableVisitor(boolean printDcl) {
        this.symbolTableHandlerVariables = new SymbolTableHandler(printDcl, false);
        this.symbolTableHandlerFunctions = new SymbolTableHandler(printDcl, true);
    }

    public SymbolTableVisitor() {
        this.symbolTableHandlerVariables = new SymbolTableHandler(false, false);
        this.symbolTableHandlerFunctions = new SymbolTableHandler(false, true);
    }

    @Override
    public void visit(StartNode node) {
        symbolTableHandlerVariables.openScope();
        symbolTableHandlerFunctions.openScope();
        node.getDcls().accept(this);
        node.getStmts().accept(this);
        symbolTableHandlerVariables.closeScope();
        symbolTableHandlerFunctions.closeScope();
    }

    @Override
    public void visit(BlockNode node) {
        symbolTableHandlerVariables.openScope();
        symbolTableHandlerFunctions.openScope();
        if (node.getDclsNode() != null) {
            node.getDclsNode().accept(this);
        }
        if (node.getStmtsNode() != null) {
            node.getStmtsNode().accept(this);
        }
        if (node.getReturnstmtNode() != null) {
            node.getReturnstmtNode().accept(this);
        }
        symbolTableHandlerVariables.closeScope();
        symbolTableHandlerFunctions.closeScope();
    }

    @Override
    public void visit(DclNode node) {
        System.out.println("Declaring variable" + node.getID());
        symbolTableHandlerVariables.enterSymbol(node.getID(), node);
    }

    @Override
    public void visit(Assign_stmtNode node) {
        node.getExprNode().accept(this);
        node.setType(symbolTableHandlerVariables.retrieveSymbol(node.getId()));
    }

    @Override
    public void visit(Func_callExprNode node) {
        node.setType(symbolTableHandlerFunctions.retrieveSymbol(node.getID()));
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
        symbolTableHandlerFunctions.enterSymbol(node.getId(), node);
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
        node.setType(symbolTableHandlerVariables.retrieveSymbol(node.getVal()));
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
