package astvisitors;

import java.util.List;

import ast.*;
import ast.expr.*;
import ast.expr.aexpr.*;
import ast.type.*;
import exceptions.ErrorHandler;
import symboltable.SymbolTableHandler;

public class FuncStructureVisitor extends AstVisitor {

    private SymbolTableHandler symtable = new SymbolTableHandler(false);

    private void matchParameterList(String functionname, List<AExpr> callparams, List<DclNode> defparams)
    {
        int parametercount = callparams.size();
        if (parametercount != defparams.size()) {
            ErrorHandler.ParameterLengthError(functionname);
            return;
        }
        for (int i = 0; i < parametercount; i++) {
            AExpr callParam = callparams.get(i);
            DclNode dclnode = defparams.get(i);
            if (callParam.getType() != dclnode.getType()) {
                ErrorHandler.ParameterTypeError(functionname);
            }
        }

    }

    @Override
    public void visit(Func_callStmtNode node)
    {
        Func_defNode funcdef = (Func_defNode) symtable.getSymbolNode(node.getID());
        matchParameterList(node.getID(), node.getParameters(), funcdef.getParameters());
    }

    @Override
    public void visit(Func_callExprNode node)
    {
        Func_defNode funcdef = (Func_defNode) symtable.getSymbolNode(node.getID());
        matchParameterList(node.getID(), node.getParameters(), funcdef.getParameters());

    }

    @Override
    public void visit(BlockNode node)
    {
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
    public void visit(Func_defNode node)
    {
        symtable.enterSymbol(node.getId(), node);
    }

    @Override
    public void visit(Return_stmtNode node)
    {
        AExpr expr = node.getReturnExpr();
        if (expr != null) {
            expr.accept(this);
        }
    }

    @Override
    public void visit(If_stmtNode node)
    {
        node.getExpr().accept(this);
        node.getIfBlock().accept(this);
        BlockNode elseblock = node.getElseBlock();
        if (elseblock != null) {
            elseblock.accept(this);
        }
    }

    @Override
    public void visit(StartNode node)
    {
        symtable.openScope();
        node.getDcls().accept(this);
        node.getStmts().accept(this);
        symtable.closeScope();

    }

    @Override
    public void visit(BooleanLiteral node)
    {

    }

    @Override
    public void visit(StmtsNode node)
    {
        int childCount = node.getChildCount();
        for (int i = 0; i < childCount; i++) {
            node.getChild(i).accept(this);
        }

    }

    @Override
    public void visit(DclNode node)
    {

    }

    @Override
    public void visit(DclsNode node)
    {
        int childCount = node.getChildCount();
        for (int i = 0; i < childCount; i++) {
            node.getChild(i).accept(this);
        }

    }

    @Override
    public void visit(While_stmtNode node)
    {
        node.getExprNode().accept(this);
        node.getBlockNode().accept(this);

    }

    @Override
    public void visit(ParametersNode node)
    {

    }

    @Override
    public void visit(AdditiveExprNode node)
    {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);

    }

    @Override
    public void visit(MultiplicativeExprNode node)
    {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);

    }

    @Override
    public void visit(LogicalAndExprNode node)
    {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);

    }

    
    public void visit(LogicalOrExprNode node)
    {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);

    }

    @Override
    public void visit(RelationalExprNode node)
    {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);

    }

    @Override
    public void visit(EqualityExprNode node)
    {
        node.getLeftNode().accept(this);
        node.getRelationalExprNode().accept(this);

    }

    @Override
    public void visit(ParenthesisExprNode node)
    {
        node.getNode().accept(this);

    }

    @Override
    public void visit(UnaryExprNode node)
    {
        node.getNode().accept(this);

    }

    @Override
    public void visit(Assign_stmtNode node)
    {
        node.getExprNode().accept(this);

    }

    @Override
    public void visit(IntegerLiteral node)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(DoubleLiteral node)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(StringLiteral node)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(IdNode node)
    {
        // TODO Auto-generated method stub

    }

}
