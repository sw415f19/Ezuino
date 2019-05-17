package astvisitors;

import ast.*;
import ast.arduino.*;
import ast.expr.*;
import ast.expr.aexpr.AExpr;
import ast.expr.cast.BooleanCastNode;
import ast.expr.cast.DoubleCastNode;
import ast.expr.cast.IntegerCastNode;
import ast.expr.cast.StringCastNode;
import ast.funcallstmt.CustomFuncCallStmtNode;
import ast.funcallstmt.PrintNode;
import ast.type.DoubleLiteral;
import ast.type.IdNode;
import ast.type.IntegerLiteral;
import ast.type.StringLiteral;
import exceptions.ErrorHandler;

import java.util.*;

public class ReservedKeywordsVisitor extends AstVisitor {
    private final Map<String, String> reservedKeywords = new HashMap<String, String>();
    private final Set<String> compatibilityKeywords = new HashSet<String>();
    private final ErrorHandler errorHandler;

    public ReservedKeywordsVisitor(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
        List<String> stringList = (Arrays.asList("return", "AND", "OR", "true", "false", "int", "double",
                "boolean", "string", "while", "if", "else", "Print", "DigitalWrite", "DigitalRead",
                "AnalogWrite", "AnalogRead", "Delay", "DelayMicro", "PinMode", "SerialBegin", "SerialEnd"));
        for (String word : stringList) {
            reservedKeywords.put(word.toUpperCase(), word);
        }
        compatibilityKeywords.addAll(Arrays.asList("goto", "Double", "float", "Integer", "for",
                "ArrayList", "List", "switch", "Collection", "main"));
    }

    private void checkReservedKeywords(String Id) {
        String key = Id.toUpperCase();
        if (reservedKeywords.containsKey(key) && (!reservedKeywords.get(key).equals(Id))) {
                errorHandler.reservedKeyword(Id, reservedKeywords.get(key));
        }
    }

    private void checkCompatibilityKeywords(String Id) {
        if (compatibilityKeywords.contains(Id)) {
            errorHandler.compatibilityKeyword(Id);
        }
    }

    @Override
    public void visit(Func_callExprNode node) {
        checkReservedKeywords(node.getID());
        for (AExpr parameter : node.getParameters()) {
            parameter.accept(this);
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
        checkReservedKeywords(node.getId());
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
        if (node.getElseBlock() != null) {
            node.getElseBlock().accept(this);
        }
    }

    @Override
    public void visit(StartNode node) {
        node.getDcls().accept(this);
        node.getStmts().accept(this);
    }

    @Override
    public void visit(StmtsNode node) {
        for (int i = 0; i < node.getChildCount(); i++) {
            node.getChild(i).accept(this);
        }
    }

    @Override
    public void visit(DclNode node) {
        checkReservedKeywords(node.getID());
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
    }

    @Override
    public void visit(Assign_stmtNode node) {
        checkReservedKeywords(node.getId());
        node.getExprNode().accept(this);
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

    @Override
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
    public void visit(PrintNode node) {
        checkReservedKeywords(node.getId());
        for (AExpr parameter : node.getParameters()) {
            parameter.accept(this);
        }
    }

    @Override
    public void visit(CustomFuncCallStmtNode node) {
        checkReservedKeywords(node.getId());
        for (AExpr parameter : node.getParameters()) {
            parameter.accept(this);
        }
    }

    @Override
    public void visit(IntegerCastNode node) {
        checkReservedKeywords(node.getID());
        for (AExpr parameter : node.getParameters()) {
            parameter.accept(this);
        }
    }

    @Override
    public void visit(DoubleCastNode node) {
        checkReservedKeywords(node.getID());
        for (AExpr parameter : node.getParameters()) {
            parameter.accept(this);
        }
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
    public void visit(BooleanLiteral node) {
    }

    @Override
    public void visit(IdNode node) {
        checkCompatibilityKeywords(node.getVal());
        checkReservedKeywords(node.getVal());
    }

    @Override
    public void visit(AnalogReadNode node) {
        checkReservedKeywords(node.getID());
        for (AExpr parameter : node.getParameters()) {
            parameter.accept(this);
        }
    }

    @Override
    public void visit(AnalogWriteNode node) {
        checkReservedKeywords(node.getId());
        for (AExpr parameter : node.getParameters()) {
            parameter.accept(this);
        }
    }

    @Override
    public void visit(DelayMicroNode node) {
        checkReservedKeywords(node.getId());
        for (AExpr parameter : node.getParameters()) {
            parameter.accept(this);
        }
    }

    @Override
    public void visit(DelayNode node) {
        checkReservedKeywords(node.getId());
        for (AExpr parameter : node.getParameters()) {
            parameter.accept(this);
        }
    }

    @Override
    public void visit(DigitalReadNode node) {
        checkReservedKeywords(node.getID());
        for (AExpr parameter : node.getParameters()) {
            parameter.accept(this);
        }
    }

    @Override
    public void visit(DigitalWriteNode node) {
        checkReservedKeywords(node.getId());
        for (AExpr parameter : node.getParameters()) {
            parameter.accept(this);
        }
    }

    @Override
    public void visit(SetPinModeNode node) {
        checkReservedKeywords(node.getId());
        for (AExpr parameter : node.getParameters()) {
            parameter.accept(this);
        }
    }

    @Override
    public void visit(SerialBeginNode node) {
        checkReservedKeywords(node.getId());
        for (AExpr parameter : node.getParameters()) {
            parameter.accept(this);
        }
    }

    @Override
    public void visit(SerialEndNode node) {
        checkReservedKeywords(node.getId());
        for (AExpr parameter : node.getParameters()) {
            parameter.accept(this);
        }
    }

    @Override
    public void visit(PinLevelNode node) {
    }

    @Override
    public void visit(PinModeNode node) {
    }

    @Override
    public void visit(SetupNode node) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(LoopNode node) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(StringCastNode node) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void visit(BooleanCastNode node) {
        // TODO Auto-generated method stub
        
    }
}
