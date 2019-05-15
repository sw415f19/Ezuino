package astvisitors;

import ast.*;
import ast.arduino.*;
import ast.expr.*;
import ast.expr.aexpr.AExpr;
import ast.funcallstmt.CustomFuncCallStmtNode;
import ast.funcallstmt.PrintNode;
import ast.expr.cast.DoubleCastNode;
import ast.expr.cast.IntegerCastNode;
import ast.type.DoubleLiteral;
import ast.type.IdNode;
import ast.type.IntegerLiteral;
import ast.type.StringLiteral;

import java.io.PrintStream;
import java.util.Iterator;

public class CCodeGenerationVisitor extends AstVisitor {
    private final StringBuilder builder = new StringBuilder();
    private final PrintStream out;

    public CCodeGenerationVisitor(PrintStream printStream) {
        this.out = printStream;
    }

    @Override
    public void visit(CustomFuncCallStmtNode node) {
        builder.append(node.getId()).append("(");
        for (Iterator<AExpr> iterator = node.getParameters().iterator(); iterator.hasNext(); ) {
            AExpr exp = iterator.next();
            exp.accept(this);
            // Adds separating comma if there is another parameter
            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }
        builder.append(");\n");
    }

    @Override
    public void visit(Func_callExprNode node) {
        builder.append(node.getID()).append("(");
        for (Iterator<AExpr> iterator = node.getParameters().iterator(); iterator.hasNext(); ) {
            AExpr exp = iterator.next();
            exp.accept(this);
            // Adds separating comma if there is another parameter
            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }
        builder.append(")");
    }

    @Override
    public void visit(BlockNode node) {
        builder.append("{\n");
        // Checks for declarations
        if (node.getDclsNode() != null) {
            node.getDclsNode().accept(this);
        }
        // Checks for statement calls
        if (node.getStmtsNode() != null) {
            node.getStmtsNode().accept(this);
        }
        if (node.getReturnstmtNode() != null) {
            node.getReturnstmtNode().accept(this);
            builder.append(";\n");
        }
        builder.append("}\n");
    }

    @Override
    public void visit(Func_defNode node) {
        String nodeType = "";
        // Formats the defined type to C types
        switch (node.getType()) {
            case INT:
                nodeType = "int ";
                break;
            case DOUBLE:
                nodeType = "double ";
                break;
            case STRING:
                nodeType = "char ";
                break;
            case BOOL:
                nodeType = "int ";
                break;
            case VOID:
                nodeType = "void ";
                break;
        }
        builder.append(nodeType).append(node.getId()).append("(");
        for (Iterator<DclNode> iterator = node.getParameters().iterator(); iterator.hasNext(); ) {
            DclNode dclNode = iterator.next();
            dclNode.accept(this);
            // Adds separating comma if there is another parameter
            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }
        builder.append(") ");
        node.getBlockNode().accept(this);
    }

    @Override
    public void visit(Return_stmtNode node) {
        if (node.getReturnExpr() == null) {
            builder.append("return");
        }
        else {
            builder.append("return ");
            node.getReturnExpr().accept(this);
        }
    }

    @Override
    public void visit(If_stmtNode node) {
        builder.append("if (");
        node.getExpr().accept(this);
        builder.append(") ");
        node.getIfBlock().accept(this);
        // Checks if there is a else block to print
        if (node.getElseBlock() != null) {
            builder.append("else ");
            node.getElseBlock().accept(this);
        }
    }

    @Override
    public void visit(StartNode node) {
        // includes
        builder.append("#include <stdio.h>\n" +
                "#include <string.h>\n");
        // start tree search
        node.getDcls().accept(this);
        node.getStmts().accept(this);
        // prints the final string
        out.print(builder.toString());
    }

    @Override
    public void visit(BooleanLiteral node) {
        if (node.getBoolval().equals("true")) {
            builder.append("1");
        }
        if (node.getBoolval().equals("false")) {
            builder.append("0");
        }
    }

    @Override
    public void visit(StmtsNode node) {
        for (Iterator<StmtNode> iterator = node.getChildIterator(); iterator.hasNext(); ) {
            StmtNode stmtNode = iterator.next();
            stmtNode.accept(this);
        }
    }

    @Override
    public void visit(DclNode node) {
        String nodeType = "";
        String arrayLength = "";
        // Formats the defined type to C types
        switch (node.getType()) {
            case INT:
                nodeType = "int ";
                break;
            case DOUBLE:
                nodeType = "double ";
                break;
            case STRING:
                // Converts the java string into a C char array of size 256
                nodeType = "char ";
                arrayLength = "[256]";
                break;
            case BOOL:
                nodeType = "int ";
                break;
        }
        builder.append(nodeType).append(node.getID()).append(arrayLength);
    }

    @Override
    public void visit(DclsNode node) {
        if (node.getChildCount() == 1) {
            node.getChild(0).accept(this);
            builder.append(";\n");
        }
        else {
            for (Iterator<DclNode> iterator = node.getChildIterator(); iterator.hasNext(); ) {
                DclNode dclNode = iterator.next();
                dclNode.accept(this);
                builder.append(";\n");
            }
        }
    }

    @Override
    public void visit(While_stmtNode node) {
        builder.append("while (");
        node.getExprNode().accept(this);
        builder.append(") ");
        node.getBlockNode().accept(this);
    }

    @Override
    public void visit(Assign_stmtNode node) {
        if (node.getExprNode().getType().equals(Type.STRING)) {
            builder.append("strcpy(").append(node.getId()).append(", ");
            node.getExprNode().accept(this);
            builder.append(");\n");
        }
        else {
            builder.append(node.getId()).append(" = ");
            node.getExprNode().accept(this);
            builder.append(";\n");
        }
    }

    @Override
    public void visit(ParenthesisExprNode node) {
        builder.append("(");
        node.getNode().accept(this);
        builder.append(")");
    }

    @Override
    public void visit(UnaryExprNode node) {
        builder.append(node.getOperator());
        node.getNode().accept(this);
    }

    @Override
    public void visit(MultiplicativeExprNode node) {
        node.getLeftNode().accept(this);
        builder.append(node.getOperator());
        node.getRightNode().accept(this);
    }

    @Override
    public void visit(AdditiveExprNode node) {
        node.getLeftNode().accept(this);
        builder.append(node.getOperator());
        node.getRightNode().accept(this);
    }

    @Override
    public void visit(RelationalExprNode node) {
        node.getLeftNode().accept(this);
        builder.append(node.getOperator());
        node.getRightNode().accept(this);
    }

    @Override
    public void visit(EqualityExprNode node) {
        if (node.getOperator().equals("=")) {
            node.getLeftNode().accept(this);
            builder.append("==");
            node.getRightNode().accept(this);
        }
        else {
            node.getLeftNode().accept(this);
            builder.append(node.getOperator());
            node.getRightNode().accept(this);
        }
    }

    @Override
    public void visit(LogicalAndExprNode node) {
        node.getLeftNode().accept(this);
        builder.append("&&");
        node.getRightNode().accept(this);
    }

    @Override
    public void visit(LogicalOrExprNode node) {
        node.getLeftNode().accept(this);
        builder.append("||");
        node.getRightNode().accept(this);
    }

    @Override
    public void visit(IntegerLiteral node) {
        builder.append(node.getVal());
    }

    @Override
    public void visit(DoubleLiteral node) {
        builder.append(node.getVal());
    }

    @Override
    public void visit(StringLiteral node) {
        if (node.getVal().length() > 255) {
            System.err.println("CCodeGenerationVisitor Error: String beyond maximum length!");
        }
        else {
            builder.append(node.getVal());
        }
    }

    @Override
    public void visit(IdNode node) {
        builder.append(node.getVal());
    }

    @Override
    public void visit(PrintNode node) {
        builder.append("printf(%s, ");
        for (AExpr exp : node.getParameters()) {
            exp.accept(this);
        }
        builder.append(");\n");
    }

    @Override
    public void visit(IntegerCastNode node) {
    }

    @Override
    public void visit(DoubleCastNode node) {
    }

    @Override
    public void visit(AnalogReadNode node) {
    }

    @Override
    public void visit(AnalogWriteNode node) {
    }

    @Override
    public void visit(DelayMicroNode node) {
    }

    @Override
    public void visit(DelayNode node) {
    }

    @Override
    public void visit(DigitalReadNode node) {
    }

    @Override
    public void visit(DigitalWriteNode node) {
    }

    @Override
    public void visit(SetPinModeNode node) {
    }

    @Override
    public void visit(SerialBeginNode node) {
    }

    @Override
    public void visit(SerialEndNode node) {
    }

    @Override
    public void visit(PinLevelNode node) {
    }

    @Override
    public void visit(PinModeNode node) {
    }

    @Override
    public void visit(SetupNode node) {
        builder.append("void ").append(node.getId().toLowerCase()).append("() ");
        node.getBlockNode().accept(this);
    }

    @Override
    public void visit(LoopNode node) {
        builder.append("void ").append(node.getId().toLowerCase()).append("() ");
        node.getBlockNode().accept(this);        
    }
}
