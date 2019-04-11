package astvisitors;


import ast.*;
import ast.expr.*;
import ast.expr.aexpr.AExpr;
import ast.type.*;
import symbolTable.SymbolTableStack;

public class SymbolTableVisitor extends AstVisitor {
    private SymbolTableStack symbolTableStack = new SymbolTableStack();

    @Override
    public void visit(StartNode node) {
        symbolTableStack.openScope();
        node.getDcls().accept(this);
        node.getStmts().accept(this);
        symbolTableStack.closeScope();
    }

    @Override
    public void visit(BlockNode node) {
        symbolTableStack.openScope();
        if (node.getDclsNode() != null) {
            node.getDclsNode().accept(this);
        }
        if (node.getStmtsNode() != null) {
            node.getStmtsNode().accept(this);
        }
        if (node.getReturnstmtNode() != null) {
            node.getReturnstmtNode().accept(this);
        }
        symbolTableStack.closeScope();
    }

    @Override
    public void visit(DclNode node) {
    	symbolTableStack.enterSymbol(node.getID(), node);
    }

    @Override
    public void visit(Assign_stmtNode node) {
        node.getExprNode().accept(this);
        node.setType(symbolTableStack.retrieveSymbol(node.getId()));
    }

    @Override
    public void visit(Func_callStmtNode node) {
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(Func_callExprNode node) {
    	node.setType(symbolTableStack.retrieveSymbol(node.getID()));
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(IntegerNode node) {

    }

    @Override
    public void visit(DoubleNode node) {

    }

    @Override
    public void visit(BooleantfNode node) {

    }

    @Override
    public void visit(StringNode node) {

    }

    @Override
    public void visit(Func_defNode node) {
    	symbolTableStack.enterSymbol(node.getId(), node);
        for (DclNode parameter : node.getParameters()) {
            parameter.accept(this);
        }
        node.getBlockNode().accept(this);
    }

    @Override
    public void visit(Print_lNode node) {
        node.getExprNode().accept(this);
    }

    @Override
    public void visit(Return_stmtNode node) {
        node.getReturnExpr().accept(this);
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
    public void visit(TypeNode node) {

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
    public void visit(ExprNode node) {

    }

    @Override
    public void visit(ParametersNode node) {

    }

    @Override
    public void visit(IdNode node) {
    	node.setType(symbolTableStack.retrieveSymbol(node.getVal()));
    }

    @Override
    public void visit(Built_in_funcNode node) {
        if (node.getPrintlNode() != null) {
            node.getPrintlNode().accept(this);
        }
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
}
