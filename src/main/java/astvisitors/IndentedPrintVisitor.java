package astvisitors;

import ast.*;
import ast.arduino.*;
import ast.expr.*;
import ast.expr.aexpr.AExpr;
import ast.funcallstmt.*;
import ast.expr.cast.*;
import ast.type.*;

public class IndentedPrintVisitor extends AstVisitor {
    private StringBuilder sb = new StringBuilder();
    private int level;

    private void print(AstNode node) {
        String resultString = "";
        resultString += level > 0 ? new String(new char[level]).replace("\0", "   ") : "";
        resultString += "+- " + node.toString();
        sb.append(resultString + "\n");
    }

    private void end() {
        System.out.println(sb.toString());
    }

    private void indentLevel() {
        level++;
    }

    private void unindentLevel() {
        level--;
    }

    @Override
    public void visit(Func_callExprNode node) {
        print(node);
        indentLevel();
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
        unindentLevel();

    }

    @Override
    public void visit(BlockNode node) {
        print(node);
        indentLevel();
        if (node.getDclsNode() != null) {
            node.getDclsNode().accept(this);
        }
        if (node.getStmtsNode() != null) {
            node.getStmtsNode().accept(this);
        }
        if (node.getReturnstmtNode() != null) {
            node.getReturnstmtNode().accept(this);
        }
        unindentLevel();

    }

    @Override
    public void visit(Func_defNode node) {
        print(node);
        indentLevel();
        for (DclNode parameter : node.getParameters()) {
            parameter.accept(this);
        }
        node.getBlockNode().accept(this);
        unindentLevel();

    }

    @Override
    public void visit(Return_stmtNode node) {
        print(node);
        indentLevel();
        if (node.getReturnExpr() != null) {
            node.getReturnExpr().accept(this);
        }
        unindentLevel();

    }

    @Override
    public void visit(If_stmtNode node) {
        print(node);
        indentLevel();
        node.getExpr().accept(this);
        node.getIfBlock().accept(this);
        BlockNode elseBlock = node.getElseBlock();
        if (elseBlock != null) {
            elseBlock.accept(this);
        }
        unindentLevel();

    }

    @Override
    public void visit(StartNode node) {
        print(node);
        indentLevel();
        node.getDcls().accept(this);
        node.getStmts().accept(this);
        unindentLevel();
        end();

    }

    @Override
    public void visit(BooleanLiteral node) {
        print(node);

    }

    @Override
    public void visit(StmtsNode node) {
        print(node);
        indentLevel();
        int childCount = node.getChildCount();
        for (int i = 0; i < childCount; i++) {
            node.getChild(i).accept(this);
        }
        unindentLevel();

    }

    @Override
    public void visit(DclNode node) {
        print(node);

    }

    @Override
    public void visit(DclsNode node) {
        print(node);
        indentLevel();
        int childCount = node.getChildCount();
        for (int i = 0; i < childCount; i++) {
            node.getChild(i).accept(this);
        }
        unindentLevel();

    }

    @Override
    public void visit(While_stmtNode node) {
        print(node);
        indentLevel();
        node.getExprNode().accept(this);
        node.getBlockNode().accept(this);
        unindentLevel();

    }

    @Override
    public void visit(AdditiveExprNode node) {
        print(node);

        indentLevel();
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        unindentLevel();
    }

    @Override
    public void visit(MultiplicativeExprNode node) {
        print(node);
        indentLevel();
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        unindentLevel();

    }

    @Override
    public void visit(LogicalAndExprNode node) {
        print(node);
        indentLevel();
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        unindentLevel();

    }

    @Override
    public void visit(LogicalOrExprNode node) {
        print(node);
        indentLevel();
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        unindentLevel();

    }

    @Override
    public void visit(RelationalExprNode node) {
        print(node);
        indentLevel();
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        unindentLevel();

    }

    @Override
    public void visit(EqualityExprNode node) {
        print(node);
        indentLevel();
        node.getLeftNode().accept(this);
        node.getRightNode().accept(this);
        unindentLevel();

    }

    @Override
    public void visit(ParenthesisExprNode node) {
        print(node);
        indentLevel();
        node.getNode().accept(this);
        unindentLevel();

    }

    @Override
    public void visit(UnaryExprNode node) {
        print(node);
        indentLevel();
        node.getNode().accept(this);
        unindentLevel();

    }

    @Override
    public void visit(PrintNode node) {
        print(node);
        indentLevel();
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
        unindentLevel();

    }

    @Override
    public void visit(CustomFuncCallStmtNode node) {
        print(node);
        indentLevel();
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
        unindentLevel();

    }

    @Override
    public void visit(IntegerCastNode node) {
        print(node);
        indentLevel();
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
        unindentLevel();

    }

    @Override
    public void visit(DoubleCastNode node) {
        print(node);
        indentLevel();
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
        unindentLevel();

    }

    @Override
    public void visit(Assign_stmtNode node) {
        print(node);
        indentLevel();
        node.getExprNode().accept(this);
        unindentLevel();

    }

    @Override
    public void visit(IntegerLiteral node) {
        print(node);

    }

    @Override
    public void visit(DoubleLiteral node) {
        print(node);

    }

    @Override
    public void visit(StringLiteral node) {
        print(node);

    }

    @Override
    public void visit(IdNode node) {
        print(node);

    }

    @Override
    public void visit(AnalogReadNode node) {
        print(node);
        indentLevel();
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
        unindentLevel();
    }

    @Override
    public void visit(AnalogWriteNode node) {
        print(node);
        indentLevel();
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
        unindentLevel();
    }

    @Override
    public void visit(DelayMicroNode node) {
        print(node);
        indentLevel();
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
        unindentLevel();
    }

    @Override
    public void visit(DelayNode node) {
        print(node);
        indentLevel();
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
        unindentLevel();
    }

    @Override
    public void visit(DigitalReadNode node) {
        print(node);
        indentLevel();
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
        unindentLevel();
    }

    @Override
    public void visit(DigitalWriteNode node) {
        print(node);
        indentLevel();
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
        unindentLevel();
    }

    @Override
    public void visit(SetPinModeNode node) {
        print(node);
        indentLevel();
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
        unindentLevel();
    }

    @Override
    public void visit(SerialBeginNode node) {
        print(node);
        indentLevel();
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
        unindentLevel();
    }

    @Override
    public void visit(SerialEndNode node) {
        print(node);
        indentLevel();
        for (AExpr child : node.getParameters()) {
            child.accept(this);
        }
        unindentLevel();
    }

    @Override
    public void visit(PinLevelNode node) {
        print(node);
    }

    @Override
    public void visit(PinModeNode node) {
        print(node);
    }
}
