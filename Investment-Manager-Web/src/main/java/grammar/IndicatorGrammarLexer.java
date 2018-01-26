// Generated from grammar\IndicatorGrammar.g4 by ANTLR 4.7
package grammar;



import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IndicatorGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, PLUS=5, MINUS=6, MULT=7, DIV=8, MOD=9, 
		ID=10, INT=11, FLOAT=12, NEWLINE=13, SPACE=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "PLUS", "MINUS", "MULT", "DIV", "MOD", 
		"ID", "INT", "FLOAT", "NEWLINE", "SPACE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "'('", "')'", "'$'", "'+'", "'-'", "'*'", "'/'", "'%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "PLUS", "MINUS", "MULT", "DIV", "MOD", "ID", 
		"INT", "FLOAT", "NEWLINE", "SPACE"
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


	public IndicatorGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IndicatorGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\20Z\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\7\13\64\n\13\f\13"+
		"\16\13\67\13\13\3\f\6\f:\n\f\r\f\16\f;\3\r\6\r?\n\r\r\r\16\r@\3\r\3\r"+
		"\7\rE\n\r\f\r\16\rH\13\r\3\r\3\r\6\rL\n\r\r\r\16\rM\5\rP\n\r\3\16\5\16"+
		"S\n\16\3\16\3\16\3\17\3\17\3\17\3\17\2\2\20\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\3\2\6\5\2C\\aac|\6\2\62;C\\"+
		"aac|\3\2\62;\4\2\13\13\"\"\2`\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3"+
		"\37\3\2\2\2\5!\3\2\2\2\7#\3\2\2\2\t%\3\2\2\2\13\'\3\2\2\2\r)\3\2\2\2\17"+
		"+\3\2\2\2\21-\3\2\2\2\23/\3\2\2\2\25\61\3\2\2\2\279\3\2\2\2\31O\3\2\2"+
		"\2\33R\3\2\2\2\35V\3\2\2\2\37 \7?\2\2 \4\3\2\2\2!\"\7*\2\2\"\6\3\2\2\2"+
		"#$\7+\2\2$\b\3\2\2\2%&\7&\2\2&\n\3\2\2\2\'(\7-\2\2(\f\3\2\2\2)*\7/\2\2"+
		"*\16\3\2\2\2+,\7,\2\2,\20\3\2\2\2-.\7\61\2\2.\22\3\2\2\2/\60\7\'\2\2\60"+
		"\24\3\2\2\2\61\65\t\2\2\2\62\64\t\3\2\2\63\62\3\2\2\2\64\67\3\2\2\2\65"+
		"\63\3\2\2\2\65\66\3\2\2\2\66\26\3\2\2\2\67\65\3\2\2\28:\t\4\2\298\3\2"+
		"\2\2:;\3\2\2\2;9\3\2\2\2;<\3\2\2\2<\30\3\2\2\2=?\t\4\2\2>=\3\2\2\2?@\3"+
		"\2\2\2@>\3\2\2\2@A\3\2\2\2AB\3\2\2\2BF\7\60\2\2CE\t\4\2\2DC\3\2\2\2EH"+
		"\3\2\2\2FD\3\2\2\2FG\3\2\2\2GP\3\2\2\2HF\3\2\2\2IK\7\60\2\2JL\t\4\2\2"+
		"KJ\3\2\2\2LM\3\2\2\2MK\3\2\2\2MN\3\2\2\2NP\3\2\2\2O>\3\2\2\2OI\3\2\2\2"+
		"P\32\3\2\2\2QS\7\17\2\2RQ\3\2\2\2RS\3\2\2\2ST\3\2\2\2TU\7\f\2\2U\34\3"+
		"\2\2\2VW\t\5\2\2WX\3\2\2\2XY\b\17\2\2Y\36\3\2\2\2\n\2\65;@FMOR\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}