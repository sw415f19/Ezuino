package cstvisitors;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import ast.*;
import generated.EzuinoBaseVisitor;
import generated.EzuinoParser;
import generated.EzuinoParser.DclContext;
import generated.EzuinoParser.StmtContext;
import generated.EzuinoParser.ExprContext;
import generated.EzuinoParser.ParamContext;
import ast.expr.ParenthesisExprNode;
import ast.expr.UnaryExprNode;
import ast.expr.iexpr.*;
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
    	for(DclContext dcl : ctx.dcl()) {
    		DclNode child = (DclNode) dcl.accept(this);
    		dcls.addChild(child);
    	}
        return dcls;
    }

    @Override
    public AstNode visitDcl(EzuinoParser.DclContext ctx) {
        if(ctx.list_dcl() != null) {
            return ctx.list_dcl().accept(this);
        }
        else {
            Type type = getType(ctx.type());
            return new DclNode(type, ctx.ID().getText());
        }
    }

    @Override
    public AstNode visitStmts(EzuinoParser.StmtsContext ctx) {
    	StmtsNode stmts = new StmtsNode();
    	for(StmtContext stmt : ctx.stmt()) {
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
    	List<ParamNode> parameters = new ArrayList<ParamNode>();
    	BlockNode blockNode = (BlockNode)ctx.block().accept(this);
    	for(ParamContext child : ctx.parameters().param()) {
    		parameters.add((ParamNode)child.accept(this));
    	}
        return new Func_defNode(parameters, blockNode);
    }

    @Override
    public AstNode visitFunc_call(EzuinoParser.Func_callContext ctx) {

    	Func_callNode result = null;
    	
        if (ctx.built_in_func() == null){
        	String id = ctx.ID().getText();
        	Func_Call_ParamNode funcCall = (Func_Call_ParamNode) ctx.func_call_param().accept(this);
            result =  new Func_callNode(id, funcCall);
        } else {
            result =  new Func_callNode((Built_in_funcNode) ctx.built_in_func().accept(this));
        	
        }
        return result;
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
            return new Built_in_funcNode((List_addNode) ctx.list_add().accept(this));
        }

        if(ctx.getChild(0) instanceof EzuinoParser.List_removeContext){
            return new Built_in_funcNode((List_removeNode) ctx.list_remove().accept(this));
        }

        if(ctx.getChild(0) instanceof EzuinoParser.Print_lContext){
            return new Built_in_funcNode((Print_lNode) ctx.print_l().accept(this));
        }

        return null;
    }

    @Override
    public AstNode visitPrint_l(EzuinoParser.Print_lContext ctx) {
        return new Print_lNode((IExpr) ctx.expr().accept(this));
    }

    @Override
    public AstNode visitVal(EzuinoParser.ValContext ctx) {

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
        return new Return_stmtNode((IExpr)ctx.expr().accept(this));
    }

    @Override
    public AstNode visitIf_stmt(EzuinoParser.If_stmtContext ctx) {
    	IExpr expr = (IExpr) ctx.expr().accept(this);
    	BlockNode trueBlock = (BlockNode) ctx.block(0).accept(this);
    	BlockNode falseBlock = null;
    	if (ctx.block().size() > 1) {
    		falseBlock = (BlockNode) ctx.block(1).accept(this);
    	}
        return new If_stmtNode(expr, trueBlock, falseBlock);
    }

    @Override
    public AstNode visitWhile_stmt(EzuinoParser.While_stmtContext ctx) {
    	IExpr expressionNode = (IExpr)ctx.expr().accept(this);
    	BlockNode blockNode = (BlockNode)ctx.block().accept(this);
        return new While_stmtNode(expressionNode, blockNode);
    }

    @Override
    public AstNode visitParameters(EzuinoParser.ParametersContext ctx) {
        return super.visitParameters(ctx);
    }

    @Override
    public AstNode visitParam(EzuinoParser.ParamContext ctx) {
        return new ParamNode(this.getType(ctx.type()), ctx.ID().getText());
    }

    @Override
    public AstNode visitBlock(EzuinoParser.BlockContext ctx) {
    	DclsNode dcls = (DclsNode) ctx.dcls().accept(this);
    	StmtsNode stmts = (StmtsNode) ctx.stmts().accept(this);
    	Return_stmtNode returnstmts = null;
        if (ctx.return_stmt() != null){
        	returnstmts = (Return_stmtNode) ctx.return_stmt().accept(this);
        }
        return new BlockNode(dcls, stmts, returnstmts);
    }


    @Override
    public AstNode visitSwitch_block(EzuinoParser.Switch_blockContext ctx) {
        return super.visitSwitch_block(ctx);
    }

    @Override
    public AstNode visitList_dcl(EzuinoParser.List_dclContext ctx) {
        Type type = getType(ctx.type());
        return new List_dclNode(type, ctx.ID().getText(), ctx.INTEGER().getText());
    }

    @Override
    public AstNode visitList_add(EzuinoParser.List_addContext ctx) {
    	String id = ctx.ID().getText();
    	String index = ctx.INTEGER().getText();
    	ValNode val = (ValNode) ctx.val().accept(this);
        return new List_addNode(id, val, index);
    }

    @Override
    public AstNode visitList_remove(EzuinoParser.List_removeContext ctx) {
        // Casts the recieving node to an ExprNode
        return new List_removeNode(ctx.ID().getText(), (ValNode)(ctx.val().accept(this)), new IntegerNode(ctx.INTEGER().getText()));
    }

    public Type getType(EzuinoParser.TypeContext ctx){
        Type type = null;
        if(ctx.int_dcl() != null) {
            type = Type.INT;
        }
        if(ctx.double_dcl() != null) {
            type = Type.DOUBLE;
        }
        if(ctx.string_dcl() != null) {
            type = Type.STRING;
        }
        if(ctx.boolean_dcl() != null) {
            type = Type.BOOL;
        }
        return type;
    }
}