// Generated from /Users/sembrik/Ezuino/src/main/java/ezuino/Ezuino.g4 by ANTLR 4.7.1

package generated;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EzuinoParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, INTDCL=7, DOUBLEDCL=8, 
		STRINGDCL=9, BOOLDCL=10, LISTDCL=11, PRINTSTMT=12, ASSIGN=13, LISTADD=14, 
		LISTREMOVE=15, PLUS=16, MINUS=17, DIVIDE=18, MULTIPLE=19, AND=20, OR=21, 
		LESS=22, GREATER=23, EQUAL=24, NOTEQUAL=25, NOT=26, LESSTHANOREQUAL=27, 
		GREATERTHANOREQUAL=28, ELSE=29, IF=30, WHILE=31, TRUE=32, FALSE=33, SWITCH=34, 
		CASE=35, RETURN=36, FUNCTION=37, DEFAULT=38, ID=39, SBRACE=40, EBRACE=41, 
		INTEGER=42, DOUBLE=43, STRING=44, BLANK=45, COMMENT=46;
	public static final int
		RULE_start = 0, RULE_dcls = 1, RULE_dcl = 2, RULE_stmts = 3, RULE_stmt = 4, 
		RULE_assign_stmt = 5, RULE_func_def = 6, RULE_func_call = 7, RULE_built_in_func = 8, 
		RULE_expr = 9, RULE_print_l = 10, RULE_comparator_operator = 11, RULE_logic_operator = 12, 
		RULE_condition = 13, RULE_boolean_expr = 14, RULE_val = 15, RULE_type = 16, 
		RULE_booleantf = 17, RULE_list_id = 18, RULE_list_size = 19, RULE_switch_stmt = 20, 
		RULE_return_stmt = 21, RULE_if_stmt = 22, RULE_else_stmt = 23, RULE_if_else = 24, 
		RULE_while_stmt = 25, RULE_parameters = 26, RULE_param = 27, RULE_block = 28, 
		RULE_block_switch = 29, RULE_list = 30, RULE_list_add = 31, RULE_list_remove = 32;
	public static final String[] ruleNames = {
		"start", "dcls", "dcl", "stmts", "stmt", "assign_stmt", "func_def", "func_call", 
		"built_in_func", "expr", "print_l", "comparator_operator", "logic_operator", 
		"condition", "boolean_expr", "val", "type", "booleantf", "list_id", "list_size", 
		"switch_stmt", "return_stmt", "if_stmt", "else_stmt", "if_else", "while_stmt", 
		"parameters", "param", "block", "block_switch", "list", "list_add", "list_remove"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "','", "')'", "'['", "']'", "':'", "'int'", "'double'", "'string'", 
		"'boolean'", "'list'", "'print'", "':='", "'list_add'", "'list_remove'", 
		"'+'", "'-'", "'/'", "'*'", "'AND'", "'OR'", "'<'", "'>'", "'='", "'!='", 
		"'!'", "'<='", "'>='", "'else'", "'if'", "'while'", "'TRUE'", "'FALSE'", 
		"'switch'", "'case'", "'return'", "'func'", "'default'", null, "'{'", 
		"'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "INTDCL", "DOUBLEDCL", "STRINGDCL", 
		"BOOLDCL", "LISTDCL", "PRINTSTMT", "ASSIGN", "LISTADD", "LISTREMOVE", 
		"PLUS", "MINUS", "DIVIDE", "MULTIPLE", "AND", "OR", "LESS", "GREATER", 
		"EQUAL", "NOTEQUAL", "NOT", "LESSTHANOREQUAL", "GREATERTHANOREQUAL", "ELSE", 
		"IF", "WHILE", "TRUE", "FALSE", "SWITCH", "CASE", "RETURN", "FUNCTION", 
		"DEFAULT", "ID", "SBRACE", "EBRACE", "INTEGER", "DOUBLE", "STRING", "BLANK", 
		"COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Ezuino.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public EzuinoParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public DclsContext dcls() {
			return getRuleContext(DclsContext.class,0);
		}
		public StmtsContext stmts() {
			return getRuleContext(StmtsContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			dcls();
			setState(67);
			stmts();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DclsContext extends ParserRuleContext {
		public List<DclContext> dcl() {
			return getRuleContexts(DclContext.class);
		}
		public DclContext dcl(int i) {
			return getRuleContext(DclContext.class,i);
		}
		public DclsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dcls; }
	}

	public final DclsContext dcls() throws RecognitionException {
		DclsContext _localctx = new DclsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_dcls);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTDCL) | (1L << DOUBLEDCL) | (1L << STRINGDCL) | (1L << BOOLDCL) | (1L << LISTDCL))) != 0)) {
				{
				{
				setState(69);
				dcl();
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DclContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(EzuinoParser.ID, 0); }
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public DclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dcl; }
	}

	public final DclContext dcl() throws RecognitionException {
		DclContext _localctx = new DclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_dcl);
		try {
			setState(79);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTDCL:
			case DOUBLEDCL:
			case STRINGDCL:
			case BOOLDCL:
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				type();
				setState(76);
				match(ID);
				}
				break;
			case LISTDCL:
				enterOuterAlt(_localctx, 2);
				{
				setState(78);
				list();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtsContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public StmtsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmts; }
	}

	public final StmtsContext stmts() throws RecognitionException {
		StmtsContext _localctx = new StmtsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_stmts);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PRINTSTMT) | (1L << LISTADD) | (1L << LISTREMOVE) | (1L << IF) | (1L << WHILE) | (1L << SWITCH) | (1L << FUNCTION) | (1L << ID))) != 0)) {
				{
				{
				setState(81);
				stmt();
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public Assign_stmtContext assign_stmt() {
			return getRuleContext(Assign_stmtContext.class,0);
		}
		public While_stmtContext while_stmt() {
			return getRuleContext(While_stmtContext.class,0);
		}
		public Func_callContext func_call() {
			return getRuleContext(Func_callContext.class,0);
		}
		public Func_defContext func_def() {
			return getRuleContext(Func_defContext.class,0);
		}
		public If_elseContext if_else() {
			return getRuleContext(If_elseContext.class,0);
		}
		public Switch_stmtContext switch_stmt() {
			return getRuleContext(Switch_stmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_stmt);
		try {
			setState(93);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				assign_stmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(88);
				while_stmt();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(89);
				func_call();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(90);
				func_def();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(91);
				if_else();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(92);
				switch_stmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assign_stmtContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(EzuinoParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(EzuinoParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BooleantfContext booleantf() {
			return getRuleContext(BooleantfContext.class,0);
		}
		public TerminalNode NOT() { return getToken(EzuinoParser.NOT, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public Assign_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_stmt; }
	}

	public final Assign_stmtContext assign_stmt() throws RecognitionException {
		Assign_stmtContext _localctx = new Assign_stmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assign_stmt);
		int _la;
		try {
			setState(107);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				match(ID);
				setState(96);
				match(ASSIGN);
				setState(97);
				expr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				match(ID);
				setState(99);
				match(ASSIGN);
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(100);
					match(NOT);
					}
				}

				setState(103);
				booleantf();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(104);
				match(ID);
				setState(105);
				match(ASSIGN);
				setState(106);
				condition();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Func_defContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(EzuinoParser.FUNCTION, 0); }
		public TerminalNode ID() { return getToken(EzuinoParser.ID, 0); }
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Func_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_def; }
	}

	public final Func_defContext func_def() throws RecognitionException {
		Func_defContext _localctx = new Func_defContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_func_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(FUNCTION);
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTDCL) | (1L << DOUBLEDCL) | (1L << STRINGDCL) | (1L << BOOLDCL))) != 0)) {
				{
				setState(110);
				type();
				}
			}

			setState(113);
			match(ID);
			setState(114);
			parameters();
			setState(115);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Func_callContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(EzuinoParser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Built_in_funcContext built_in_func() {
			return getRuleContext(Built_in_funcContext.class,0);
		}
		public Func_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_call; }
	}

	public final Func_callContext func_call() throws RecognitionException {
		Func_callContext _localctx = new Func_callContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_func_call);
		int _la;
		try {
			setState(133);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				match(ID);
				setState(118);
				match(T__0);
				setState(129);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(120);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << PRINTSTMT) | (1L << LISTADD) | (1L << LISTREMOVE) | (1L << ID) | (1L << INTEGER) | (1L << DOUBLE) | (1L << STRING))) != 0)) {
						{
						setState(119);
						expr(0);
						}
					}

					}
					break;
				case 2:
					{
					setState(122);
					expr(0);
					setState(125); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(123);
						match(T__1);
						setState(124);
						expr(0);
						}
						}
						setState(127); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__1 );
					}
					break;
				}
				setState(131);
				match(T__2);
				}
				break;
			case PRINTSTMT:
			case LISTADD:
			case LISTREMOVE:
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				built_in_func();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Built_in_funcContext extends ParserRuleContext {
		public Print_lContext print_l() {
			return getRuleContext(Print_lContext.class,0);
		}
		public List_addContext list_add() {
			return getRuleContext(List_addContext.class,0);
		}
		public List_removeContext list_remove() {
			return getRuleContext(List_removeContext.class,0);
		}
		public Built_in_funcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_built_in_func; }
	}

	public final Built_in_funcContext built_in_func() throws RecognitionException {
		Built_in_funcContext _localctx = new Built_in_funcContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_built_in_func);
		try {
			setState(138);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PRINTSTMT:
				enterOuterAlt(_localctx, 1);
				{
				setState(135);
				print_l();
				}
				break;
			case LISTADD:
				enterOuterAlt(_localctx, 2);
				{
				setState(136);
				list_add();
				}
				break;
			case LISTREMOVE:
				enterOuterAlt(_localctx, 3);
				{
				setState(137);
				list_remove();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ValContext val() {
			return getRuleContext(ValContext.class,0);
		}
		public Func_callContext func_call() {
			return getRuleContext(Func_callContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(EzuinoParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(EzuinoParser.MINUS, 0); }
		public TerminalNode MULTIPLE() { return getToken(EzuinoParser.MULTIPLE, 0); }
		public TerminalNode DIVIDE() { return getToken(EzuinoParser.DIVIDE, 0); }
		public Logic_operatorContext logic_operator() {
			return getRuleContext(Logic_operatorContext.class,0);
		}
		public Comparator_operatorContext comparator_operator() {
			return getRuleContext(Comparator_operatorContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(141);
				val();
				}
				break;
			case 2:
				{
				setState(142);
				func_call();
				}
				break;
			case 3:
				{
				setState(143);
				match(T__0);
				setState(144);
				expr(0);
				setState(145);
				match(T__2);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(171);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(169);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(149);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(150);
						match(PLUS);
						setState(151);
						expr(8);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(152);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(153);
						match(MINUS);
						setState(154);
						expr(7);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(155);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(156);
						match(MULTIPLE);
						setState(157);
						expr(6);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(158);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(159);
						match(DIVIDE);
						setState(160);
						expr(5);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(161);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(162);
						logic_operator();
						setState(163);
						expr(3);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(165);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(166);
						comparator_operator();
						setState(167);
						expr(2);
						}
						break;
					}
					} 
				}
				setState(173);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Print_lContext extends ParserRuleContext {
		public TerminalNode PRINTSTMT() { return getToken(EzuinoParser.PRINTSTMT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Print_lContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print_l; }
	}

	public final Print_lContext print_l() throws RecognitionException {
		Print_lContext _localctx = new Print_lContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_print_l);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(PRINTSTMT);
			setState(175);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Comparator_operatorContext extends ParserRuleContext {
		public TerminalNode EQUAL() { return getToken(EzuinoParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(EzuinoParser.NOTEQUAL, 0); }
		public TerminalNode LESS() { return getToken(EzuinoParser.LESS, 0); }
		public TerminalNode LESSTHANOREQUAL() { return getToken(EzuinoParser.LESSTHANOREQUAL, 0); }
		public TerminalNode GREATER() { return getToken(EzuinoParser.GREATER, 0); }
		public TerminalNode GREATERTHANOREQUAL() { return getToken(EzuinoParser.GREATERTHANOREQUAL, 0); }
		public Comparator_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparator_operator; }
	}

	public final Comparator_operatorContext comparator_operator() throws RecognitionException {
		Comparator_operatorContext _localctx = new Comparator_operatorContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_comparator_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS) | (1L << GREATER) | (1L << EQUAL) | (1L << NOTEQUAL) | (1L << LESSTHANOREQUAL) | (1L << GREATERTHANOREQUAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Logic_operatorContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(EzuinoParser.AND, 0); }
		public TerminalNode OR() { return getToken(EzuinoParser.OR, 0); }
		public Logic_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logic_operator; }
	}

	public final Logic_operatorContext logic_operator() throws RecognitionException {
		Logic_operatorContext _localctx = new Logic_operatorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_logic_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			_la = _input.LA(1);
			if ( !(_la==AND || _la==OR) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public List<Boolean_exprContext> boolean_expr() {
			return getRuleContexts(Boolean_exprContext.class);
		}
		public Boolean_exprContext boolean_expr(int i) {
			return getRuleContext(Boolean_exprContext.class,i);
		}
		public List<Logic_operatorContext> logic_operator() {
			return getRuleContexts(Logic_operatorContext.class);
		}
		public Logic_operatorContext logic_operator(int i) {
			return getRuleContext(Logic_operatorContext.class,i);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			boolean_expr();
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND || _la==OR) {
				{
				{
				setState(182);
				logic_operator();
				setState(183);
				boolean_expr();
				}
				}
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Boolean_exprContext extends ParserRuleContext {
		public List<ValContext> val() {
			return getRuleContexts(ValContext.class);
		}
		public ValContext val(int i) {
			return getRuleContext(ValContext.class,i);
		}
		public Comparator_operatorContext comparator_operator() {
			return getRuleContext(Comparator_operatorContext.class,0);
		}
		public BooleantfContext booleantf() {
			return getRuleContext(BooleantfContext.class,0);
		}
		public Boolean_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean_expr; }
	}

	public final Boolean_exprContext boolean_expr() throws RecognitionException {
		Boolean_exprContext _localctx = new Boolean_exprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_boolean_expr);
		try {
			setState(195);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case INTEGER:
			case DOUBLE:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(190);
				val();
				setState(191);
				comparator_operator();
				setState(192);
				val();
				}
				break;
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				booleantf();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(EzuinoParser.ID, 0); }
		public TerminalNode INTEGER() { return getToken(EzuinoParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(EzuinoParser.DOUBLE, 0); }
		public TerminalNode STRING() { return getToken(EzuinoParser.STRING, 0); }
		public ValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_val; }
	}

	public final ValContext val() throws RecognitionException {
		ValContext _localctx = new ValContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_val);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << INTEGER) | (1L << DOUBLE) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INTDCL() { return getToken(EzuinoParser.INTDCL, 0); }
		public TerminalNode DOUBLEDCL() { return getToken(EzuinoParser.DOUBLEDCL, 0); }
		public TerminalNode BOOLDCL() { return getToken(EzuinoParser.BOOLDCL, 0); }
		public TerminalNode STRINGDCL() { return getToken(EzuinoParser.STRINGDCL, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTDCL) | (1L << DOUBLEDCL) | (1L << STRINGDCL) | (1L << BOOLDCL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleantfContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(EzuinoParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(EzuinoParser.FALSE, 0); }
		public BooleantfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleantf; }
	}

	public final BooleantfContext booleantf() throws RecognitionException {
		BooleantfContext _localctx = new BooleantfContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_booleantf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class List_idContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(EzuinoParser.ID, 0); }
		public List_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_id; }
	}

	public final List_idContext list_id() throws RecognitionException {
		List_idContext _localctx = new List_idContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_list_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class List_sizeContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(EzuinoParser.INTEGER, 0); }
		public List_sizeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_size; }
	}

	public final List_sizeContext list_size() throws RecognitionException {
		List_sizeContext _localctx = new List_sizeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_list_size);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(T__3);
			setState(206);
			match(INTEGER);
			setState(207);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Switch_stmtContext extends ParserRuleContext {
		public TerminalNode SWITCH() { return getToken(EzuinoParser.SWITCH, 0); }
		public ValContext val() {
			return getRuleContext(ValContext.class,0);
		}
		public Block_switchContext block_switch() {
			return getRuleContext(Block_switchContext.class,0);
		}
		public Switch_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switch_stmt; }
	}

	public final Switch_stmtContext switch_stmt() throws RecognitionException {
		Switch_stmtContext _localctx = new Switch_stmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_switch_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			match(SWITCH);
			setState(210);
			match(T__0);
			setState(211);
			val();
			setState(212);
			match(T__2);
			setState(213);
			block_switch();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Return_stmtContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(EzuinoParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Return_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_stmt; }
	}

	public final Return_stmtContext return_stmt() throws RecognitionException {
		Return_stmtContext _localctx = new Return_stmtContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_return_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(RETURN);
			setState(216);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_stmtContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(EzuinoParser.IF, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public If_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stmt; }
	}

	public final If_stmtContext if_stmt() throws RecognitionException {
		If_stmtContext _localctx = new If_stmtContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_if_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			match(IF);
			setState(219);
			match(T__0);
			setState(220);
			condition();
			setState(221);
			match(T__2);
			setState(222);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Else_stmtContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(EzuinoParser.ELSE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Else_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else_stmt; }
	}

	public final Else_stmtContext else_stmt() throws RecognitionException {
		Else_stmtContext _localctx = new Else_stmtContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_else_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(ELSE);
			setState(225);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_elseContext extends ParserRuleContext {
		public List<If_stmtContext> if_stmt() {
			return getRuleContexts(If_stmtContext.class);
		}
		public If_stmtContext if_stmt(int i) {
			return getRuleContext(If_stmtContext.class,i);
		}
		public Else_stmtContext else_stmt() {
			return getRuleContext(Else_stmtContext.class,0);
		}
		public If_elseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_else; }
	}

	public final If_elseContext if_else() throws RecognitionException {
		If_elseContext _localctx = new If_elseContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_if_else);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(228); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(227);
					if_stmt();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(230); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(232);
				else_stmt();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class While_stmtContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(EzuinoParser.WHILE, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public While_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_stmt; }
	}

	public final While_stmtContext while_stmt() throws RecognitionException {
		While_stmtContext _localctx = new While_stmtContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_while_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(WHILE);
			setState(236);
			match(T__0);
			setState(237);
			condition();
			setState(238);
			match(T__2);
			setState(239);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParametersContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			match(T__0);
			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTDCL) | (1L << DOUBLEDCL) | (1L << STRINGDCL) | (1L << BOOLDCL))) != 0)) {
				{
				setState(242);
				param();
				}
			}

			setState(249);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(245);
				match(T__1);
				setState(246);
				param();
				}
				}
				setState(251);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(252);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(EzuinoParser.ID, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			type();
			setState(255);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode SBRACE() { return getToken(EzuinoParser.SBRACE, 0); }
		public DclsContext dcls() {
			return getRuleContext(DclsContext.class,0);
		}
		public StmtsContext stmts() {
			return getRuleContext(StmtsContext.class,0);
		}
		public TerminalNode EBRACE() { return getToken(EzuinoParser.EBRACE, 0); }
		public Return_stmtContext return_stmt() {
			return getRuleContext(Return_stmtContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(SBRACE);
			setState(258);
			dcls();
			setState(259);
			stmts();
			setState(261);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURN) {
				{
				setState(260);
				return_stmt();
				}
			}

			setState(263);
			match(EBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Block_switchContext extends ParserRuleContext {
		public TerminalNode SBRACE() { return getToken(EzuinoParser.SBRACE, 0); }
		public TerminalNode EBRACE() { return getToken(EzuinoParser.EBRACE, 0); }
		public List<TerminalNode> CASE() { return getTokens(EzuinoParser.CASE); }
		public TerminalNode CASE(int i) {
			return getToken(EzuinoParser.CASE, i);
		}
		public List<ValContext> val() {
			return getRuleContexts(ValContext.class);
		}
		public ValContext val(int i) {
			return getRuleContext(ValContext.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode DEFAULT() { return getToken(EzuinoParser.DEFAULT, 0); }
		public Block_switchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block_switch; }
	}

	public final Block_switchContext block_switch() throws RecognitionException {
		Block_switchContext _localctx = new Block_switchContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_block_switch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			match(SBRACE);
			setState(280);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE) {
				{
				{
				setState(266);
				match(CASE);
				setState(267);
				val();
				setState(272);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(268);
					match(T__1);
					setState(269);
					val();
					}
					}
					setState(274);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(275);
				match(T__5);
				setState(276);
				block();
				}
				}
				setState(282);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(286);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(283);
				match(DEFAULT);
				setState(284);
				match(T__5);
				setState(285);
				block();
				}
			}

			setState(288);
			match(EBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListContext extends ParserRuleContext {
		public TerminalNode LISTDCL() { return getToken(EzuinoParser.LISTDCL, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List_idContext list_id() {
			return getRuleContext(List_idContext.class,0);
		}
		public List_sizeContext list_size() {
			return getRuleContext(List_sizeContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(EzuinoParser.ASSIGN, 0); }
		public List<ValContext> val() {
			return getRuleContexts(ValContext.class);
		}
		public ValContext val(int i) {
			return getRuleContext(ValContext.class,i);
		}
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			match(LISTDCL);
			setState(291);
			type();
			setState(292);
			list_id();
			setState(293);
			list_size();
			setState(294);
			match(ASSIGN);
			setState(295);
			match(T__0);
			setState(302);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << INTEGER) | (1L << DOUBLE) | (1L << STRING))) != 0)) {
				{
				{
				setState(296);
				val();
				setState(298);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(297);
					match(T__1);
					}
				}

				}
				}
				setState(304);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(305);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class List_addContext extends ParserRuleContext {
		public TerminalNode LISTADD() { return getToken(EzuinoParser.LISTADD, 0); }
		public TerminalNode ID() { return getToken(EzuinoParser.ID, 0); }
		public ValContext val() {
			return getRuleContext(ValContext.class,0);
		}
		public TerminalNode INTEGER() { return getToken(EzuinoParser.INTEGER, 0); }
		public List_addContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_add; }
	}

	public final List_addContext list_add() throws RecognitionException {
		List_addContext _localctx = new List_addContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_list_add);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			match(LISTADD);
			setState(308);
			match(T__0);
			setState(309);
			match(ID);
			setState(310);
			match(T__1);
			setState(311);
			val();
			setState(312);
			match(T__1);
			setState(313);
			match(INTEGER);
			setState(314);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class List_removeContext extends ParserRuleContext {
		public TerminalNode LISTREMOVE() { return getToken(EzuinoParser.LISTREMOVE, 0); }
		public TerminalNode ID() { return getToken(EzuinoParser.ID, 0); }
		public ValContext val() {
			return getRuleContext(ValContext.class,0);
		}
		public TerminalNode INTEGER() { return getToken(EzuinoParser.INTEGER, 0); }
		public List_removeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_remove; }
	}

	public final List_removeContext list_remove() throws RecognitionException {
		List_removeContext _localctx = new List_removeContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_list_remove);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			match(LISTREMOVE);
			setState(317);
			match(T__0);
			setState(318);
			match(ID);
			setState(319);
			match(T__1);
			setState(320);
			val();
			setState(321);
			match(T__1);
			setState(322);
			match(INTEGER);
			setState(323);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 9:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 2);
		case 5:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\60\u0148\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\2\3\3\7\3I\n\3\f\3\16\3L\13\3\3\4\3\4\3\4\3\4\5"+
		"\4R\n\4\3\5\7\5U\n\5\f\5\16\5X\13\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6`\n\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\5\7h\n\7\3\7\3\7\3\7\3\7\5\7n\n\7\3\b\3\b\5\br"+
		"\n\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\5\t{\n\t\3\t\3\t\3\t\6\t\u0080\n\t\r"+
		"\t\16\t\u0081\5\t\u0084\n\t\3\t\3\t\5\t\u0088\n\t\3\n\3\n\3\n\5\n\u008d"+
		"\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u0096\n\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\7\13\u00ac\n\13\f\13\16\13\u00af\13\13\3\f\3\f\3\f\3\r"+
		"\3\r\3\16\3\16\3\17\3\17\3\17\3\17\7\17\u00bc\n\17\f\17\16\17\u00bf\13"+
		"\17\3\20\3\20\3\20\3\20\3\20\5\20\u00c6\n\20\3\21\3\21\3\22\3\22\3\23"+
		"\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\32\6\32\u00e7"+
		"\n\32\r\32\16\32\u00e8\3\32\5\32\u00ec\n\32\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\34\3\34\5\34\u00f6\n\34\3\34\3\34\7\34\u00fa\n\34\f\34\16\34\u00fd"+
		"\13\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\36\5\36\u0108\n\36\3"+
		"\36\3\36\3\37\3\37\3\37\3\37\3\37\7\37\u0111\n\37\f\37\16\37\u0114\13"+
		"\37\3\37\3\37\3\37\7\37\u0119\n\37\f\37\16\37\u011c\13\37\3\37\3\37\3"+
		"\37\5\37\u0121\n\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \5 \u012d\n \7 \u012f"+
		"\n \f \16 \u0132\13 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\""+
		"\3\"\3\"\3\"\3\"\3\"\3\"\2\3\24#\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668:<>@B\2\7\4\2\30\33\35\36\3\2\26\27\4\2)),.\3\2"+
		"\t\f\3\2\"#\2\u014c\2D\3\2\2\2\4J\3\2\2\2\6Q\3\2\2\2\bV\3\2\2\2\n_\3\2"+
		"\2\2\fm\3\2\2\2\16o\3\2\2\2\20\u0087\3\2\2\2\22\u008c\3\2\2\2\24\u0095"+
		"\3\2\2\2\26\u00b0\3\2\2\2\30\u00b3\3\2\2\2\32\u00b5\3\2\2\2\34\u00b7\3"+
		"\2\2\2\36\u00c5\3\2\2\2 \u00c7\3\2\2\2\"\u00c9\3\2\2\2$\u00cb\3\2\2\2"+
		"&\u00cd\3\2\2\2(\u00cf\3\2\2\2*\u00d3\3\2\2\2,\u00d9\3\2\2\2.\u00dc\3"+
		"\2\2\2\60\u00e2\3\2\2\2\62\u00e6\3\2\2\2\64\u00ed\3\2\2\2\66\u00f3\3\2"+
		"\2\28\u0100\3\2\2\2:\u0103\3\2\2\2<\u010b\3\2\2\2>\u0124\3\2\2\2@\u0135"+
		"\3\2\2\2B\u013e\3\2\2\2DE\5\4\3\2EF\5\b\5\2F\3\3\2\2\2GI\5\6\4\2HG\3\2"+
		"\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\5\3\2\2\2LJ\3\2\2\2MN\5\"\22\2NO\7"+
		")\2\2OR\3\2\2\2PR\5> \2QM\3\2\2\2QP\3\2\2\2R\7\3\2\2\2SU\5\n\6\2TS\3\2"+
		"\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2W\t\3\2\2\2XV\3\2\2\2Y`\5\f\7\2Z`\5"+
		"\64\33\2[`\5\20\t\2\\`\5\16\b\2]`\5\62\32\2^`\5*\26\2_Y\3\2\2\2_Z\3\2"+
		"\2\2_[\3\2\2\2_\\\3\2\2\2_]\3\2\2\2_^\3\2\2\2`\13\3\2\2\2ab\7)\2\2bc\7"+
		"\17\2\2cn\5\24\13\2de\7)\2\2eg\7\17\2\2fh\7\34\2\2gf\3\2\2\2gh\3\2\2\2"+
		"hi\3\2\2\2in\5$\23\2jk\7)\2\2kl\7\17\2\2ln\5\34\17\2ma\3\2\2\2md\3\2\2"+
		"\2mj\3\2\2\2n\r\3\2\2\2oq\7\'\2\2pr\5\"\22\2qp\3\2\2\2qr\3\2\2\2rs\3\2"+
		"\2\2st\7)\2\2tu\5\66\34\2uv\5:\36\2v\17\3\2\2\2wx\7)\2\2x\u0083\7\3\2"+
		"\2y{\5\24\13\2zy\3\2\2\2z{\3\2\2\2{\u0084\3\2\2\2|\177\5\24\13\2}~\7\4"+
		"\2\2~\u0080\5\24\13\2\177}\3\2\2\2\u0080\u0081\3\2\2\2\u0081\177\3\2\2"+
		"\2\u0081\u0082\3\2\2\2\u0082\u0084\3\2\2\2\u0083z\3\2\2\2\u0083|\3\2\2"+
		"\2\u0084\u0085\3\2\2\2\u0085\u0088\7\5\2\2\u0086\u0088\5\22\n\2\u0087"+
		"w\3\2\2\2\u0087\u0086\3\2\2\2\u0088\21\3\2\2\2\u0089\u008d\5\26\f\2\u008a"+
		"\u008d\5@!\2\u008b\u008d\5B\"\2\u008c\u0089\3\2\2\2\u008c\u008a\3\2\2"+
		"\2\u008c\u008b\3\2\2\2\u008d\23\3\2\2\2\u008e\u008f\b\13\1\2\u008f\u0096"+
		"\5 \21\2\u0090\u0096\5\20\t\2\u0091\u0092\7\3\2\2\u0092\u0093\5\24\13"+
		"\2\u0093\u0094\7\5\2\2\u0094\u0096\3\2\2\2\u0095\u008e\3\2\2\2\u0095\u0090"+
		"\3\2\2\2\u0095\u0091\3\2\2\2\u0096\u00ad\3\2\2\2\u0097\u0098\f\t\2\2\u0098"+
		"\u0099\7\22\2\2\u0099\u00ac\5\24\13\n\u009a\u009b\f\b\2\2\u009b\u009c"+
		"\7\23\2\2\u009c\u00ac\5\24\13\t\u009d\u009e\f\7\2\2\u009e\u009f\7\25\2"+
		"\2\u009f\u00ac\5\24\13\b\u00a0\u00a1\f\6\2\2\u00a1\u00a2\7\24\2\2\u00a2"+
		"\u00ac\5\24\13\7\u00a3\u00a4\f\4\2\2\u00a4\u00a5\5\32\16\2\u00a5\u00a6"+
		"\5\24\13\5\u00a6\u00ac\3\2\2\2\u00a7\u00a8\f\3\2\2\u00a8\u00a9\5\30\r"+
		"\2\u00a9\u00aa\5\24\13\4\u00aa\u00ac\3\2\2\2\u00ab\u0097\3\2\2\2\u00ab"+
		"\u009a\3\2\2\2\u00ab\u009d\3\2\2\2\u00ab\u00a0\3\2\2\2\u00ab\u00a3\3\2"+
		"\2\2\u00ab\u00a7\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad"+
		"\u00ae\3\2\2\2\u00ae\25\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b1\7\16\2"+
		"\2\u00b1\u00b2\5\24\13\2\u00b2\27\3\2\2\2\u00b3\u00b4\t\2\2\2\u00b4\31"+
		"\3\2\2\2\u00b5\u00b6\t\3\2\2\u00b6\33\3\2\2\2\u00b7\u00bd\5\36\20\2\u00b8"+
		"\u00b9\5\32\16\2\u00b9\u00ba\5\36\20\2\u00ba\u00bc\3\2\2\2\u00bb\u00b8"+
		"\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be"+
		"\35\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0\u00c1\5 \21\2\u00c1\u00c2\5\30\r"+
		"\2\u00c2\u00c3\5 \21\2\u00c3\u00c6\3\2\2\2\u00c4\u00c6\5$\23\2\u00c5\u00c0"+
		"\3\2\2\2\u00c5\u00c4\3\2\2\2\u00c6\37\3\2\2\2\u00c7\u00c8\t\4\2\2\u00c8"+
		"!\3\2\2\2\u00c9\u00ca\t\5\2\2\u00ca#\3\2\2\2\u00cb\u00cc\t\6\2\2\u00cc"+
		"%\3\2\2\2\u00cd\u00ce\7)\2\2\u00ce\'\3\2\2\2\u00cf\u00d0\7\6\2\2\u00d0"+
		"\u00d1\7,\2\2\u00d1\u00d2\7\7\2\2\u00d2)\3\2\2\2\u00d3\u00d4\7$\2\2\u00d4"+
		"\u00d5\7\3\2\2\u00d5\u00d6\5 \21\2\u00d6\u00d7\7\5\2\2\u00d7\u00d8\5<"+
		"\37\2\u00d8+\3\2\2\2\u00d9\u00da\7&\2\2\u00da\u00db\5\24\13\2\u00db-\3"+
		"\2\2\2\u00dc\u00dd\7 \2\2\u00dd\u00de\7\3\2\2\u00de\u00df\5\34\17\2\u00df"+
		"\u00e0\7\5\2\2\u00e0\u00e1\5:\36\2\u00e1/\3\2\2\2\u00e2\u00e3\7\37\2\2"+
		"\u00e3\u00e4\5:\36\2\u00e4\61\3\2\2\2\u00e5\u00e7\5.\30\2\u00e6\u00e5"+
		"\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9"+
		"\u00eb\3\2\2\2\u00ea\u00ec\5\60\31\2\u00eb\u00ea\3\2\2\2\u00eb\u00ec\3"+
		"\2\2\2\u00ec\63\3\2\2\2\u00ed\u00ee\7!\2\2\u00ee\u00ef\7\3\2\2\u00ef\u00f0"+
		"\5\34\17\2\u00f0\u00f1\7\5\2\2\u00f1\u00f2\5:\36\2\u00f2\65\3\2\2\2\u00f3"+
		"\u00f5\7\3\2\2\u00f4\u00f6\58\35\2\u00f5\u00f4\3\2\2\2\u00f5\u00f6\3\2"+
		"\2\2\u00f6\u00fb\3\2\2\2\u00f7\u00f8\7\4\2\2\u00f8\u00fa\58\35\2\u00f9"+
		"\u00f7\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2"+
		"\2\2\u00fc\u00fe\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u00ff\7\5\2\2\u00ff"+
		"\67\3\2\2\2\u0100\u0101\5\"\22\2\u0101\u0102\7)\2\2\u01029\3\2\2\2\u0103"+
		"\u0104\7*\2\2\u0104\u0105\5\4\3\2\u0105\u0107\5\b\5\2\u0106\u0108\5,\27"+
		"\2\u0107\u0106\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010a"+
		"\7+\2\2\u010a;\3\2\2\2\u010b\u011a\7*\2\2\u010c\u010d\7%\2\2\u010d\u0112"+
		"\5 \21\2\u010e\u010f\7\4\2\2\u010f\u0111\5 \21\2\u0110\u010e\3\2\2\2\u0111"+
		"\u0114\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0115\3\2"+
		"\2\2\u0114\u0112\3\2\2\2\u0115\u0116\7\b\2\2\u0116\u0117\5:\36\2\u0117"+
		"\u0119\3\2\2\2\u0118\u010c\3\2\2\2\u0119\u011c\3\2\2\2\u011a\u0118\3\2"+
		"\2\2\u011a\u011b\3\2\2\2\u011b\u0120\3\2\2\2\u011c\u011a\3\2\2\2\u011d"+
		"\u011e\7(\2\2\u011e\u011f\7\b\2\2\u011f\u0121\5:\36\2\u0120\u011d\3\2"+
		"\2\2\u0120\u0121\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0123\7+\2\2\u0123"+
		"=\3\2\2\2\u0124\u0125\7\r\2\2\u0125\u0126\5\"\22\2\u0126\u0127\5&\24\2"+
		"\u0127\u0128\5(\25\2\u0128\u0129\7\17\2\2\u0129\u0130\7\3\2\2\u012a\u012c"+
		"\5 \21\2\u012b\u012d\7\4\2\2\u012c\u012b\3\2\2\2\u012c\u012d\3\2\2\2\u012d"+
		"\u012f\3\2\2\2\u012e\u012a\3\2\2\2\u012f\u0132\3\2\2\2\u0130\u012e\3\2"+
		"\2\2\u0130\u0131\3\2\2\2\u0131\u0133\3\2\2\2\u0132\u0130\3\2\2\2\u0133"+
		"\u0134\7\5\2\2\u0134?\3\2\2\2\u0135\u0136\7\20\2\2\u0136\u0137\7\3\2\2"+
		"\u0137\u0138\7)\2\2\u0138\u0139\7\4\2\2\u0139\u013a\5 \21\2\u013a\u013b"+
		"\7\4\2\2\u013b\u013c\7,\2\2\u013c\u013d\7\5\2\2\u013dA\3\2\2\2\u013e\u013f"+
		"\7\21\2\2\u013f\u0140\7\3\2\2\u0140\u0141\7)\2\2\u0141\u0142\7\4\2\2\u0142"+
		"\u0143\5 \21\2\u0143\u0144\7\4\2\2\u0144\u0145\7,\2\2\u0145\u0146\7\5"+
		"\2\2\u0146C\3\2\2\2\35JQV_gmqz\u0081\u0083\u0087\u008c\u0095\u00ab\u00ad"+
		"\u00bd\u00c5\u00e8\u00eb\u00f5\u00fb\u0107\u0112\u011a\u0120\u012c\u0130";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}