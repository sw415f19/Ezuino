package astvisitors;

import ast.*;
import ast.expr.*;
import ast.expr.aexpr.AExpr;
import ast.funcallstmt.CustomFuncCallStmtNode;
import ast.funcallstmt.ListAddNode;
import ast.funcallstmt.ListRemoveNode;
import ast.funcallstmt.PrintNode;
import ast.funcallstmt.cast.DoubleCastNode;
import ast.funcallstmt.cast.IntegerCastNode;
import ast.type.DoubleLiteral;
import ast.type.IdNode;
import ast.type.IntegerLiteral;
import ast.type.StringLiteral;

import java.io.PrintStream;
import java.util.Iterator;

public class CCodeGenerationVisitor extends AstVisitor {
    private PrintStream out;

    public CCodeGenerationVisitor(PrintStream printStream) {
        this.out = printStream;
    }

    @Override
    public void visit(CustomFuncCallStmtNode node) {
        out.print(node.getId() + "(");
        for (Iterator<AExpr> iterator = node.getParameters().iterator(); iterator.hasNext(); ) {
            AExpr exp = iterator.next();
            exp.accept(this);
            // Adds separating comma if there is another parameter
            if (iterator.hasNext()) {
                out.print(", ");
            }
        }
        out.print(");\n");
    }

    @Override
    public void visit(Func_callExprNode node) {
        out.print(node.getID() + "(");
        for (Iterator<AExpr> iterator = node.getParameters().iterator(); iterator.hasNext(); ) {
            AExpr exp = iterator.next();
            exp.accept(this);
            // Adds separating comma if there is another parameter
            if (iterator.hasNext()) {
                out.print(", ");
            }
        }
        out.print(")");
    }

    @Override
    public void visit(BlockNode node) {
        out.print("{\n");
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
            out.print(";\n");
        }
        out.print("}\n");
    }

    @Override
    public void visit(Func_defNode node) {
        String nodeType = "";
        // Formats the defined type to C types
        switch (node.getType()) {
            case INT:
                nodeType="int ";
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
        out.print(nodeType + node.getId() + "(");
        for (Iterator<DclNode> iterator = node.getParameters().iterator(); iterator.hasNext(); ) {
            DclNode dclNode = iterator.next();
            dclNode.accept(this);
            // Adds separating comma if there is another parameter
            if (iterator.hasNext()) {
                out.print(", ");
            }
        }
        out.print(") ");
        node.getBlockNode().accept(this);
    }

    @Override
    public void visit(Return_stmtNode node) {
        if (node.getReturnExpr() == null) {
            out.print("return");
        }
        else {
            out.print("return ");
            node.getReturnExpr().accept(this);
        }
    }

    @Override
    public void visit(If_stmtNode node) {
        out.print("if (");
        node.getExpr().accept(this);
        out.print(") ");
        node.getIfBlock().accept(this);
        // Checks if there is a else block to print
        if (node.getElseBlock() != null) {
            out.print("else ");
            node.getElseBlock().accept(this);
        }
    }

    @Override
    public void visit(StartNode node) {
        // includes
        out.print("#include <stdio.h>\n" +
                "#include <string.h>\n");
        // open main
        out.print("int main (void) {\n");
        // start tree search
        node.getDcls().accept(this);
        node.getStmts().accept(this);
        // close main
        out.print("}\n");
    }

    @Override
    public void visit(BooleanLiteral node) {
        if (node.getBoolval().equals("true")) {
            out.print("1");
        }
        if (node.getBoolval().equals("false")) {
            out.print("0");
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
                nodeType="int ";
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
        out.print(nodeType + node.getID() + arrayLength);
    }

    @Override
    public void visit(DclsNode node) {
        if (node.getChildCount() == 1) {
            node.getChild(0).accept(this);
            out.print(";\n");
        }
        else {
            for (Iterator<DclNode> iterator = node.getChildIterator(); iterator.hasNext(); ) {
                DclNode dclNode = iterator.next();
                dclNode.accept(this);
                out.print(";\n");
            }
        }
    }

    @Override
    public void visit(While_stmtNode node) {
        out.print("while (");
        node.getExprNode().accept(this);
        out.print(") ");
        node.getBlockNode().accept(this);
    }

    @Override
    public void visit(Assign_stmtNode node) {
        if (node.getExprNode().getType().equals(Type.STRING)) {
            out.print("strcpy(" + node.getId() + ", ");
            node.getExprNode().accept(this);
            out.print(");\n");
        }
        else {
            out.print(node.getId() + " = ");
            node.getExprNode().accept(this);
            out.print(";\n");
        }
    }

    @Override
    public void visit(ParametersNode node) {
        // ParametersNode does currently not have an implementation
    }

    @Override
    public void visit(ParenthesisExprNode node) {
        out.print("(");
        node.getNode().accept(this);
        out.print(")");
    }

    @Override
    public void visit(UnaryExprNode node) {
        out.print(node.getOperator());
        node.getNode().accept(this);
    }

    @Override
    public void visit(MultiplicativeExprNode node) {
        node.getLeftNode().accept(this);
        out.print(node.getOperator());
        node.getRightNode().accept(this);
    }

    @Override
    public void visit(AdditiveExprNode node) {
        node.getLeftNode().accept(this);
        out.print(node.getOperator());
        node.getRightNode().accept(this);
    }

    @Override
    public void visit(RelationalExprNode node) {
        node.getLeftNode().accept(this);
        out.print(node.getOperator());
        node.getRightNode().accept(this);
    }

    @Override
    public void visit(EqualityExprNode node) {
        if (node.getOperator().equals("=")) {
            node.getLeftNode().accept(this);
            out.print("==");
            node.getRightNode().accept(this);
        }
        else {
            node.getLeftNode().accept(this);
            out.print(node.getOperator());
            node.getRightNode().accept(this);
        }
    }

    @Override
    public void visit(LogicalAndExprNode node) {
        node.getLeftNode().accept(this);
        out.print("&&");
        node.getRightNode().accept(this);
    }

    @Override
    public void visit(LogicalOrExprNode node) {
        node.getLeftNode().accept(this);
        out.print("||");
        node.getRightNode().accept(this);
    }

    @Override
    public void visit(IntegerLiteral node) {
        out.print(node.getVal());
    }

    @Override
    public void visit(DoubleLiteral node) {
        out.print(node.getVal());
    }

    @Override
    public void visit(StringLiteral node) {
        if (node.getVal().length() > 255) {
            System.err.println("CCodeGenerationVisitor Error: String beyond maximum length!");
        }
        else {
            out.print(node.getVal());
        }
    }

    @Override
    public void visit(IdNode node) {
        out.print(node.getVal());
    }

    @Override
    public void visit(PrintNode node) {
        out.print("printf(%s, ");
        for (Iterator<AExpr> iterator = node.getParameters().iterator(); iterator.hasNext(); ) {
            AExpr exp = iterator.next();
            exp.accept(this);
        }
        out.print(");\n");
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

    @Override
    public void visit(AstNode astNode) {
        super.visit(astNode);
    }
}