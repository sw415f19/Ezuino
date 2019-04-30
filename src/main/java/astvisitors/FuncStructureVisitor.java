package astvisitors;

import java.util.List;

import ast.*;
import ast.expr.*;
import ast.expr.aexpr.*;
import ast.funcallstmt.CustomFuncCallStmtNode;
import ast.funcallstmt.ListAddNode;
import ast.funcallstmt.ListRemoveNode;
import ast.funcallstmt.PrintNode;
import ast.funcallstmt.cast.DoubleCastNode;
import ast.funcallstmt.cast.IntegerCastNode;
import ast.type.*;
import exceptions.ErrorHandler;
import symboltable.SymbolTableHandler;

public class FuncStructureVisitor extends AstVisitor {

    private SymbolTableHandler symtable;
    private ErrorHandler errorHandler;

    public FuncStructureVisitor(ErrorHandler errorHandler) {
        symtable = new SymbolTableHandler(false);
        this.errorHandler = errorHandler;
    }

    private void matchParameterList(String functionname, List<AExpr> callparams, List<DclNode> defparams) {
        int parametercount = callparams.size();
        if (parametercount != defparams.size()) {
            errorHandler.parameterLengthError(functionname);

            return;
        }
        for (int i = 0; i < parametercount; i++) {
            AExpr callParam = callparams.get(i);
            DclNode dclnode = defparams.get(i);
            if (callParam.getType() != dclnode.getType()) {
                errorHandler.parameterTypeError(functionname);
            }
        }

    }

    private void checkSpecificType(ITypeNode node, Type expectedType) {
        Type nodeType = node.getType();
        if (nodeType == null) {
            System.err.println("FuncStructureVisitor: Programming error: nodeType = null");
        }
        if (!nodeType.equals(expectedType)) {
            errorHandler.unexpectedType(node, nodeType);
        }
    }

    @Override
    public void visit(Func_callExprNode node) {
        Func_defNode funcdef = (Func_defNode) symtable.getSymbolNode(node.getID());
        matchParameterList(node.getID(), node.getParameters(), funcdef.getParameters());

    }

    @Override
    public void visit(BlockNode node) {
        symtable.openScope();
        DclsNode dcls = node.getDclsNode();
        StmtsNode stmts = node.getStmtsNode();
        Return_stmtNode return_node = node.getReturnstmtNode();
        if (dcls != null) {
            dcls.accept(this);
        }
        if (stmts != null) {
            stmts.accept(this);
        }
        if (return_node != null) {
            return_node.accept(this);
        }
        symtable.closeScope();
    }

    @Override
    public void visit(Func_defNode node) {
        symtable.enterSymbol(node.getId(), node);
    }

    @Override
    public void visit(Return_stmtNode node) {
        AExpr expr = node.getReturnExpr();
        if (expr != null) {
            expr.accept(this);
        }
    }

    @Override
    public void visit(If_stmtNode node) {
        node.getExpr().accept(this);
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
        node.getExprNode().accept(this);
        node.getBlockNode().accept(this);

    }

    @Override
    public void visit(ParametersNode node) {

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
    public void visit(LogicalAndExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);

    }

    public void visit(LogicalOrExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);

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
    public void visit(UnaryExprNode node) {
        node.getNode().accept(this);

    }

    @Override
    public void visit(Assign_stmtNode node) {
        node.getExprNode().accept(this);

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
    public void visit(PrintNode node) {
        List<AExpr> parameters = node.getParameters();
        if (parameters.size() != 1) {
            errorHandler.parameterLengthError(node.toString());
        }
        checkSpecificType(node.getParameters().get(0), Type.STRING);
    }

    @Override
    public void visit(CustomFuncCallStmtNode node) {
        Func_defNode funcdef = (Func_defNode) symtable.getSymbolNode(node.getId());
        matchParameterList(node.getId(), node.getParameters(), funcdef.getParameters());

    }

    @Override
    public void visit(ListAddNode node) {

    }

    @Override
    public void visit(ListRemoveNode node) {

    }

    @Override
    public void visit(IntegerCastNode node) {

    }

    @Override
    public void visit(DoubleCastNode node) {
    }

}
