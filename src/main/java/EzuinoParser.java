// Generated from ezuino.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EzuinoParser extends Parser {
    static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

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
            RULE_func = 5, RULE_func_call = 6, RULE_expr = 7, RULE_print_l = 8, RULE_comparator_operator = 9,
            RULE_logic_operator = 10, RULE_condition = 11, RULE_boolean_expr = 12,
            RULE_val = 13, RULE_type = 14, RULE_booleantf = 15, RULE_list_id = 16,
            RULE_list_size = 17, RULE_switch_stmt = 18, RULE_return_stmt = 19, RULE_if_stmt = 20,
            RULE_else_stmt = 21, RULE_if_else = 22, RULE_while_stmt = 23, RULE_parameters = 24,
            RULE_param = 25, RULE_block = 26, RULE_block_switch = 27, RULE_list = 28,
            RULE_list_add = 29, RULE_list_remove = 30;
    private static String[] makeRuleNames() {
        return new String[] {
                "start", "dcls", "dcl", "stmts", "stmt", "func", "func_call", "expr",
                "print_l", "comparator_operator", "logic_operator", "condition", "boolean_expr",
                "val", "type", "booleantf", "list_id", "list_size", "switch_stmt", "return_stmt",
                "if_stmt", "else_stmt", "if_else", "while_stmt", "parameters", "param",
                "block", "block_switch", "list", "list_add", "list_remove"
        };
    }
    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[] {
                null, "'['", "']'", "'('", "','", "')'", "':'", "'int'", "'double'",
                "'string'", "'boolean'", "'list'", "'print'", "':='", "'list_add'", "'list_remove'",
                "'+'", "'-'", "'/'", "'*'", "'AND'", "'OR'", "'<'", "'>'", "'='", "'!='",
                "'!'", "'<='", "'>='", "'else'", "'if'", "'while'", "'TRUE'", "'FALSE'",
                "'switch'", "'case'", "'return'", "'func'", "'default'", null, "'__'" ,
                "'__'"};
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();
    private static String[] makeSymbolicNames() {
        return new String[] {
                null, null, null, null, null, null, null, "INTDCL", "DOUBLEDCL", "STRINGDCL",
                "BOOLDCL", "LISTDCL", "PRINTSTMT", "ASSIGN", "LISTADD", "LISTREMOVE",
                "PLUS", "MINUS", "DIVIDE", "MULTIPLE", "AND", "OR", "LESS", "GREATER",
                "EQUAL", "NOTEQUAL", "NOT", "LESSTHANOREQUAL", "GREATERTHANOREQUAL",
                "ELSE", "IF", "WHILE", "TRUE", "FALSE", "SWITCH", "CASE", "RETURN", "FUNCTION",
                "DEFAULT", "ID", "SBRACE", "EBRACE", "INTEGER", "DOUBLE", "STRING", "BLANK",
                "COMMENT"
        };
    }

    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
    public String getGrammarFileName() { return "ezuino.g4"; }

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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterStart(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitStart(this);
        }
    }

    public final StartContext start() throws RecognitionException {
        StartContext _localctx = new StartContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_start);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(62);
                dcls();
                setState(63);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterDcls(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitDcls(this);
        }
    }

    public final DclsContext dcls() throws RecognitionException {
        DclsContext _localctx = new DclsContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_dcls);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(68);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTDCL) | (1L << DOUBLEDCL) | (1L << STRINGDCL) | (1L << BOOLDCL) | (1L << LISTDCL))) != 0)) {
                    {
                        {
                            setState(65);
                            dcl();
                        }
                    }
                    setState(70);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterDcl(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitDcl(this);
        }
    }

    public final DclContext dcl() throws RecognitionException {
        DclContext _localctx = new DclContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_dcl);
        try {
            setState(75);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case INTDCL:
                case DOUBLEDCL:
                case STRINGDCL:
                case BOOLDCL:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(71);
                    type();
                    setState(72);
                    match(ID);
                }
                break;
                case LISTDCL:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(74);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterStmts(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitStmts(this);
        }
    }

    public final StmtsContext stmts() throws RecognitionException {
        StmtsContext _localctx = new StmtsContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_stmts);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(80);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PRINTSTMT) | (1L << LISTADD) | (1L << LISTREMOVE) | (1L << IF) | (1L << WHILE) | (1L << SWITCH) | (1L << FUNCTION) | (1L << ID))) != 0)) {
                    {
                        {
                            setState(77);
                            stmt();
                        }
                    }
                    setState(82);
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
        public TerminalNode ID() { return getToken(EzuinoParser.ID, 0); }
        public TerminalNode ASSIGN() { return getToken(EzuinoParser.ASSIGN, 0); }
        public ExprContext expr() {
            return getRuleContext(ExprContext.class,0);
        }
        public Func_callContext func_call() {
            return getRuleContext(Func_callContext.class,0);
        }
        public BooleantfContext booleantf() {
            return getRuleContext(BooleantfContext.class,0);
        }
        public TerminalNode NOT() { return getToken(EzuinoParser.NOT, 0); }
        public Print_lContext print_l() {
            return getRuleContext(Print_lContext.class,0);
        }
        public TerminalNode LISTREMOVE() { return getToken(EzuinoParser.LISTREMOVE, 0); }
        public TerminalNode LISTADD() { return getToken(EzuinoParser.LISTADD, 0); }
        public If_elseContext if_else() {
            return getRuleContext(If_elseContext.class,0);
        }
        public While_stmtContext while_stmt() {
            return getRuleContext(While_stmtContext.class,0);
        }
        public FuncContext func() {
            return getRuleContext(FuncContext.class,0);
        }
        public Switch_stmtContext switch_stmt() {
            return getRuleContext(Switch_stmtContext.class,0);
        }
        public List_addContext list_add() {
            return getRuleContext(List_addContext.class,0);
        }
        public List_removeContext list_remove() {
            return getRuleContext(List_removeContext.class,0);
        }
        public StmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }
        @Override public int getRuleIndex() { return RULE_stmt; }
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterStmt(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitStmt(this);
        }
    }

    public final StmtContext stmt() throws RecognitionException {
        StmtContext _localctx = new StmtContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_stmt);
        int _la;
        try {
            setState(104);
            _errHandler.sync(this);
            switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(83);
                    match(ID);
                    setState(84);
                    match(ASSIGN);
                    setState(85);
                    expr(0);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(86);
                    func_call();
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(87);
                    match(ID);
                    setState(88);
                    match(ASSIGN);
                    setState(90);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la==NOT) {
                        {
                            setState(89);
                            match(NOT);
                        }
                    }

                    setState(92);
                    booleantf();
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(93);
                    print_l();
                }
                break;
                case 5:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(94);
                    match(LISTREMOVE);
                    setState(95);
                    match(T__0);
                    setState(96);
                    match(T__1);
                }
                break;
                case 6:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(97);
                    match(LISTADD);
                }
                break;
                case 7:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(98);
                    if_else();
                }
                break;
                case 8:
                    enterOuterAlt(_localctx, 8);
                {
                    setState(99);
                    while_stmt();
                }
                break;
                case 9:
                    enterOuterAlt(_localctx, 9);
                {
                    setState(100);
                    func();
                }
                break;
                case 10:
                    enterOuterAlt(_localctx, 10);
                {
                    setState(101);
                    switch_stmt();
                }
                break;
                case 11:
                    enterOuterAlt(_localctx, 11);
                {
                    setState(102);
                    list_add();
                }
                break;
                case 12:
                    enterOuterAlt(_localctx, 12);
                {
                    setState(103);
                    list_remove();
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

    public static class FuncContext extends ParserRuleContext {
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
        public FuncContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }
        @Override public int getRuleIndex() { return RULE_func; }
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterFunc(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitFunc(this);
        }
    }

    public final FuncContext func() throws RecognitionException {
        FuncContext _localctx = new FuncContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_func);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(106);
                match(FUNCTION);
                setState(108);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTDCL) | (1L << DOUBLEDCL) | (1L << STRINGDCL) | (1L << BOOLDCL))) != 0)) {
                    {
                        setState(107);
                        type();
                    }
                }

                setState(110);
                match(ID);
                setState(111);
                parameters();
                setState(112);
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
        public Func_callContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }
        @Override public int getRuleIndex() { return RULE_func_call; }
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterFunc_call(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitFunc_call(this);
        }
    }

    public final Func_callContext func_call() throws RecognitionException {
        Func_callContext _localctx = new Func_callContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_func_call);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(114);
                match(ID);
                setState(115);
                match(T__2);
                setState(126);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
                    case 1:
                    {
                        setState(117);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << ID) | (1L << INTEGER) | (1L << DOUBLE) | (1L << STRING))) != 0)) {
                            {
                                setState(116);
                                expr(0);
                            }
                        }

                    }
                    break;
                    case 2:
                    {
                        setState(119);
                        expr(0);
                        setState(122);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        do {
                            {
                                {
                                    setState(120);
                                    match(T__3);
                                    setState(121);
                                    expr(0);
                                }
                            }
                            setState(124);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                        } while ( _la==T__3 );
                    }
                    break;
                }
                setState(128);
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
        public ExprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }
        @Override public int getRuleIndex() { return RULE_expr; }
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterExpr(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitExpr(this);
        }
    }

    public final ExprContext expr() throws RecognitionException {
        return expr(0);
    }

    private ExprContext expr(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        ExprContext _localctx = new ExprContext(_ctx, _parentState);
        ExprContext _prevctx = _localctx;
        int _startState = 14;
        enterRecursionRule(_localctx, 14, RULE_expr, _p);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(137);
                _errHandler.sync(this);
                switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
                    case 1:
                    {
                        setState(131);
                        val();
                    }
                    break;
                    case 2:
                    {
                        setState(132);
                        func_call();
                    }
                    break;
                    case 3:
                    {
                        setState(133);
                        match(T__2);
                        setState(134);
                        expr(0);
                        setState(135);
                        match(T__4);
                    }
                    break;
                }
                _ctx.stop = _input.LT(-1);
                setState(153);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input,11,_ctx);
                while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
                    if ( _alt==1 ) {
                        if ( _parseListeners!=null ) triggerExitRuleEvent();
                        _prevctx = _localctx;
                        {
                            setState(151);
                            _errHandler.sync(this);
                            switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
                                case 1:
                                {
                                    _localctx = new ExprContext(_parentctx, _parentState);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(139);
                                    if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
                                    setState(140);
                                    match(PLUS);
                                    setState(141);
                                    expr(6);
                                }
                                break;
                                case 2:
                                {
                                    _localctx = new ExprContext(_parentctx, _parentState);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(142);
                                    if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
                                    setState(143);
                                    match(MINUS);
                                    setState(144);
                                    expr(5);
                                }
                                break;
                                case 3:
                                {
                                    _localctx = new ExprContext(_parentctx, _parentState);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(145);
                                    if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
                                    setState(146);
                                    match(MULTIPLE);
                                    setState(147);
                                    expr(4);
                                }
                                break;
                                case 4:
                                {
                                    _localctx = new ExprContext(_parentctx, _parentState);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(148);
                                    if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
                                    setState(149);
                                    match(DIVIDE);
                                    setState(150);
                                    expr(3);
                                }
                                break;
                            }
                        }
                    }
                    setState(155);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterPrint_l(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitPrint_l(this);
        }
    }

    public final Print_lContext print_l() throws RecognitionException {
        Print_lContext _localctx = new Print_lContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_print_l);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(156);
                match(PRINTSTMT);
                setState(157);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterComparator_operator(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitComparator_operator(this);
        }
    }

    public final Comparator_operatorContext comparator_operator() throws RecognitionException {
        Comparator_operatorContext _localctx = new Comparator_operatorContext(_ctx, getState());
        enterRule(_localctx, 18, RULE_comparator_operator);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(159);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterLogic_operator(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitLogic_operator(this);
        }
    }

    public final Logic_operatorContext logic_operator() throws RecognitionException {
        Logic_operatorContext _localctx = new Logic_operatorContext(_ctx, getState());
        enterRule(_localctx, 20, RULE_logic_operator);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(161);
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
        public BooleantfContext booleantf() {
            return getRuleContext(BooleantfContext.class,0);
        }
        public ConditionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }
        @Override public int getRuleIndex() { return RULE_condition; }
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterCondition(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitCondition(this);
        }
    }

    public final ConditionContext condition() throws RecognitionException {
        ConditionContext _localctx = new ConditionContext(_ctx, getState());
        enterRule(_localctx, 22, RULE_condition);
        int _la;
        try {
            setState(173);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case ID:
                case INTEGER:
                case DOUBLE:
                case STRING:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(163);
                    boolean_expr();
                    setState(169);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la==AND || _la==OR) {
                        {
                            {
                                setState(164);
                                logic_operator();
                                setState(165);
                                boolean_expr();
                            }
                        }
                        setState(171);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                }
                break;
                case TRUE:
                case FALSE:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(172);
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
        public Boolean_exprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }
        @Override public int getRuleIndex() { return RULE_boolean_expr; }
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterBoolean_expr(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitBoolean_expr(this);
        }
    }

    public final Boolean_exprContext boolean_expr() throws RecognitionException {
        Boolean_exprContext _localctx = new Boolean_exprContext(_ctx, getState());
        enterRule(_localctx, 24, RULE_boolean_expr);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(175);
                val();
                setState(176);
                comparator_operator();
                setState(177);
                val();
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterVal(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitVal(this);
        }
    }

    public final ValContext val() throws RecognitionException {
        ValContext _localctx = new ValContext(_ctx, getState());
        enterRule(_localctx, 26, RULE_val);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(179);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterType(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitType(this);
        }
    }

    public final TypeContext type() throws RecognitionException {
        TypeContext _localctx = new TypeContext(_ctx, getState());
        enterRule(_localctx, 28, RULE_type);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(181);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterBooleantf(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitBooleantf(this);
        }
    }

    public final BooleantfContext booleantf() throws RecognitionException {
        BooleantfContext _localctx = new BooleantfContext(_ctx, getState());
        enterRule(_localctx, 30, RULE_booleantf);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(183);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterList_id(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitList_id(this);
        }
    }

    public final List_idContext list_id() throws RecognitionException {
        List_idContext _localctx = new List_idContext(_ctx, getState());
        enterRule(_localctx, 32, RULE_list_id);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(185);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterList_size(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitList_size(this);
        }
    }

    public final List_sizeContext list_size() throws RecognitionException {
        List_sizeContext _localctx = new List_sizeContext(_ctx, getState());
        enterRule(_localctx, 34, RULE_list_size);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(187);
                match(T__0);
                setState(188);
                match(INTEGER);
                setState(189);
                match(T__1);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterSwitch_stmt(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitSwitch_stmt(this);
        }
    }

    public final Switch_stmtContext switch_stmt() throws RecognitionException {
        Switch_stmtContext _localctx = new Switch_stmtContext(_ctx, getState());
        enterRule(_localctx, 36, RULE_switch_stmt);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(191);
                match(SWITCH);
                setState(192);
                match(T__2);
                setState(193);
                val();
                setState(194);
                match(T__4);
                setState(195);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterReturn_stmt(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitReturn_stmt(this);
        }
    }

    public final Return_stmtContext return_stmt() throws RecognitionException {
        Return_stmtContext _localctx = new Return_stmtContext(_ctx, getState());
        enterRule(_localctx, 38, RULE_return_stmt);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(197);
                match(RETURN);
                setState(198);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterIf_stmt(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitIf_stmt(this);
        }
    }

    public final If_stmtContext if_stmt() throws RecognitionException {
        If_stmtContext _localctx = new If_stmtContext(_ctx, getState());
        enterRule(_localctx, 40, RULE_if_stmt);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(200);
                match(IF);
                setState(201);
                match(T__2);
                setState(202);
                condition();
                setState(203);
                match(T__4);
                setState(204);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterElse_stmt(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitElse_stmt(this);
        }
    }

    public final Else_stmtContext else_stmt() throws RecognitionException {
        Else_stmtContext _localctx = new Else_stmtContext(_ctx, getState());
        enterRule(_localctx, 42, RULE_else_stmt);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(206);
                match(ELSE);
                setState(207);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterIf_else(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitIf_else(this);
        }
    }

    public final If_elseContext if_else() throws RecognitionException {
        If_elseContext _localctx = new If_elseContext(_ctx, getState());
        enterRule(_localctx, 44, RULE_if_else);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(210);
                _errHandler.sync(this);
                _alt = 1;
                do {
                    switch (_alt) {
                        case 1:
                        {
                            {
                                setState(209);
                                if_stmt();
                            }
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                    setState(212);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input,14,_ctx);
                } while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
                setState(215);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==ELSE) {
                    {
                        setState(214);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterWhile_stmt(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitWhile_stmt(this);
        }
    }

    public final While_stmtContext while_stmt() throws RecognitionException {
        While_stmtContext _localctx = new While_stmtContext(_ctx, getState());
        enterRule(_localctx, 46, RULE_while_stmt);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(217);
                match(WHILE);
                setState(218);
                match(T__2);
                setState(219);
                condition();
                setState(220);
                match(T__4);
                setState(221);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterParameters(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitParameters(this);
        }
    }

    public final ParametersContext parameters() throws RecognitionException {
        ParametersContext _localctx = new ParametersContext(_ctx, getState());
        enterRule(_localctx, 48, RULE_parameters);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(223);
                match(T__2);
                setState(225);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTDCL) | (1L << DOUBLEDCL) | (1L << STRINGDCL) | (1L << BOOLDCL))) != 0)) {
                    {
                        setState(224);
                        param();
                    }
                }

                setState(231);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la==T__3) {
                    {
                        {
                            setState(227);
                            match(T__3);
                            setState(228);
                            param();
                        }
                    }
                    setState(233);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(234);
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

    public static class ParamContext extends ParserRuleContext {
        public TypeContext type() {
            return getRuleContext(TypeContext.class,0);
        }
        public TerminalNode ID() { return getToken(EzuinoParser.ID, 0); }
        public ParamContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }
        @Override public int getRuleIndex() { return RULE_param; }
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterParam(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitParam(this);
        }
    }

    public final ParamContext param() throws RecognitionException {
        ParamContext _localctx = new ParamContext(_ctx, getState());
        enterRule(_localctx, 50, RULE_param);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(236);
                type();
                setState(237);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterBlock(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitBlock(this);
        }
    }

    public final BlockContext block() throws RecognitionException {
        BlockContext _localctx = new BlockContext(_ctx, getState());
        enterRule(_localctx, 52, RULE_block);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(239);
                match(SBRACE);
                setState(240);
                dcls();
                setState(241);
                stmts();
                setState(243);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==RETURN) {
                    {
                        setState(242);
                        return_stmt();
                    }
                }

                setState(245);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterBlock_switch(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitBlock_switch(this);
        }
    }

    public final Block_switchContext block_switch() throws RecognitionException {
        Block_switchContext _localctx = new Block_switchContext(_ctx, getState());
        enterRule(_localctx, 54, RULE_block_switch);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(247);
                match(SBRACE);
                setState(262);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la==CASE) {
                    {
                        {
                            setState(248);
                            match(CASE);
                            setState(249);
                            val();
                            setState(254);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            while (_la==T__3) {
                                {
                                    {
                                        setState(250);
                                        match(T__3);
                                        setState(251);
                                        val();
                                    }
                                }
                                setState(256);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                            }
                            setState(257);
                            match(T__5);
                            setState(258);
                            block();
                        }
                    }
                    setState(264);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(268);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la==DEFAULT) {
                    {
                        setState(265);
                        match(DEFAULT);
                        setState(266);
                        match(T__5);
                        setState(267);
                        block();
                    }
                }

                setState(270);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterList(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitList(this);
        }
    }

    public final ListContext list() throws RecognitionException {
        ListContext _localctx = new ListContext(_ctx, getState());
        enterRule(_localctx, 56, RULE_list);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(272);
                match(LISTDCL);
                setState(273);
                type();
                setState(274);
                list_id();
                setState(275);
                list_size();
                setState(276);
                match(ASSIGN);
                setState(277);
                match(T__2);
                setState(284);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << INTEGER) | (1L << DOUBLE) | (1L << STRING))) != 0)) {
                    {
                        {
                            setState(278);
                            val();
                            setState(280);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            if (_la==T__3) {
                                {
                                    setState(279);
                                    match(T__3);
                                }
                            }

                        }
                    }
                    setState(286);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(287);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterList_add(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitList_add(this);
        }
    }

    public final List_addContext list_add() throws RecognitionException {
        List_addContext _localctx = new List_addContext(_ctx, getState());
        enterRule(_localctx, 58, RULE_list_add);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(289);
                match(LISTADD);
                setState(290);
                match(T__2);
                setState(291);
                match(ID);
                setState(292);
                match(T__3);
                setState(293);
                val();
                setState(294);
                match(T__3);
                setState(295);
                match(INTEGER);
                setState(296);
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
        @Override
        public void enterRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).enterList_remove(this);
        }
        @Override
        public void exitRule(ParseTreeListener listener) {
            if ( listener instanceof EzuinoListener) ((EzuinoListener)listener).exitList_remove(this);
        }
    }

    public final List_removeContext list_remove() throws RecognitionException {
        List_removeContext _localctx = new List_removeContext(_ctx, getState());
        enterRule(_localctx, 60, RULE_list_remove);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(298);
                match(LISTREMOVE);
                setState(299);
                match(T__2);
                setState(300);
                match(ID);
                setState(301);
                match(T__3);
                setState(302);
                val();
                setState(303);
                match(T__3);
                setState(304);
                match(INTEGER);
                setState(305);
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

    public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
        switch (ruleIndex) {
            case 7:
                return expr_sempred((ExprContext)_localctx, predIndex);
        }
        return true;
    }
    private boolean expr_sempred(ExprContext _localctx, int predIndex) {
        switch (predIndex) {
            case 0:
                return precpred(_ctx, 5);
            case 1:
                return precpred(_ctx, 4);
            case 2:
                return precpred(_ctx, 3);
            case 3:
                return precpred(_ctx, 2);
        }
        return true;
    }

    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\60\u0136\4\2\t\2"+
                    "\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
                    "\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
                    "\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
                    "\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
                    "\3\2\3\2\3\3\7\3E\n\3\f\3\16\3H\13\3\3\4\3\4\3\4\3\4\5\4N\n\4\3\5\7\5"+
                    "Q\n\5\f\5\16\5T\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6]\n\6\3\6\3\6\3\6"+
                    "\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6k\n\6\3\7\3\7\5\7o\n\7\3\7\3\7"+
                    "\3\7\3\7\3\b\3\b\3\b\5\bx\n\b\3\b\3\b\3\b\6\b}\n\b\r\b\16\b~\5\b\u0081"+
                    "\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u008c\n\t\3\t\3\t\3\t\3\t"+
                    "\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u009a\n\t\f\t\16\t\u009d\13\t\3\n"+
                    "\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\7\r\u00aa\n\r\f\r\16\r\u00ad"+
                    "\13\r\3\r\5\r\u00b0\n\r\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3"+
                    "\21\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3"+
                    "\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\30\6\30\u00d5"+
                    "\n\30\r\30\16\30\u00d6\3\30\5\30\u00da\n\30\3\31\3\31\3\31\3\31\3\31\3"+
                    "\31\3\32\3\32\5\32\u00e4\n\32\3\32\3\32\7\32\u00e8\n\32\f\32\16\32\u00eb"+
                    "\13\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\34\5\34\u00f6\n\34\3"+
                    "\34\3\34\3\35\3\35\3\35\3\35\3\35\7\35\u00ff\n\35\f\35\16\35\u0102\13"+
                    "\35\3\35\3\35\3\35\7\35\u0107\n\35\f\35\16\35\u010a\13\35\3\35\3\35\3"+
                    "\35\5\35\u010f\n\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
                    "\5\36\u011b\n\36\7\36\u011d\n\36\f\36\16\36\u0120\13\36\3\36\3\36\3\37"+
                    "\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3"+
                    " \2\3\20!\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66"+
                    "8:<>\2\7\4\2\30\33\35\36\3\2\26\27\4\2)),.\3\2\t\f\3\2\"#\2\u013b\2@\3"+
                    "\2\2\2\4F\3\2\2\2\6M\3\2\2\2\bR\3\2\2\2\nj\3\2\2\2\fl\3\2\2\2\16t\3\2"+
                    "\2\2\20\u008b\3\2\2\2\22\u009e\3\2\2\2\24\u00a1\3\2\2\2\26\u00a3\3\2\2"+
                    "\2\30\u00af\3\2\2\2\32\u00b1\3\2\2\2\34\u00b5\3\2\2\2\36\u00b7\3\2\2\2"+
                    " \u00b9\3\2\2\2\"\u00bb\3\2\2\2$\u00bd\3\2\2\2&\u00c1\3\2\2\2(\u00c7\3"+
                    "\2\2\2*\u00ca\3\2\2\2,\u00d0\3\2\2\2.\u00d4\3\2\2\2\60\u00db\3\2\2\2\62"+
                    "\u00e1\3\2\2\2\64\u00ee\3\2\2\2\66\u00f1\3\2\2\28\u00f9\3\2\2\2:\u0112"+
                    "\3\2\2\2<\u0123\3\2\2\2>\u012c\3\2\2\2@A\5\4\3\2AB\5\b\5\2B\3\3\2\2\2"+
                    "CE\5\6\4\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2G\5\3\2\2\2HF\3\2\2"+
                    "\2IJ\5\36\20\2JK\7)\2\2KN\3\2\2\2LN\5:\36\2MI\3\2\2\2ML\3\2\2\2N\7\3\2"+
                    "\2\2OQ\5\n\6\2PO\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2S\t\3\2\2\2TR\3"+
                    "\2\2\2UV\7)\2\2VW\7\17\2\2Wk\5\20\t\2Xk\5\16\b\2YZ\7)\2\2Z\\\7\17\2\2"+
                    "[]\7\34\2\2\\[\3\2\2\2\\]\3\2\2\2]^\3\2\2\2^k\5 \21\2_k\5\22\n\2`a\7\21"+
                    "\2\2ab\7\3\2\2bk\7\4\2\2ck\7\20\2\2dk\5.\30\2ek\5\60\31\2fk\5\f\7\2gk"+
                    "\5&\24\2hk\5<\37\2ik\5> \2jU\3\2\2\2jX\3\2\2\2jY\3\2\2\2j_\3\2\2\2j`\3"+
                    "\2\2\2jc\3\2\2\2jd\3\2\2\2je\3\2\2\2jf\3\2\2\2jg\3\2\2\2jh\3\2\2\2ji\3"+
                    "\2\2\2k\13\3\2\2\2ln\7\'\2\2mo\5\36\20\2nm\3\2\2\2no\3\2\2\2op\3\2\2\2"+
                    "pq\7)\2\2qr\5\62\32\2rs\5\66\34\2s\r\3\2\2\2tu\7)\2\2u\u0080\7\5\2\2v"+
                    "x\5\20\t\2wv\3\2\2\2wx\3\2\2\2x\u0081\3\2\2\2y|\5\20\t\2z{\7\6\2\2{}\5"+
                    "\20\t\2|z\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0081\3\2\2\2\u0080"+
                    "w\3\2\2\2\u0080y\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\7\7\2\2\u0083"+
                    "\17\3\2\2\2\u0084\u0085\b\t\1\2\u0085\u008c\5\34\17\2\u0086\u008c\5\16"+
                    "\b\2\u0087\u0088\7\5\2\2\u0088\u0089\5\20\t\2\u0089\u008a\7\7\2\2\u008a"+
                    "\u008c\3\2\2\2\u008b\u0084\3\2\2\2\u008b\u0086\3\2\2\2\u008b\u0087\3\2"+
                    "\2\2\u008c\u009b\3\2\2\2\u008d\u008e\f\7\2\2\u008e\u008f\7\22\2\2\u008f"+
                    "\u009a\5\20\t\b\u0090\u0091\f\6\2\2\u0091\u0092\7\23\2\2\u0092\u009a\5"+
                    "\20\t\7\u0093\u0094\f\5\2\2\u0094\u0095\7\25\2\2\u0095\u009a\5\20\t\6"+
                    "\u0096\u0097\f\4\2\2\u0097\u0098\7\24\2\2\u0098\u009a\5\20\t\5\u0099\u008d"+
                    "\3\2\2\2\u0099\u0090\3\2\2\2\u0099\u0093\3\2\2\2\u0099\u0096\3\2\2\2\u009a"+
                    "\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\21\3\2\2"+
                    "\2\u009d\u009b\3\2\2\2\u009e\u009f\7\16\2\2\u009f\u00a0\5\20\t\2\u00a0"+
                    "\23\3\2\2\2\u00a1\u00a2\t\2\2\2\u00a2\25\3\2\2\2\u00a3\u00a4\t\3\2\2\u00a4"+
                    "\27\3\2\2\2\u00a5\u00ab\5\32\16\2\u00a6\u00a7\5\26\f\2\u00a7\u00a8\5\32"+
                    "\16\2\u00a8\u00aa\3\2\2\2\u00a9\u00a6\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab"+
                    "\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00b0\3\2\2\2\u00ad\u00ab\3\2"+
                    "\2\2\u00ae\u00b0\5 \21\2\u00af\u00a5\3\2\2\2\u00af\u00ae\3\2\2\2\u00b0"+
                    "\31\3\2\2\2\u00b1\u00b2\5\34\17\2\u00b2\u00b3\5\24\13\2\u00b3\u00b4\5"+
                    "\34\17\2\u00b4\33\3\2\2\2\u00b5\u00b6\t\4\2\2\u00b6\35\3\2\2\2\u00b7\u00b8"+
                    "\t\5\2\2\u00b8\37\3\2\2\2\u00b9\u00ba\t\6\2\2\u00ba!\3\2\2\2\u00bb\u00bc"+
                    "\7)\2\2\u00bc#\3\2\2\2\u00bd\u00be\7\3\2\2\u00be\u00bf\7,\2\2\u00bf\u00c0"+
                    "\7\4\2\2\u00c0%\3\2\2\2\u00c1\u00c2\7$\2\2\u00c2\u00c3\7\5\2\2\u00c3\u00c4"+
                    "\5\34\17\2\u00c4\u00c5\7\7\2\2\u00c5\u00c6\58\35\2\u00c6\'\3\2\2\2\u00c7"+
                    "\u00c8\7&\2\2\u00c8\u00c9\5\20\t\2\u00c9)\3\2\2\2\u00ca\u00cb\7 \2\2\u00cb"+
                    "\u00cc\7\5\2\2\u00cc\u00cd\5\30\r\2\u00cd\u00ce\7\7\2\2\u00ce\u00cf\5"+
                    "\66\34\2\u00cf+\3\2\2\2\u00d0\u00d1\7\37\2\2\u00d1\u00d2\5\66\34\2\u00d2"+
                    "-\3\2\2\2\u00d3\u00d5\5*\26\2\u00d4\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2"+
                    "\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d9\3\2\2\2\u00d8\u00da"+
                    "\5,\27\2\u00d9\u00d8\3\2\2\2\u00d9\u00da\3\2\2\2\u00da/\3\2\2\2\u00db"+
                    "\u00dc\7!\2\2\u00dc\u00dd\7\5\2\2\u00dd\u00de\5\30\r\2\u00de\u00df\7\7"+
                    "\2\2\u00df\u00e0\5\66\34\2\u00e0\61\3\2\2\2\u00e1\u00e3\7\5\2\2\u00e2"+
                    "\u00e4\5\64\33\2\u00e3\u00e2\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e9\3"+
                    "\2\2\2\u00e5\u00e6\7\6\2\2\u00e6\u00e8\5\64\33\2\u00e7\u00e5\3\2\2\2\u00e8"+
                    "\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00ec\3\2"+
                    "\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00ed\7\7\2\2\u00ed\63\3\2\2\2\u00ee\u00ef"+
                    "\5\36\20\2\u00ef\u00f0\7)\2\2\u00f0\65\3\2\2\2\u00f1\u00f2\7*\2\2\u00f2"+
                    "\u00f3\5\4\3\2\u00f3\u00f5\5\b\5\2\u00f4\u00f6\5(\25\2\u00f5\u00f4\3\2"+
                    "\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8\7+\2\2\u00f8"+
                    "\67\3\2\2\2\u00f9\u0108\7*\2\2\u00fa\u00fb\7%\2\2\u00fb\u0100\5\34\17"+
                    "\2\u00fc\u00fd\7\6\2\2\u00fd\u00ff\5\34\17\2\u00fe\u00fc\3\2\2\2\u00ff"+
                    "\u0102\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0103\3\2"+
                    "\2\2\u0102\u0100\3\2\2\2\u0103\u0104\7\b\2\2\u0104\u0105\5\66\34\2\u0105"+
                    "\u0107\3\2\2\2\u0106\u00fa\3\2\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2"+
                    "\2\2\u0108\u0109\3\2\2\2\u0109\u010e\3\2\2\2\u010a\u0108\3\2\2\2\u010b"+
                    "\u010c\7(\2\2\u010c\u010d\7\b\2\2\u010d\u010f\5\66\34\2\u010e\u010b\3"+
                    "\2\2\2\u010e\u010f\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0111\7+\2\2\u0111"+
                    "9\3\2\2\2\u0112\u0113\7\r\2\2\u0113\u0114\5\36\20\2\u0114\u0115\5\"\22"+
                    "\2\u0115\u0116\5$\23\2\u0116\u0117\7\17\2\2\u0117\u011e\7\5\2\2\u0118"+
                    "\u011a\5\34\17\2\u0119\u011b\7\6\2\2\u011a\u0119\3\2\2\2\u011a\u011b\3"+
                    "\2\2\2\u011b\u011d\3\2\2\2\u011c\u0118\3\2\2\2\u011d\u0120\3\2\2\2\u011e"+
                    "\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0121\3\2\2\2\u0120\u011e\3\2"+
                    "\2\2\u0121\u0122\7\7\2\2\u0122;\3\2\2\2\u0123\u0124\7\20\2\2\u0124\u0125"+
                    "\7\5\2\2\u0125\u0126\7)\2\2\u0126\u0127\7\6\2\2\u0127\u0128\5\34\17\2"+
                    "\u0128\u0129\7\6\2\2\u0129\u012a\7,\2\2\u012a\u012b\7\7\2\2\u012b=\3\2"+
                    "\2\2\u012c\u012d\7\21\2\2\u012d\u012e\7\5\2\2\u012e\u012f\7)\2\2\u012f"+
                    "\u0130\7\6\2\2\u0130\u0131\5\34\17\2\u0131\u0132\7\6\2\2\u0132\u0133\7"+
                    ",\2\2\u0133\u0134\7\7\2\2\u0134?\3\2\2\2\32FMR\\jnw~\u0080\u008b\u0099"+
                    "\u009b\u00ab\u00af\u00d6\u00d9\u00e3\u00e9\u00f5\u0100\u0108\u010e\u011a"+
                    "\u011e";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}