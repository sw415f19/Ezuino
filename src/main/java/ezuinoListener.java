// Generated from ezuino.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ezuinoParser}.
 */
public interface ezuinoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(ezuinoParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(ezuinoParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#dcls}.
	 * @param ctx the parse tree
	 */
	void enterDcls(ezuinoParser.DclsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#dcls}.
	 * @param ctx the parse tree
	 */
	void exitDcls(ezuinoParser.DclsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#dcl}.
	 * @param ctx the parse tree
	 */
	void enterDcl(ezuinoParser.DclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#dcl}.
	 * @param ctx the parse tree
	 */
	void exitDcl(ezuinoParser.DclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#stmts}.
	 * @param ctx the parse tree
	 */
	void enterStmts(ezuinoParser.StmtsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#stmts}.
	 * @param ctx the parse tree
	 */
	void exitStmts(ezuinoParser.StmtsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(ezuinoParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(ezuinoParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(ezuinoParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(ezuinoParser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#func_call}.
	 * @param ctx the parse tree
	 */
	void enterFunc_call(ezuinoParser.Func_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#func_call}.
	 * @param ctx the parse tree
	 */
	void exitFunc_call(ezuinoParser.Func_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ezuinoParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ezuinoParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#print_l}.
	 * @param ctx the parse tree
	 */
	void enterPrint_l(ezuinoParser.Print_lContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#print_l}.
	 * @param ctx the parse tree
	 */
	void exitPrint_l(ezuinoParser.Print_lContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#comparator_operator}.
	 * @param ctx the parse tree
	 */
	void enterComparator_operator(ezuinoParser.Comparator_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#comparator_operator}.
	 * @param ctx the parse tree
	 */
	void exitComparator_operator(ezuinoParser.Comparator_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#logic_operator}.
	 * @param ctx the parse tree
	 */
	void enterLogic_operator(ezuinoParser.Logic_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#logic_operator}.
	 * @param ctx the parse tree
	 */
	void exitLogic_operator(ezuinoParser.Logic_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(ezuinoParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(ezuinoParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#boolean_expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_expr(ezuinoParser.Boolean_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#boolean_expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_expr(ezuinoParser.Boolean_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#val}.
	 * @param ctx the parse tree
	 */
	void enterVal(ezuinoParser.ValContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#val}.
	 * @param ctx the parse tree
	 */
	void exitVal(ezuinoParser.ValContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(ezuinoParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(ezuinoParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#booleantf}.
	 * @param ctx the parse tree
	 */
	void enterBooleantf(ezuinoParser.BooleantfContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#booleantf}.
	 * @param ctx the parse tree
	 */
	void exitBooleantf(ezuinoParser.BooleantfContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#list_id}.
	 * @param ctx the parse tree
	 */
	void enterList_id(ezuinoParser.List_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#list_id}.
	 * @param ctx the parse tree
	 */
	void exitList_id(ezuinoParser.List_idContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#list_size}.
	 * @param ctx the parse tree
	 */
	void enterList_size(ezuinoParser.List_sizeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#list_size}.
	 * @param ctx the parse tree
	 */
	void exitList_size(ezuinoParser.List_sizeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#switch_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSwitch_stmt(ezuinoParser.Switch_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#switch_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSwitch_stmt(ezuinoParser.Switch_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturn_stmt(ezuinoParser.Return_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturn_stmt(ezuinoParser.Return_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIf_stmt(ezuinoParser.If_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIf_stmt(ezuinoParser.If_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#else_stmt}.
	 * @param ctx the parse tree
	 */
	void enterElse_stmt(ezuinoParser.Else_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#else_stmt}.
	 * @param ctx the parse tree
	 */
	void exitElse_stmt(ezuinoParser.Else_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#if_else}.
	 * @param ctx the parse tree
	 */
	void enterIf_else(ezuinoParser.If_elseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#if_else}.
	 * @param ctx the parse tree
	 */
	void exitIf_else(ezuinoParser.If_elseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhile_stmt(ezuinoParser.While_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhile_stmt(ezuinoParser.While_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(ezuinoParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(ezuinoParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(ezuinoParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(ezuinoParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ezuinoParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ezuinoParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#block_switch}.
	 * @param ctx the parse tree
	 */
	void enterBlock_switch(ezuinoParser.Block_switchContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#block_switch}.
	 * @param ctx the parse tree
	 */
	void exitBlock_switch(ezuinoParser.Block_switchContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(ezuinoParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(ezuinoParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#list_add}.
	 * @param ctx the parse tree
	 */
	void enterList_add(ezuinoParser.List_addContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#list_add}.
	 * @param ctx the parse tree
	 */
	void exitList_add(ezuinoParser.List_addContext ctx);
	/**
	 * Enter a parse tree produced by {@link ezuinoParser#list_remove}.
	 * @param ctx the parse tree
	 */
	void enterList_remove(ezuinoParser.List_removeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ezuinoParser#list_remove}.
	 * @param ctx the parse tree
	 */
	void exitList_remove(ezuinoParser.List_removeContext ctx);
}