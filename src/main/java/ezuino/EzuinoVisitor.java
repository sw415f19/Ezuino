package ezuino;
// Generated from ezuino.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import parser.EzuinoParser;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EzuinoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EzuinoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(EzuinoParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#dcls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDcls(EzuinoParser.DclsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#dcl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDcl(EzuinoParser.DclContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#stmts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmts(EzuinoParser.StmtsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(EzuinoParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(EzuinoParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#func_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_call(EzuinoParser.Func_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(EzuinoParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#print_l}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint_l(EzuinoParser.Print_lContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#comparator_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparator_operator(EzuinoParser.Comparator_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#logic_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogic_operator(EzuinoParser.Logic_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(EzuinoParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#boolean_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_expr(EzuinoParser.Boolean_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVal(EzuinoParser.ValContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(EzuinoParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#booleantf}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleantf(EzuinoParser.BooleantfContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#list_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_id(EzuinoParser.List_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#list_size}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_size(EzuinoParser.List_sizeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#switch_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitch_stmt(EzuinoParser.Switch_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#return_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_stmt(EzuinoParser.Return_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stmt(EzuinoParser.If_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#else_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_stmt(EzuinoParser.Else_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#if_else}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_else(EzuinoParser.If_elseContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#while_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_stmt(EzuinoParser.While_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(EzuinoParser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(EzuinoParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(EzuinoParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#block_switch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock_switch(EzuinoParser.Block_switchContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(EzuinoParser.ListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#list_add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_add(EzuinoParser.List_addContext ctx);
	/**
	 * Visit a parse tree produced by {@link EzuinoParser#list_remove}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_remove(EzuinoParser.List_removeContext ctx);
}