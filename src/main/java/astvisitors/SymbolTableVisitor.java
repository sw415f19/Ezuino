package astvisitors;

import ast.*;
import ast.arduino.*;
import ast.expr.*;
import ast.expr.aexpr.AExpr;
import ast.funcallstmt.CustomFuncCallStmtNode;
import ast.funcallstmt.PrintNode;
import ast.expr.cast.DoubleCastNode;
import ast.expr.cast.IntegerCastNode;
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

    private void enterFunctionSymbol(Func_defNode node) {
        String id = node.getId();
        if (!stFunctions.isGlobalScope()) {
            errorHandler.nestedFuncDef(id);
        }
        if (!stFunctions.enterSymbol(id, node)) {
            errorHandler.funcAlreadyDeclared(id);
        }
    }

    private Type getVariableType(String id) {
        ITypeNode idNode = stVariables.retrieveSymbol(id);
        if (idNode == null) {
            errorHandler.undeclaredVariable(id);
            return null;
        }
        return idNode.getType();
    }

    private Func_defNode getFunctionDef(String key) {
        Func_defNode result = (Func_defNode) stFunctions.retrieveSymbol(key);
        if (result == null) {
            errorHandler.undeclaredFunction(key);
        }
        return result;
    }

    private Type getFunctionReturnType(String key) {
        Func_defNode node = getFunctionDef(key);
        if (node == null) {
            return null;
        }
        return node.getType();
    }

    private void openVariableScope() {
        stVariables.openScope();
    }

    private void closeVariableScope() {
        stVariables.closeScope();
    }

    private void openGeneralScope() {
        stFunctions.openScope();
        openVariableScope();
    }

    private void closeGeneralScope() {
        stFunctions.closeScope();
        closeVariableScope();
    }
    
    private void checkEssentialFunctions() {
        String setup = "Setup";
        String loop = "Loop";
        if(stFunctions.retrieveSymbol(setup) == null) {
            errorHandler.missingEssentialFunction(setup, true);
        }
        if(stFunctions.retrieveSymbol(loop) == null) {
            errorHandler.missingEssentialFunction(loop, false);
        }
    }

    @Override
    public void visit(StartNode node) {
        openGeneralScope();
        node.getDcls().accept(this);
        node.getStmts().accept(this);
        checkEssentialFunctions();
        closeGeneralScope();
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
        node.setType(getFunctionReturnType(node.getID()));
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(IntegerLiteral node) {
        node.setType(Type.INT);
    }

    @Override
    public void visit(DoubleLiteral node) {
        node.setType(Type.DOUBLE);
    }

    @Override
    public void visit(StringLiteral node) {
        node.setType(Type.STRING);
    }

    @Override
    public void visit(BooleanLiteral node) {
        node.setType(Type.BOOL);
    }

    @Override
    public void visit(Func_defNode node) {
        enterFunctionSymbol(node);

        openGeneralScope();
        for (DclNode parameter : node.getParameters()) {
            parameter.accept(this);
        }
        node.getBlockNode().accept(this);
        closeGeneralScope();
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
        openVariableScope();
        node.getIfBlock().accept(this);
        closeVariableScope();
        openVariableScope();
        BlockNode elseBlock = node.getElseBlock();
        if (elseBlock != null) {
            elseBlock.accept(this);
        }
        closeVariableScope();
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
        openVariableScope();
        node.getBlockNode().accept(this);
        closeVariableScope();
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
        // Check if function is declared
        getFunctionDef(node.getId());
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
        node.setType(getFunctionReturnType(node.getId()));

    }

    @Override
    public void visit(IntegerCastNode node) {
        node.setType(Type.INT);
        for (AExpr var : node.getParameters()) {
            var.accept(this);
        }
    }

    @Override
    public void visit(DoubleCastNode node) {
        node.setType(Type.DOUBLE);
        for (AExpr var : node.getParameters()) {
            var.accept(this);
        }
    }

    @Override
    public void visit(AnalogReadNode node) {
        node.setType(Type.INT);
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(AnalogWriteNode node) {
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(DelayMicroNode node) {
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(DelayNode node) {
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(DigitalReadNode node) {
        node.setType(Type.INT);
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(DigitalWriteNode node) {
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(SetPinModeNode node) {
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(SerialBeginNode node) {
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(SerialEndNode node) {
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(PinLevelNode node) {
        node.setType(Type.INT);
    }

    @Override
    public void visit(PinModeNode node) {
        node.setType(Type.INT);
    }

    @Override
    public void visit(SetupNode node) {
        enterFunctionSymbol(node);

        openGeneralScope();
        for (DclNode parameter : node.getParameters()) {
            parameter.accept(this);
        }
        node.getBlockNode().accept(this);
        closeGeneralScope();
    }

    @Override
    public void visit(LoopNode node) {
        enterFunctionSymbol(node);

        openGeneralScope();
        for (DclNode parameter : node.getParameters()) {
            parameter.accept(this);
        }
        node.getBlockNode().accept(this);
        closeGeneralScope();        
    }
}
