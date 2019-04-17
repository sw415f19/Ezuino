package cstvisitors;

import java.util.ArrayList;
import java.util.List;

import ast.*;
import generated.EzuinoBaseVisitor;
import generated.EzuinoParser;
import generated.EzuinoParser.DclContext;
import generated.EzuinoParser.StmtContext;
import generated.EzuinoParser.ExprContext;
import ast.expr.ParenthesisExprNode;
import ast.expr.UnaryExprNode;
import ast.expr.aexpr.*;
import ast.type.*;
import ast.expr.*;

public class BuildAstVisitor extends EzuinoBaseVisitor<AstNode> {
    @Override
    public AstNode visitStart(EzuinoParser.StartContext ctx) {
        StmtsNode stmts = (StmtsNode) ctx.stmts().accept(this);
        DclsNode dcls = (DclsNode) ctx.dcls().accept(this);

        return new StartNode(dcls, stmts);
    }

    @Override
    public AstNode visitDcls(EzuinoParser.DclsContext ctx) {
        DclsNode dcls = new DclsNode();
        for (DclContext dcl : ctx.dcl()) {
            DclNode child = (DclNode) dcl.accept(this);
            dcls.addChild(child);
        }
        return dcls;
    }

    @Override
    public AstNode visitDcl(EzuinoParser.DclContext ctx) {

        Type type = getType(ctx.type());
        return new DclNode(type, ctx.ID().getText());
    }

    @Override
    public AstNode visitStmts(EzuinoParser.StmtsContext ctx) {
        StmtsNode stmts = new StmtsNode();
        for (StmtContext stmt : ctx.stmt()) {
            StmtNode child = (StmtNode) stmt.accept(this);
            stmts.addChild(child);
        }
        return stmts;
    }

    @Override
    public AstNode visitStmt(EzuinoParser.StmtContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public AstNode visitAssign_stmt(EzuinoParser.Assign_stmtContext ctx) {
        return new Assign_stmtNode(ctx.ID().getText(), (AExpr) ctx.expr().accept(this));
    }

    @Override
    public AstNode visitPrimaryExpr(EzuinoParser.PrimaryExprContext ctx) {
        if (ctx.val() != null) {
            return ctx.val().accept(this);
        }
        if (ctx.BOOL() != null) {
            return new BooleanLiteral(ctx.BOOL().getText());
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
        return new ParenthesisExprNode((AExpr) ctx.expr().accept(this));
    }

    @Override
    public AstNode visitUnaryExpr(EzuinoParser.UnaryExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return ctx.parenthesisExpr().accept(this);
        }
        return new UnaryExprNode(ctx.op.getText(), (AParenthesisExpr) ctx.parenthesisExpr().accept(this));
    }

    @Override
    public AstNode visitMultiplicativeExpr(EzuinoParser.MultiplicativeExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return ctx.unaryExpr().accept(this);
        }
        return new MultiplicativeExprNode((AMultiplicativeExpr) ctx.multiplicativeExpr().accept(this),
                ctx.operator.getText(), (AUnaryExpr) ctx.unaryExpr().accept(this));
    }

    @Override
    public AstNode visitAdditiveExpr(EzuinoParser.AdditiveExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return ctx.multiplicativeExpr().accept(this);
        }
        return new AdditiveExprNode((AAddictiveExpr) ctx.additiveExpr().accept(this), ctx.operator.getText(),
                (AMultiplicativeExpr) ctx.multiplicativeExpr().accept(this));
    }

    @Override
    public AstNode visitRelationalExpr(EzuinoParser.RelationalExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return ctx.additiveExpr().accept(this);
        }
        return new RelationalExprNode((ARelationalExpr) ctx.relationalExpr().accept(this), ctx.operator.getText(),
                (AAddictiveExpr) ctx.additiveExpr().accept(this));
    }

    @Override
    public AstNode visitEqualityExpr(EzuinoParser.EqualityExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return ctx.relationalExpr().accept(this);
        }
        return new EqualityExprNode((AEqualityExpr) ctx.equalityExpr().accept(this), ctx.operator.getText(),
                (ARelationalExpr) ctx.relationalExpr().accept(this));
    }

    @Override
    public AstNode visitLogicalAndExpr(EzuinoParser.LogicalAndExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return ctx.equalityExpr().accept(this);
        }
        return new LogicalAndExprNode((ALogicalAndExpr) ctx.logicalAndExpr().accept(this),
                (AEqualityExpr) ctx.equalityExpr().accept(this));
    }

    @Override
    public AstNode visitLogicalOrExpr(EzuinoParser.LogicalOrExprContext ctx) {
        if (ctx.getChildCount() == 1) {
            return ctx.logicalAndExpr().accept(this);
        }
        return new LogicalOrExprNode((AlogicalOrExpr) ctx.logicalOrExpr().accept(this),
                (ALogicalAndExpr) ctx.logicalAndExpr().accept(this));
    }

    @Override
    public AstNode visitExpr(EzuinoParser.ExprContext ctx) {
        return ctx.logicalOrExpr().accept(this);
    }

    @Override
    public AstNode visitFunc_def(EzuinoParser.Func_defContext ctx) {
        String ID = ctx.ID().getText();
        Type type = getType(ctx.type());
        List<DclNode> parameters = new ArrayList<DclNode>();
        BlockNode blockNode = (BlockNode) ctx.block().accept(this);
        for (DclContext child : ctx.parameters().dcl()) {
            parameters.add((DclNode) child.accept(this));
        }
        return new Func_defNode(ID, type, parameters, blockNode);
    }

    @Override
    public AstNode visitFunc_call(EzuinoParser.Func_callContext ctx) {
        String id = ctx.ID().getText();
        ArrayList<AExpr> parameters = new ArrayList<AExpr>();

        for (ExprContext param : ctx.func_call_param().expr()) {
            parameters.add((AExpr) param.accept(this));
        }

        if (ctx.parent instanceof EzuinoParser.PrimaryExprContext) {
            return new Func_callExprNode(id, parameters);
        }
        return new Func_callStmtNode(id, parameters);
    }

    @Override
    public AstNode visitVal(EzuinoParser.ValContext ctx) {

        if (ctx.INTEGER() != null) {
            return new IntegerLiteral(ctx.INTEGER().getText());
        }

        if (ctx.DOUBLE() != null) {
            return new DoubleLiteral(ctx.DOUBLE().getText());
        }

        if (ctx.STRING() != null) {
            return new StringLiteral(ctx.STRING().getText());
        }

        if (ctx.ID() != null) {
            return new IdNode(ctx.ID().getText());
        }

        return null;
    }

    @Override
    public AstNode visitReturn_stmt(EzuinoParser.Return_stmtContext ctx) {
        AExpr aExpr = null;
        if(ctx.expr() != null) {
            aExpr = (AExpr) ctx.expr().accept(this);
        }
        return new Return_stmtNode(aExpr);
    }

    @Override
    public AstNode visitIf_stmt(EzuinoParser.If_stmtContext ctx) {
        AExpr expr = (AExpr) ctx.expr().accept(this);
        BlockNode ifBlock = (BlockNode) ctx.block(0).accept(this);
        BlockNode elseBlock = null;
        if (ctx.block().size() > 1) {
            elseBlock = (BlockNode) ctx.block(1).accept(this);
        }
        return new If_stmtNode(expr, ifBlock, elseBlock);
    }

    @Override
    public AstNode visitWhile_stmt(EzuinoParser.While_stmtContext ctx) {
        AExpr expressionNode = (AExpr) ctx.expr().accept(this);
        BlockNode blockNode = (BlockNode) ctx.block().accept(this);
        return new While_stmtNode(expressionNode, blockNode);
    }

    @Override
    public AstNode visitBlock(EzuinoParser.BlockContext ctx) {
        DclsNode dcls = (DclsNode) ctx.dcls().accept(this);
        StmtsNode stmts = (StmtsNode) ctx.stmts().accept(this);
        Return_stmtNode returnstmts = null;
        if (ctx.return_stmt() != null) {
            returnstmts = (Return_stmtNode) ctx.return_stmt().accept(this);
        }
        return new BlockNode(dcls, stmts, returnstmts);
    }

    public Type getType(EzuinoParser.TypeContext ctx) {
        Type type = null;
        if (ctx == null) {
        	return Type.VOID;
        }
        if (ctx.INTDCL() != null) {
            type = Type.INT;
        }
        if (ctx.DOUBLEDCL() != null) {
            type = Type.DOUBLE;
        }
        if (ctx.STRINGDCL() != null) {
            type = Type.STRING;
        }
        if (ctx.BOOLEANDCL() != null) {
            type = Type.BOOL;
        }
        return type;
    }
}