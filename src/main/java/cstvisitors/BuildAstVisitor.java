package cstvisitors;

import java.util.ArrayList;

import ast.*;
import generated.EzuinoBaseVisitor;
import generated.EzuinoParser;
import generated.EzuinoParser.ExprContext;
import ast.expr.ParenthesisExprNode;
import ast.expr.UnaryExprNode;
import ast.expr.iexpr.*;
import ast.type.*;
import ast.expr.*;

public class BuildAstVisitor extends EzuinoBaseVisitor<AstNode> {
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
    public AstNode visitStmts(EzuinoParser.StmtsContext ctx) {
        return super.visitStmts(ctx);
    }

    @Override
    public AstNode visitStmt(EzuinoParser.StmtContext ctx) {
        return super.visitStmt(ctx);
    }

    @Override
    public AstNode visitAssign_stmt(EzuinoParser.Assign_stmtContext ctx) {
        // Casts the recieving node to an ExprNode
        return new Assign_stmtNode(ctx.ID().getText(), (IExpr) ctx.expr().accept(this));
    }

    @Override
    public AstNode visitPrimaryExpr(EzuinoParser.PrimaryExprContext ctx) {
        if (ctx.val() != null) {
            return ctx.val().accept(this);
        }
        if (ctx.booleantf() != null) {
            return ctx.booleantf().accept(this);
        }
        if (ctx.func_call() != null) {
            return ctx.func_call().accept(this);
        }
        return null;
    }

    @Override
    public AstNode visitParenthesisExpr(EzuinoParser.ParenthesisExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return ctx.primaryExpr().accept(this);
        }
        return new ParenthesisExprNode((IExpr) ctx.expr().accept(this));
    }

    @Override
    public AstNode visitUnaryExpr(EzuinoParser.UnaryExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return ctx.parenthesisExpr().accept(this);
        }
        return new UnaryExprNode(ctx.MINUS().getText(), (IParenthesisExpr) ctx.parenthesisExpr().accept(this));
    }

    @Override
    public AstNode visitMultiplicativeExpr(EzuinoParser.MultiplicativeExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return ctx.unaryExpr().accept(this);
        }
        return new MultiplicativeExprNode((IMultiplicativeExpr) ctx.multiplicativeExpr().accept(this),
                ctx.operator.getText(), (IUnaryExpr) ctx.unaryExpr().accept(this));
    }

    @Override
    public AstNode visitAdditiveExpr(EzuinoParser.AdditiveExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return ctx.multiplicativeExpr().accept(this);
        }
        return new AdditiveExprNode((IAddictiveExpr) ctx.additiveExpr().accept(this), ctx.operator.getText(),
                (IMultiplicativeExpr) ctx.multiplicativeExpr().accept(this));
    }

    @Override
    public AstNode visitRelationalExpr(EzuinoParser.RelationalExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return ctx.additiveExpr().accept(this);
        }
        return new RelationalExprNode((IRelationalExpr) ctx.relationalExpr().accept(this), ctx.operator.getText(),
                (IAddictiveExpr) ctx.additiveExpr().accept(this));
    }

    @Override
    public AstNode visitEqualityExpr(EzuinoParser.EqualityExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return ctx.relationalExpr().accept(this);
        }
        return new EqualityExprNode((IEqualityExpr) ctx.equalityExpr().accept(this), ctx.operator.getText(),
                (IRelationalExpr) ctx.relationalExpr().accept(this));
    }

    @Override
    public AstNode visitLogicalAndExpr(EzuinoParser.LogicalAndExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return ctx.equalityExpr().accept(this);
        }
        return new LogicalAndExprNode((ILogicalAndExpr) ctx.logicalAndExpr().accept(this),
                (IEqualityExpr) ctx.equalityExpr().accept(this));
    }

    @Override
    public AstNode visitLogicalOrExpr(EzuinoParser.LogicalOrExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return ctx.logicalAndExpr().accept(this);
        }
        return new LogicalOrExprNode((IlogicalOrExpr) ctx.logicalOrExpr().accept(this),
                (ILogicalAndExpr) ctx.logicalAndExpr().accept(this));
    }

    @Override
    public AstNode visitExpr(EzuinoParser.ExprContext ctx) {
        return ctx.logicalOrExpr().accept(this);
    }

    @Override
    public AstNode visitFunc_def(EzuinoParser.Func_defContext ctx) {
        return super.visitFunc_def(ctx);
    }

    @Override
    public AstNode visitFunc_call(EzuinoParser.Func_callContext ctx) {
        if (ctx.getChildCount() > 1){
            return new Func_callNode(ctx.ID().getText(),
                    (Func_Call_ParamNode) ctx.func_call_param().accept(this));
        }

        if (ctx.getChildCount() == 1){
            return new Func_callNode((Built_in_funcNode) ctx.built_in_func().accept(this));
        }

        return null;
    }

    @Override
    public AstNode visitFunc_call_param(EzuinoParser.Func_call_paramContext ctx) {
        if (ctx.getChildCount() == 0) {
            return null;
        }
        ArrayList<IExpr> expr = new ArrayList<IExpr>();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (ctx.expr(i) instanceof ExprContext) {
                expr.add((IExpr) ctx.expr(i).accept(this));
            }
        }
        return new Func_Call_ParamNode(expr);
    }

    @Override
    public AstNode visitBuilt_in_func(EzuinoParser.Built_in_funcContext ctx) {
        if(ctx.getChild(0) instanceof EzuinoParser.List_addContext){
            return null;
        }
        return null;
    }

    @Override
    public AstNode visitPrint_l(EzuinoParser.Print_lContext ctx) {
        return new Print_lNode((IExpr) ctx.expr().accept(this));
    }

    @Override
    public AstNode visitVal(EzuinoParser.ValContext ctx) {
        System.out.println("IN VAL");

        if (ctx.INTEGER() != null) {
            return new IntegerNode(ctx.INTEGER().getText());
        }

        if (ctx.DOUBLE() != null) {
            return new DoubleNode(ctx.DOUBLE().getText());
        }

        if (ctx.STRING() != null) {
            return new StringNode(ctx.STRING().getText());
        }

        if (ctx.ID() != null) {
            return new IdNode(ctx.ID().getText());
        }

        return null;
    }

    @Override
    public AstNode visitBooleantf(EzuinoParser.BooleantfContext ctx) {
        return new BooleantfNode(ctx.getText());
    }

    @Override
    public AstNode visitType(EzuinoParser.TypeContext ctx) {
        return super.visitType(ctx);
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
    public AstNode visitBoolean_dcl(EzuinoParser.Boolean_dclContext ctx) {
        return super.visitBoolean_dcl(ctx);
    }

    @Override
    public AstNode visitString_dcl(EzuinoParser.String_dclContext ctx) {
        return super.visitString_dcl(ctx);
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
    public AstNode visitSwitch_block(EzuinoParser.Switch_blockContext ctx) {
        return super.visitSwitch_block(ctx);
    }

    @Override
    public AstNode visitList_dcl(EzuinoParser.List_dclContext ctx) {
        return super.visitList_dcl(ctx);
    }

    @Override
    public AstNode visitList_add(EzuinoParser.List_addContext ctx) {
        return new List_addNode(ctx.ID().getText(), (ValNode) ctx.val().accept(this), new IntegerNode(ctx.INTEGER().getText()));
    }

    @Override
    public AstNode visitList_remove(EzuinoParser.List_removeContext ctx) {
        // Casts the recieving node to an ExprNode
        return new List_removeNode(ctx.ID().getText(), new IntegerNode(ctx.INTEGER().getText()), (ValNode)(ctx.val().accept(this)));
    }
}