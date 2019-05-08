package astvisitors;

import java.util.Arrays;

import ast.*;
import ast.expr.*;
import ast.funcallstmt.CustomFuncCallStmtNode;
import ast.funcallstmt.PrintNode;
import ast.funcallstmt.cast.DoubleCastNode;
import ast.funcallstmt.cast.IntegerCastNode;
import ast.expr.aexpr.AExpr;
import ast.type.*;
import exceptions.ErrorHandler;

public class Typechecker extends AstVisitor {

    private final String keywords[] = { "PRINT", "RETURN", "DEFAULT", "SWITCH" };
    private ErrorHandler errorHandler;

    public Typechecker(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
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
            errorHandler.typeMismatch(leftNode, rightNode);

        }
    }

    private void checkSpecificType(ITypeNode node, Type expectedType) {
        Type nodeType = node.getType();
        if (nodeType == null) {
            System.err.println("Compiler error. nodeType was null in Typechecker.");
        }
        if (nodeType != expectedType) {
            errorHandler.unexpectedType(node, expectedType);
        }
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
    public void visit(Func_defNode node) {
        node.getBlockNode().accept(this);
        for (DclNode parameter : node.getParameters()) {
            parameter.accept(this);
        }

    }

    @Override
    public void visit(Return_stmtNode node) {
        Type nodeType = Type.VOID;
        AExpr expression = node.getReturnExpr();
        if (expression != null) {
            expression.accept(this);
            nodeType = expression.getType();
        }
        node.setType(nodeType);
    }

    @Override
    public void visit(If_stmtNode node) {
        node.getExpr().accept(this);
        node.getIfBlock().accept(this);
        if (node.getElseBlock() != null) {
            node.getElseBlock().accept(this);
        }
        checkSpecificType(node.getExpr(), Type.BOOL);

    }

    @Override
    public void visit(StartNode node) {
        node.getDcls().accept(this);
        node.getStmts().accept(this);

    }

    @Override
    public void visit(BooleanLiteral node) {
        node.setType(Type.BOOL);
    }

    @Override
    public void visit(StmtsNode node) {
        for (int i = 0; i < node.getChildCount(); i++) {
            node.getChild(i).accept(this);
        }
    }

    @Override
    public void visit(DclNode node) {
        if (isReservedKeyword(node.getID()))
            errorHandler.reservedKeyword(node.getID());
    }

    @Override
    public void visit(DclsNode node) {
        for (int i = 0; i < node.getChildCount(); i++) {
            node.getChild(i).accept(this);
        }

    }

    @Override
    public void visit(While_stmtNode node) {
        node.getExprNode().accept(this);
        node.getBlockNode().accept(this);
        checkSpecificType(node.getExprNode(), Type.BOOL);
    }

    @Override
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
        if (node.getType().equals(Type.BOOL) ||
                node.getType().equals(Type.STRING) && node.getOperator().equals("-")) {
            errorHandler.invalidOperatorForType(node.getOperator(), node.getType());
        }
    }

    @Override
    public void visit(MultiplicativeExprNode node) {
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        checkType(node.getLeftNode(), node.getRightNode());
        node.setType(node.getLeftNode().getType());
        if (node.getType().equals(Type.STRING) || node.getType().equals(Type.BOOL)) {
            errorHandler.invalidOperatorForType(node.getOperator(), node.getType());
        }
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
        node.getRightNode().accept(this);
        checkType(node.getLeftNode(), node.getRightNode());
        node.setType(Type.BOOL);
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
            errorHandler.invalidTF();
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
        Type nodeType = node.getNode().getType();
        node.setType(nodeType);
        String nodeOperator = node.getOperator();

        boolean printErr = false;
        if ("-".equals(nodeOperator)) {
            printErr = nodeType.equals(Type.BOOL) || nodeType.equals(Type.STRING);
        } else if ("!".equals(nodeOperator)) {
            printErr = !nodeType.equals(Type.BOOL);
        }
        if (printErr) {
            errorHandler.invalidOperatorForType(nodeOperator, nodeType);
        }
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
        for (AExpr var : node.getParameters()) {
            var.accept(this);
        }

    }

    @Override
    public void visit(CustomFuncCallStmtNode node) {
        for (AExpr var : node.getParameters()) {
            var.accept(this);
        }
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
            if (var.getType() == Type.INT) {
                errorHandler.invalidCastException();
            }
            var.accept(this);
        }
    }
}
