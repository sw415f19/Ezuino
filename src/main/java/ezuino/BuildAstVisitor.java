package ezuino;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;

import ast.*;
import generated.EzuinoBaseVisitor;
import generated.EzuinoParser;

public class BuildAstVisitor extends EzuinoBaseVisitor<AstNode> {

    @Override
    public AstNode visitStart(EzuinoParser.StartContext ctx) {

        StartNode StartNode = new StartNode();
        System.out.println("Made Start node");
        StartNode.setDcls((DclsNode) visit(ctx.dcls()));
        StartNode.setStmts((StmtsNode) visit(ctx.stmts()));
        return StartNode;
    }

    @Override
    public AstNode visitDcls(EzuinoParser.DclsContext ctx) {
        System.out.println("Made Dcls node");
        DclsNode dclsNode = new DclsNode();
        for(RuleContext child : ctx.dcl()) {
           dclsNode.addChild((DclNode) visit(child));
        }
        return dclsNode;
    }

    @Override
    public AstNode visitDcl(EzuinoParser.DclContext ctx) {
        System.out.println("Made dcl node");
        return new DclNode();
    }

    @Override
    public AstNode visitStmts(EzuinoParser.StmtsContext ctx) {
        System.out.println("Made Stmts node");
        StmtsNode stmtsNode = new StmtsNode();
        for(RuleContext child : ctx.stmt()) {
            stmtsNode.addChild((StmtNode) visit(child));
        }
        return stmtsNode;
    }

    @Override
    public AstNode visitStmt(EzuinoParser.StmtContext ctx) {
        return visit(ctx.getChild(0));
    }
    
    @Override
    public AstNode visitFunc_def(EzuinoParser.Func_defContext ctx) {
        return new Func_defNode();
    }

    @Override
    public AstNode visitFunc_call(EzuinoParser.Func_callContext ctx) {
        return new Func_callNode();
    }

    @Override
    public AstNode visitExpr(EzuinoParser.ExprContext ctx) {
        return new ExprNode();
    }

    @Override
    public AstNode visitPrint_l(EzuinoParser.Print_lContext ctx) {
        return new Print_lNode();
    }

    @Override
    public AstNode visitComparator_operator(EzuinoParser.Comparator_operatorContext ctx) {
        return new Comparator_operatorNode();
    }

    @Override
    public AstNode visitLogic_operator(EzuinoParser.Logic_operatorContext ctx) {
        return new Logic_operatorNode();
    }

    @Override
    public AstNode visitCondition(EzuinoParser.ConditionContext ctx) {
        return new ConditionNode();
    }

    @Override
    public AstNode visitBoolean_expr(EzuinoParser.Boolean_exprContext ctx) {
        return new Boolean_exprNode();
    }

    @Override
    public AstNode visitVal(EzuinoParser.ValContext ctx) {
        return new ValNode();
    }

    @Override
    public AstNode visitType(EzuinoParser.TypeContext ctx) {
        return new TypeNode();
    }

    @Override
    public AstNode visitBooleantf(EzuinoParser.BooleantfContext ctx) {
        return new BooleantfNode();
    }

    @Override
    public AstNode visitList_id(EzuinoParser.List_idContext ctx) {
        return new List_idNode();
    }

    @Override
    public AstNode visitList_size(EzuinoParser.List_sizeContext ctx) {
        return new List_sizeNode();
    }

    @Override
    public AstNode visitSwitch_stmt(EzuinoParser.Switch_stmtContext ctx) {
        return new Switch_stmtNode();
    }

    @Override
    public AstNode visitReturn_stmt(EzuinoParser.Return_stmtContext ctx) {
        return new Return_stmtNode();
    }

    @Override
    public AstNode visitIf_stmt(EzuinoParser.If_stmtContext ctx) {
        return new If_stmtNode();
    }

    @Override
    public AstNode visitElse_stmt(EzuinoParser.Else_stmtContext ctx) {
        return new Else_stmtNode();
    }

    @Override
    public AstNode visitIf_else(EzuinoParser.If_elseContext ctx) {
        return new If_elseNode();
    }

    @Override
    public AstNode visitWhile_stmt(EzuinoParser.While_stmtContext ctx) {
        return new While_stmtNode();
    }

    @Override
    public AstNode visitParameters(EzuinoParser.ParametersContext ctx) {
        return new ParametersNode();
    }

    @Override
    public AstNode visitParam(EzuinoParser.ParamContext ctx) {
        return new ParamNode();
    }

    @Override
    public AstNode visitBlock(EzuinoParser.BlockContext ctx) {
        return new BlockNode();
    }

    @Override
    public AstNode visitBlock_switch(EzuinoParser.Block_switchContext ctx) {
        return new Block_switchNode();
    }

    @Override
    public AstNode visitList(EzuinoParser.ListContext ctx) {
        return new ListNode();
    }

    @Override
    public AstNode visitList_add(EzuinoParser.List_addContext ctx) {
        return new List_addNode();
    }

    @Override
    public AstNode visitList_remove(EzuinoParser.List_removeContext ctx) {
        return new List_removeNode();
    }

    @Override
    public AstNode visitAssign_stmt(EzuinoParser.Assign_stmtContext ctx) {
        System.out.println("IN ASSIGN");

        System.out.println(ctx.children.get(2));

        if (ctx.children.get(2) instanceof EzuinoParser.ExprContext){
            System.out.println(ctx.children.get(2));
        }
         
        return new Assign_stmtNode(ctx.ID().getText());
    }

    @Override
    public AstNode visitBuilt_in_func(EzuinoParser.Built_in_funcContext ctx) {
        return new Built_in_funcNode();
    }
}
