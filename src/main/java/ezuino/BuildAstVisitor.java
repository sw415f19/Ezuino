package ezuino;

import org.antlr.v4.runtime.RuleContext;

import ast.AstNode;
import ast.BlockNode;
import ast.Block_switchNode;
import ast.Boolean_exprNode;
import ast.BooleantfNode;
import ast.Comparator_operatorNode;
import ast.ConditionNode;
import ast.DclNode;
import ast.DclsNode;
import ast.Else_stmtNode;
import ast.ExprNode;
import ast.FuncNode;
import ast.Func_callNode;
import ast.If_elseNode;
import ast.If_stmtNode;
import ast.ListNode;
import ast.List_addNode;
import ast.List_idNode;
import ast.List_removeNode;
import ast.List_sizeNode;
import ast.Logic_operatorNode;
import ast.ParamNode;
import ast.ParametersNode;
import ast.Print_lNode;
import ast.Return_stmtNode;
import ast.StartNode;
import ast.StmtNode;
import ast.StmtsNode;
import ast.Switch_stmtNode;
import ast.TypeNode;
import ast.ValNode;
import ast.While_stmtNode;
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
        System.out.println("Made stmt node");
        return new StmtsNode();
    }

    @Override
    public AstNode visitFunc(EzuinoParser.FuncContext ctx) {
        return new FuncNode();
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
}
