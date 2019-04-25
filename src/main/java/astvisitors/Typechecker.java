package astvisitors;

import java.util.Arrays;

import ast.*;
import ast.expr.*;
import ast.funcallstmt.CustomFuncCallStmtNode;
import ast.funcallstmt.Func_callStmtNode;
import ast.funcallstmt.PrintNode;
import ast.expr.aexpr.AExpr;
import ast.type.*;
import exceptions.ErrorHandler;

public class Typechecker extends AstVisitor {

    private final String keywords[] = { "PRINT", "RETURN", "DEFAULT", "SWITCH" };

    public void visit(Func_callStmtNode node) {
        for (AExpr parameter : node.getParameters()) {
            parameter.accept(this);
        }
    }

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

    public void visit(Func_defNode node) {
        node.getBlockNode().accept(this);
        for (DclNode parameter : node.getParameters()) {
            parameter.accept(this);
        }
    }

    public void visit(Return_stmtNode node) {
        Type nodeType = Type.VOID;
        AExpr expression = node.getReturnExpr();
        if (expression != null) {
            expression.accept(this);
            nodeType = expression.getType();
        }
        node.setType(nodeType);
    }

    public void visit(If_stmtNode node) {
        node.getExpr().accept(this);
        node.getIfBlock().accept(this);
        if (node.getElseBlock() != null) {
            node.getElseBlock().accept(this);
        }
        checkSpecificType(node.getExpr(), Type.BOOL);
    }

    public void visit(StartNode node) {
        node.getDcls().accept(this);
        node.getStmts().accept(this);

    }

    public void visit(BooleanLiteral node) {
        node.setType(Type.BOOL);
    }

    public void visit(StmtNode node) {

    }

    public void visit(StmtsNode node) {
        for (int i = 0; i < node.getChildCount(); i++) {
            node.getChild(i).accept(this);
        }
    }

    public void visit(DclNode node) {
        if (isReservedKeyword(node.getID()))
            ErrorHandler.reservedKeyword(node.getID());
    }

    public void visit(TypeNode node) {

    }

    public void visit(DclsNode node) {
        for (int i = 0; i < node.getChildCount(); i++) {
            node.getChild(i).accept(this);
        }

    }

    public void visit(ValNode node) {
    }

    public void visit(While_stmtNode node) {
        node.getExprNode().accept(this);
        node.getBlockNode().accept(this);
        checkSpecificType(node.getExprNode(), Type.BOOL);
    }

    public void visit(ExprNode node) {

    }

    public void visit(ParametersNode node) {

    }

    // One added assignment nodes.
    public void visit(Assign_stmtNode node) {
        node.getExprNode().accept(this);
        checkType(node, node.getExprNode());

    }

    @Override
    public void visit(Func_callExprNode node) {
        for (AExpr parameter : node.getParameters()) {
            parameter.accept(this);
        }
    }

    @Override
    public void visit(AdditiveExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        checkType(node.getLeftNode(), node.getRightNode());
        node.setType(node.getLeftNode().getType());
    }

    @Override
    public void visit(MultiplicativeExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        checkType(node.getLeftNode(), node.getRightNode());
        node.setType(node.getLeftNode().getType());
    }

    @Override
    public void visit(LogicalAndExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        checkSpecificType(node.getLeftNode(), Type.BOOL);
        checkSpecificType(node.getRightNode(), Type.BOOL);
        node.setType(Type.BOOL);
    }

    @Override
    public void visit(RelationalExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        checkType(node.getLeftNode(), node.getRightNode());
        node.setType(Type.BOOL);
    }

    @Override
    public void visit(EqualityExprNode node) {
        node.getLeftNode().accept(this);
        node.getRelationalExprNode().accept(this);
        checkType(node.getLeftNode(), node.getRelationalExprNode());
        node.setType(Type.BOOL);
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
        if (leftType != rightType) {
            ErrorHandler.typeMismatch(leftNode, rightNode);

        }
    }

    private void checkSpecificType(ITypeNode node, Type expectedType) {
        Type nodeType = node.getType();
        if (nodeType == null) {
            System.err.println("node null in 184 :(");
        }
        if (nodeType != expectedType) {
            ErrorHandler.unexpectedType(node, nodeType);
        }
    }

    @Override
    public void visit(ParenthesisExprNode node) {
        node.getNode().accept(this);
        node.setType(node.getNode().getType());
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
    public void visit(IdNode node) {
        if (node.getVal().toUpperCase().equals("TRUE") || node.getVal().toUpperCase().equals("FALSE"))
            ErrorHandler.invalidTF();
    }

    @Override
    public void visit(AstNode astNode) {
        super.visit(astNode);
    }

    private boolean isReservedKeyword(String word) {
        return (Arrays.binarySearch(keywords, word.toUpperCase()) >= 0);
    }

    @Override
    public void visit(UnaryExprNode node) {
        node.getNode().accept(this);
        node.setType(node.getNode().getType());
    }

    @Override
    public void visit(LogicalOrExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        checkSpecificType(node.getLeftNode(), Type.BOOL);
        checkSpecificType(node.getRightNode(), Type.BOOL);
        node.setType(Type.BOOL);
    }

    @Override
    public void visit(PrintNode node) {
    }

    @Override
    public void visit(CustomFuncCallStmtNode node) {
    }
}
