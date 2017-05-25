// Generated from IndicatorGrammar.g4 by ANTLR 4.4
package main.g4.grammar;

	
	

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IndicatorGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LPAREN=1, RPAREN=2, PLUS=3, MINUS=4, MULTI=5, DIV=6, POINT=7, LETTER=8, 
		DIGIT=9, WS=10;
	public static final String[] tokenNames = {
		"<INVALID>", "'('", "')'", "'+'", "'-'", "'*'", "'/'", "'.'", "LETTER", 
		"DIGIT", "WS"
	};
	public static final int
		RULE_indicador = 0, RULE_term = 1, RULE_factor = 2, RULE_number = 3, RULE_variable = 4;
	public static final String[] ruleNames = {
		"indicador", "term", "factor", "number", "variable"
	};

	@Override
	public String getGrammarFileName() { return "IndicatorGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public IndicatorGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class IndicadorContext extends ParserRuleContext {
		public TerminalNode MINUS(int i) {
			return getToken(IndicatorGrammarParser.MINUS, i);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(IndicatorGrammarParser.PLUS); }
		public List<TerminalNode> MINUS() { return getTokens(IndicatorGrammarParser.MINUS); }
		public TerminalNode PLUS(int i) {
			return getToken(IndicatorGrammarParser.PLUS, i);
		}
		public IndicadorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indicador; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicatorGrammarListener ) ((IndicatorGrammarListener)listener).enterIndicador(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicatorGrammarListener ) ((IndicatorGrammarListener)listener).exitIndicador(this);
		}
	}

	public final IndicadorContext indicador() throws RecognitionException {
		IndicadorContext _localctx = new IndicadorContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_indicador);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10); term();
			setState(15);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(11);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(12); term();
				}
				}
				setState(17);
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

	public static class TermContext extends ParserRuleContext {
		public List<TerminalNode> MULTI() { return getTokens(IndicatorGrammarParser.MULTI); }
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public TerminalNode MULTI(int i) {
			return getToken(IndicatorGrammarParser.MULTI, i);
		}
		public List<TerminalNode> DIV() { return getTokens(IndicatorGrammarParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(IndicatorGrammarParser.DIV, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicatorGrammarListener ) ((IndicatorGrammarListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicatorGrammarListener ) ((IndicatorGrammarListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18); factor();
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MULTI || _la==DIV) {
				{
				{
				setState(19);
				_la = _input.LA(1);
				if ( !(_la==MULTI || _la==DIV) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(20); factor();
				}
				}
				setState(25);
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

	public static class FactorContext extends ParserRuleContext {
		public IndicadorContext indicador() {
			return getRuleContext(IndicadorContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(IndicatorGrammarParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(IndicatorGrammarParser.RPAREN, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicatorGrammarListener ) ((IndicatorGrammarListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicatorGrammarListener ) ((IndicatorGrammarListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_factor);
		try {
			setState(32);
			switch (_input.LA(1)) {
			case LETTER:
				enterOuterAlt(_localctx, 1);
				{
				setState(26); variable();
				}
				break;
			case MINUS:
			case DIGIT:
				enterOuterAlt(_localctx, 2);
				{
				setState(27); number();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 3);
				{
				setState(28); match(LPAREN);
				setState(29); indicador();
				setState(30); match(RPAREN);
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

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode POINT() { return getToken(IndicatorGrammarParser.POINT, 0); }
		public TerminalNode DIGIT(int i) {
			return getToken(IndicatorGrammarParser.DIGIT, i);
		}
		public List<TerminalNode> DIGIT() { return getTokens(IndicatorGrammarParser.DIGIT); }
		public TerminalNode MINUS() { return getToken(IndicatorGrammarParser.MINUS, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicatorGrammarListener ) ((IndicatorGrammarListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicatorGrammarListener ) ((IndicatorGrammarListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(34); match(MINUS);
				}
			}

			setState(38); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(37); match(DIGIT);
				}
				}
				setState(40); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGIT );
			setState(48);
			_la = _input.LA(1);
			if (_la==POINT) {
				{
				setState(42); match(POINT);
				setState(44); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(43); match(DIGIT);
					}
					}
					setState(46); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DIGIT );
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

	public static class VariableContext extends ParserRuleContext {
		public List<TerminalNode> LETTER() { return getTokens(IndicatorGrammarParser.LETTER); }
		public TerminalNode DIGIT(int i) {
			return getToken(IndicatorGrammarParser.DIGIT, i);
		}
		public TerminalNode LETTER(int i) {
			return getToken(IndicatorGrammarParser.LETTER, i);
		}
		public List<TerminalNode> DIGIT() { return getTokens(IndicatorGrammarParser.DIGIT); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IndicatorGrammarListener ) ((IndicatorGrammarListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IndicatorGrammarListener ) ((IndicatorGrammarListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50); match(LETTER);
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LETTER || _la==DIGIT) {
				{
				{
				setState(51);
				_la = _input.LA(1);
				if ( !(_la==LETTER || _la==DIGIT) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(56);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\f<\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\7\2\20\n\2\f\2\16\2\23\13\2\3\3"+
		"\3\3\3\3\7\3\30\n\3\f\3\16\3\33\13\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4#\n\4"+
		"\3\5\5\5&\n\5\3\5\6\5)\n\5\r\5\16\5*\3\5\3\5\6\5/\n\5\r\5\16\5\60\5\5"+
		"\63\n\5\3\6\3\6\7\6\67\n\6\f\6\16\6:\13\6\3\6\2\2\7\2\4\6\b\n\2\5\3\2"+
		"\5\6\3\2\7\b\3\2\n\13?\2\f\3\2\2\2\4\24\3\2\2\2\6\"\3\2\2\2\b%\3\2\2\2"+
		"\n\64\3\2\2\2\f\21\5\4\3\2\r\16\t\2\2\2\16\20\5\4\3\2\17\r\3\2\2\2\20"+
		"\23\3\2\2\2\21\17\3\2\2\2\21\22\3\2\2\2\22\3\3\2\2\2\23\21\3\2\2\2\24"+
		"\31\5\6\4\2\25\26\t\3\2\2\26\30\5\6\4\2\27\25\3\2\2\2\30\33\3\2\2\2\31"+
		"\27\3\2\2\2\31\32\3\2\2\2\32\5\3\2\2\2\33\31\3\2\2\2\34#\5\n\6\2\35#\5"+
		"\b\5\2\36\37\7\3\2\2\37 \5\2\2\2 !\7\4\2\2!#\3\2\2\2\"\34\3\2\2\2\"\35"+
		"\3\2\2\2\"\36\3\2\2\2#\7\3\2\2\2$&\7\6\2\2%$\3\2\2\2%&\3\2\2\2&(\3\2\2"+
		"\2\')\7\13\2\2(\'\3\2\2\2)*\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\62\3\2\2\2,."+
		"\7\t\2\2-/\7\13\2\2.-\3\2\2\2/\60\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61"+
		"\63\3\2\2\2\62,\3\2\2\2\62\63\3\2\2\2\63\t\3\2\2\2\648\7\n\2\2\65\67\t"+
		"\4\2\2\66\65\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29\13\3\2\2\2:8\3"+
		"\2\2\2\n\21\31\"%*\60\628";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}