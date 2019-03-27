package ezuino;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;

import ast.*;
import generated.EzuinoBaseVisitor;
import generated.EzuinoParser;

import java.util.ArrayList;

public class EzuinoVisitor extends EzuinoBaseVisitor<AstNode> {

    private ArrayList<AstNode> ast = new ArrayList<AstNode>();

    @Override
    public AstNode visitStart(EzuinoParser.StartContext ctx) {
        return super.visitStart(ctx);
    }

    @Override
    public AstNode visitDcls(EzuinoParser.DclsContext ctx) {
        return super.visitDcls(ctx);
    }

    @Override
    public AstNode visitDcl(EzuinoParser.DclContext ctx) {
        return super.visitDcl(ctx);
    }

    @Override
    public AstNode visitInt_dcl(EzuinoParser.Int_dclContext ctx) {
        return super.visitInt_dcl(ctx);
    }

    @Override
    public AstNode visitDouble_dcl(EzuinoParser.Double_dclContext ctx) {
        return super.visitDouble_dcl(ctx);
    }

    @Override
    public AstNode visitBool_dcl(EzuinoParser.Bool_dclContext ctx) {
        return super.visitBool_dcl(ctx);
    }

    @Override
    public AstNode visitString_dcl(EzuinoParser.String_dclContext ctx) {
        return super.visitString_dcl(ctx);
    }

    @Override
    public AstNode visitStmts(EzuinoParser.StmtsContext ctx) {
        return super.visitStmts(ctx);
    }

    @Override
    public AstNode visitStmt(EzuinoParser.StmtContext ctx) {
        return super.visitStmt(ctx);
    }

    @Override
    public AstNode visitAssign_stmt(EzuinoParser.Assign_stmtContext ctx) {
        return super.visitAssign_stmt(ctx);
    }

    @Override
    public AstNode visitAssign_boolean(EzuinoParser.Assign_booleanContext ctx) {
        String variableID = ctx.getChild(0).getText();
        String booleanValue = ctx.getChild(2).getText();
        Bool_dclNode booleanValueAsASTNode = new Bool_dclNode(booleanValue);

        System.out.println("new AST node: " + variableID + " " + booleanValue);

        Assign_booleanNode assign_booleanNode = new Assign_booleanNode(variableID, booleanValueAsASTNode);
        ast.add(assign_booleanNode);
        return super.visitAssign_boolean(ctx);
    }

    @Override
    public AstNode visitAssign_expr(EzuinoParser.Assign_exprContext ctx) {
        return super.visitAssign_expr(ctx);
    }

    @Override
    public AstNode visitAssign_condition(EzuinoParser.Assign_conditionContext ctx) {
        return super.visitAssign_condition(ctx);
    }

    @Override
    public AstNode visitFunc_def(EzuinoParser.Func_defContext ctx) {
        return super.visitFunc_def(ctx);
    }

    @Override
    public AstNode visitFunc_call(EzuinoParser.Func_callContext ctx) {
        return super.visitFunc_call(ctx);
    }

    @Override
    public AstNode visitBuilt_in_func(EzuinoParser.Built_in_funcContext ctx) {
        System.out.println("DO NOT VISIT");
        return super.visitBuilt_in_func(ctx);
    }

    @Override
    public AstNode visitExpr(EzuinoParser.ExprContext ctx) {
        return super.visitExpr(ctx);
    }

    @Override
    public AstNode visitPrint_l(EzuinoParser.Print_lContext ctx) {
        return super.visitPrint_l(ctx);
    }

    @Override
    public AstNode visitComparator_operator(EzuinoParser.Comparator_operatorContext ctx) {
        return super.visitComparator_operator(ctx);
    }

    @Override
    public AstNode visitLogic_operator(EzuinoParser.Logic_operatorContext ctx) {
        return super.visitLogic_operator(ctx);
    }

    @Override
    public AstNode visitCondition(EzuinoParser.ConditionContext ctx) {
        return super.visitCondition(ctx);
    }

    @Override
    public AstNode visitBoolean_expr(EzuinoParser.Boolean_exprContext ctx)
    {
        System.out.println("");
        return super.visitBoolean_expr(ctx);
    }

    @Override
    public AstNode visitVal(EzuinoParser.ValContext ctx) {
        return super.visitVal(ctx);
    }

    @Override
    public AstNode visitType(EzuinoParser.TypeContext ctx) {
        return super.visitType(ctx);
    }

    @Override
    public AstNode visitBooleantf(EzuinoParser.BooleantfContext ctx) {
        return super.visitBooleantf(ctx);
    }

    @Override
    public AstNode visitList_id(EzuinoParser.List_idContext ctx) {
        return super.visitList_id(ctx);
    }

    @Override
    public AstNode visitList_size(EzuinoParser.List_sizeContext ctx) {
        return super.visitList_size(ctx);
    }

    @Override
    public AstNode visitSwitch_stmt(EzuinoParser.Switch_stmtContext ctx) {
        return super.visitSwitch_stmt(ctx);
    }

    @Override
    public AstNode visitReturn_stmt(EzuinoParser.Return_stmtContext ctx) {
        return super.visitReturn_stmt(ctx);
    }

    @Override
    public AstNode visitIf_stmt(EzuinoParser.If_stmtContext ctx) {
        return super.visitIf_stmt(ctx);
    }

    @Override
    public AstNode visitElse_stmt(EzuinoParser.Else_stmtContext ctx) {
        return super.visitElse_stmt(ctx);
    }

    @Override
    public AstNode visitIf_else(EzuinoParser.If_elseContext ctx) {
        return super.visitIf_else(ctx);
    }

    @Override
    public AstNode visitWhile_stmt(EzuinoParser.While_stmtContext ctx) {
        return super.visitWhile_stmt(ctx);
    }

    @Override
    public AstNode visitParameters(EzuinoParser.ParametersContext ctx) {
        return super.visitParameters(ctx);
    }

    @Override
    public AstNode visitParam(EzuinoParser.ParamContext ctx) {
        return super.visitParam(ctx);
    }

    @Override
    public AstNode visitBlock(EzuinoParser.BlockContext ctx) {
        return super.visitBlock(ctx);
    }

    @Override
    public AstNode visitBlock_switch(EzuinoParser.Block_switchContext ctx) {
        return super.visitBlock_switch(ctx);
    }

    @Override
    public AstNode visitList(EzuinoParser.ListContext ctx) {
        return super.visitList(ctx);
    }

    @Override
    public AstNode visitList_add(EzuinoParser.List_addContext ctx) {
        return super.visitList_add(ctx);
    }

    @Override
    public AstNode visitList_remove(EzuinoParser.List_removeContext ctx) {
        return super.visitList_remove(ctx);
    }

    public ArrayList<AstNode> getAst() {
        return ast;
    }

    public void setAst(ArrayList<AstNode> ast) {
        this.ast = ast;
    }

    public AstNode Val(String valToBeEvaluated) {
        AstNode itsAst = null;

        /* Bestem kriterier for hvornår det er hvilken data type */
        if (true) {
            itsAst = new Bool_dclNode(valToBeEvaluated);
        }
        /*
        if (ts.peek() == ID) {
            Token tid = expect(ID);
            itsAst = new SymReferencing(tid.val);
        }
        else if (ts.peek() == INUM) {
            Token tid = expect(INUM);
            itsAst = new IntConsting(tid.val);
        }
        else if (ts.peek() == FNUM) {
            Token tid = expect(FNUM);
            itsAst = new FloatConsting(tid.val);
        }
        else error("expected id, inum, or fnum");
        */
        return itsAst;
    }
}
