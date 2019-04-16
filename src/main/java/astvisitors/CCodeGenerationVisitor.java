package astvisitors;

import ast.*;
import ast.expr.*;
import ast.expr.aexpr.AExpr;
import ast.type.DoubleNode;
import ast.type.IdNode;
import ast.type.IntegerNode;
import ast.type.StringNode;

import java.io.PrintStream;
import java.util.Iterator;

public class CCodeGenerationVisitor extends AstVisitor {
    private PrintStream out;

    public CCodeGenerationVisitor(PrintStream printStream) {
        this.out = printStream;
    }

    @Override
    public void visit(Func_callStmtNode node) {
        out.print(node.getID() + "(");
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
        out.print(");\n");
    }

    @Override
    public void visit(BlockNode node) {
        out.print("{");
        node.getDclsNode().accept(this);
        out.print(";\n");
        node.getStmtsNode().accept(this);
        out.print(";\n");
        node.getReturnstmtNode().accept(this);
        out.print(";\n}");
    }

    @Override
    public void visit(Func_defNode node) {
        out.print(node.getType() + " " + node.getId() + "(");
        for (Iterator<DclNode> iterator = node.getParameters().iterator(); iterator.hasNext(); ) {
            DclNode dclNode = iterator.next();
            dclNode.accept(this);
            // Adds separating comma if there is another parameter
            if (iterator.hasNext()) {
                out.print(", ");
            }
        }
        out.print(") {\n");
        node.getBlockNode().accept(this);
        out.print("}\n");
    }

    @Override
    public void visit(Print_lNode node) {
        out.print("print(");
        node.getExprNode().accept(this);
        out.print(");\n");
    }

    @Override
    public void visit(Return_stmtNode node) {
        node.getReturnExpr().accept(this);
    }

    @Override
    public void visit(If_stmtNode node) {
        out.print("if (");
        node.getExpr().accept(this);
        out.print(")\n");
        node.getIfBlock().accept(this);
        out.print("else (");
        node.getElseBlock().accept(this);
        out.print(")");
    }

    @Override
    public void visit(StartNode node) {
        node.getDcls().accept(this);
        node.getStmts().accept(this);
    }

    @Override
    public void visit(BooleantfNode node) {
        out.print(node.getBoolval());
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
        // Void types might be able to be declared, which would give an error in C
        out.print(node.getType() + " " + node.getID() + ";\n");
    }

    @Override
    public void visit(TypeNode node) {
        out.print(node.getType());
    }

    @Override
    public void visit(DclsNode node) {
        for (Iterator<DclNode> iterator = node.getChildIterator(); iterator.hasNext(); ) {
            DclNode dclNode = iterator.next();
            dclNode.accept(this);
        }
    }

    @Override
    public void visit(While_stmtNode node) {
        out.print("while (");
        node.getExprNode().accept(this);
        out.print(") {\n");
        node.getBlockNode().accept(this);
        out.print("}\n");
    }

    @Override
    public void visit(ExprNode node) {

    }

    @Override
    public void visit(ParametersNode node) {
        // ParametersNode does currently not have an implementation
    }

    @Override
    public void visit(Built_in_funcNode node) {
        node.getPrintlNode().accept(this);
    }

    @Override
    public void visit(AdditiveExprNode node) {

    }

    @Override
    public void visit(MultiplicativeExprNode node) {

    }

    @Override
    public void visit(LogicalAndExprNode node) {

    }

    @Override
    public void visit(RelationalExprNode node) {

    }

    @Override
    public void visit(EqualityExprNode node) {

    }

    @Override
    public void visit(ParenthesisExprNode node) {

    }

    @Override
    public void visit(Assign_stmtNode node) {
        out.print(node.getType() + " " + node.getId() + " = " );
        node.getExprNode().accept(this);
        out.print(";\n");
    }

    @Override
    public void visit(IntegerNode node) {
        out.print(node.getVal());
    }

    @Override
    public void visit(DoubleNode node) {
        out.print(node.getVal());
    }

    @Override
    public void visit(StringNode node) {
        out.print(node.getVal());
    }

    @Override
    public void visit(IdNode node) {
        out.print(node.getVal());
    }

    @Override
    public void visit(AstNode astNode) {
        super.visit(astNode);
    }
}
