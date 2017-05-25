package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;

import main.g4.grammar.IndicatorGrammarLexer;
import main.g4.grammar.IndicatorGrammarParser;
import main.java.IndicatorListener;

public class IndicatorListenerTest {

	 public static final String INPUT_PATH = "/prueba.txt";
	   

	    @Test
	    public void testGramatica() throws IOException {
	        String file = this.getInputFilePath();

	        // ---- Esto es para evaluar una gramatica y crear expresiones -----------
	        IndicatorGrammarLexer lexer = new IndicatorGrammarLexer(new ANTLRFileStream(file));
	        CommonTokenStream tokens = new CommonTokenStream(lexer);
	        IndicatorGrammarParser parser = new IndicatorGrammarParser(tokens);
	        IndicatorGrammarParser.IndicadorContext indicadorContext = parser.indicador();
	        ParseTreeWalker walker = new ParseTreeWalker();
	        IndicatorListener listener = new IndicatorListener();
	        walker.walk(listener, indicadorContext);

	        Assert.assertTrue(true);

	    }

	    private String getInputFilePath() {
	        return this.getClass().getResource(INPUT_PATH).getPath();
	    }

}
