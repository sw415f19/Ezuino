package astvisitors;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import ast.*;
import ast.expr.*;
import ast.expr.aexpr.AExpr;
import ast.type.*;
import functionality.EzuinoList;
import symboltable.SymbolTableHandler;

public class FuncVisitor extends AstVisitor {

    private PriorityQueue<String> queue = new PriorityQueue<String>(); 
    private List<String> test = new ArrayList<>();
    private EzuinoList ezuinoList = new EzuinoList();

    @Override
    public void visit(StartNode node) {

        node.getDcls().accept(this);
        node.getStmts().accept(this);

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
        if(node.isList()){
           ezuinoList.newList(node.getID(), node.getType());
        }
    }

    @Override
    public void visit(Assign_stmtNode node) {
        node.getExprNode().accept(this);

    }

    @Override
    public void visit(Func_callStmtNode node) {

        if (node.getID().equals("listAdd")){
            for (AExpr child : node.getParameters()) {
                child.accept(this);
            }
    
        } 

    }

    @Override
    public void visit(Func_callExprNode node) {

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

        for (DclNode parameter : node.getParameters()) {
            parameter.accept(this);
        }
        node.getBlockNode().accept(this);
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
        System.out.println(" ID NODE : " + node.getVal() +  " NODE TYPE : " +  node.getType());
        test.add(node.getVal());
        if (test.size() == 2){
            
            ezuinoList.addToList(test.get(0), node);
            test.clear();
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

    @Override
    public void visit(UnaryExprNode node) {
        node.getNode().accept(this);

    }

    @Override
    public void visit(LogicalOrExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);

    }
}
