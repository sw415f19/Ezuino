package ezuino;
// Generated from ezuino.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

import parser.EzuinoParser;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link EzuinoParser}.
 */
public interface EzuinoListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by {@link EzuinoParser#start}.
     * @param ctx the parse tree
     */
    void enterStart(EzuinoParser.StartContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#start}.
     * @param ctx the parse tree
     */
    void exitStart(EzuinoParser.StartContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#dcls}.
     * @param ctx the parse tree
     */
    void enterDcls(EzuinoParser.DclsContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#dcls}.
     * @param ctx the parse tree
     */
    void exitDcls(EzuinoParser.DclsContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#dcl}.
     * @param ctx the parse tree
     */
    void enterDcl(EzuinoParser.DclContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#dcl}.
     * @param ctx the parse tree
     */
    void exitDcl(EzuinoParser.DclContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#stmts}.
     * @param ctx the parse tree
     */
    void enterStmts(EzuinoParser.StmtsContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#stmts}.
     * @param ctx the parse tree
     */
    void exitStmts(EzuinoParser.StmtsContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#stmt}.
     * @param ctx the parse tree
     */
    void enterStmt(EzuinoParser.StmtContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#stmt}.
     * @param ctx the parse tree
     */
    void exitStmt(EzuinoParser.StmtContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#func}.
     * @param ctx the parse tree
     */
    void enterFunc(EzuinoParser.FuncContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#func}.
     * @param ctx the parse tree
     */
    void exitFunc(EzuinoParser.FuncContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#func_call}.
     * @param ctx the parse tree
     */
    void enterFunc_call(EzuinoParser.Func_callContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#func_call}.
     * @param ctx the parse tree
     */
    void exitFunc_call(EzuinoParser.Func_callContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#expr}.
     * @param ctx the parse tree
     */
    void enterExpr(EzuinoParser.ExprContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#expr}.
     * @param ctx the parse tree
     */
    void exitExpr(EzuinoParser.ExprContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#print_l}.
     * @param ctx the parse tree
     */
    void enterPrint_l(EzuinoParser.Print_lContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#print_l}.
     * @param ctx the parse tree
     */
    void exitPrint_l(EzuinoParser.Print_lContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#comparator_operator}.
     * @param ctx the parse tree
     */
    void enterComparator_operator(EzuinoParser.Comparator_operatorContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#comparator_operator}.
     * @param ctx the parse tree
     */
    void exitComparator_operator(EzuinoParser.Comparator_operatorContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#logic_operator}.
     * @param ctx the parse tree
     */
    void enterLogic_operator(EzuinoParser.Logic_operatorContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#logic_operator}.
     * @param ctx the parse tree
     */
    void exitLogic_operator(EzuinoParser.Logic_operatorContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#condition}.
     * @param ctx the parse tree
     */
    void enterCondition(EzuinoParser.ConditionContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#condition}.
     * @param ctx the parse tree
     */
    void exitCondition(EzuinoParser.ConditionContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#boolean_expr}.
     * @param ctx the parse tree
     */
    void enterBoolean_expr(EzuinoParser.Boolean_exprContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#boolean_expr}.
     * @param ctx the parse tree
     */
    void exitBoolean_expr(EzuinoParser.Boolean_exprContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#val}.
     * @param ctx the parse tree
     */
    void enterVal(EzuinoParser.ValContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#val}.
     * @param ctx the parse tree
     */
    void exitVal(EzuinoParser.ValContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#type}.
     * @param ctx the parse tree
     */
    void enterType(EzuinoParser.TypeContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#type}.
     * @param ctx the parse tree
     */
    void exitType(EzuinoParser.TypeContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#booleantf}.
     * @param ctx the parse tree
     */
    void enterBooleantf(EzuinoParser.BooleantfContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#booleantf}.
     * @param ctx the parse tree
     */
    void exitBooleantf(EzuinoParser.BooleantfContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#list_id}.
     * @param ctx the parse tree
     */
    void enterList_id(EzuinoParser.List_idContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#list_id}.
     * @param ctx the parse tree
     */
    void exitList_id(EzuinoParser.List_idContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#list_size}.
     * @param ctx the parse tree
     */
    void enterList_size(EzuinoParser.List_sizeContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#list_size}.
     * @param ctx the parse tree
     */
    void exitList_size(EzuinoParser.List_sizeContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#switch_stmt}.
     * @param ctx the parse tree
     */
    void enterSwitch_stmt(EzuinoParser.Switch_stmtContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#switch_stmt}.
     * @param ctx the parse tree
     */
    void exitSwitch_stmt(EzuinoParser.Switch_stmtContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#return_stmt}.
     * @param ctx the parse tree
     */
    void enterReturn_stmt(EzuinoParser.Return_stmtContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#return_stmt}.
     * @param ctx the parse tree
     */
    void exitReturn_stmt(EzuinoParser.Return_stmtContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#if_stmt}.
     * @param ctx the parse tree
     */
    void enterIf_stmt(EzuinoParser.If_stmtContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#if_stmt}.
     * @param ctx the parse tree
     */
    void exitIf_stmt(EzuinoParser.If_stmtContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#else_stmt}.
     * @param ctx the parse tree
     */
    void enterElse_stmt(EzuinoParser.Else_stmtContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#else_stmt}.
     * @param ctx the parse tree
     */
    void exitElse_stmt(EzuinoParser.Else_stmtContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#if_else}.
     * @param ctx the parse tree
     */
    void enterIf_else(EzuinoParser.If_elseContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#if_else}.
     * @param ctx the parse tree
     */
    void exitIf_else(EzuinoParser.If_elseContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#while_stmt}.
     * @param ctx the parse tree
     */
    void enterWhile_stmt(EzuinoParser.While_stmtContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#while_stmt}.
     * @param ctx the parse tree
     */
    void exitWhile_stmt(EzuinoParser.While_stmtContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#parameters}.
     * @param ctx the parse tree
     */
    void enterParameters(EzuinoParser.ParametersContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#parameters}.
     * @param ctx the parse tree
     */
    void exitParameters(EzuinoParser.ParametersContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#param}.
     * @param ctx the parse tree
     */
    void enterParam(EzuinoParser.ParamContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#param}.
     * @param ctx the parse tree
     */
    void exitParam(EzuinoParser.ParamContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#block}.
     * @param ctx the parse tree
     */
    void enterBlock(EzuinoParser.BlockContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#block}.
     * @param ctx the parse tree
     */
    void exitBlock(EzuinoParser.BlockContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#block_switch}.
     * @param ctx the parse tree
     */
    void enterBlock_switch(EzuinoParser.Block_switchContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#block_switch}.
     * @param ctx the parse tree
     */
    void exitBlock_switch(EzuinoParser.Block_switchContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#list}.
     * @param ctx the parse tree
     */
    void enterList(EzuinoParser.ListContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#list}.
     * @param ctx the parse tree
     */
    void exitList(EzuinoParser.ListContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#list_add}.
     * @param ctx the parse tree
     */
    void enterList_add(EzuinoParser.List_addContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#list_add}.
     * @param ctx the parse tree
     */
    void exitList_add(EzuinoParser.List_addContext ctx);
    /**
     * Enter a parse tree produced by {@link EzuinoParser#list_remove}.
     * @param ctx the parse tree
     */
    void enterList_remove(EzuinoParser.List_removeContext ctx);
    /**
     * Exit a parse tree produced by {@link EzuinoParser#list_remove}.
     * @param ctx the parse tree
     */
    void exitList_remove(EzuinoParser.List_removeContext ctx);
}