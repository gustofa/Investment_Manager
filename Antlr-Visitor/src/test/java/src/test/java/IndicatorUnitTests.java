package src.test.java;

import static org.junit.Assert.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;
import grammar.IndicatorGrammarLexer;
import grammar.IndicatorGrammarParser;
import groupone.java.indicator.EvalVisitor;
import groupone.java.indicator.IndicatorErrorListener;

public class IndicatorUnitTests {
	@Test
	public void testGramatica() throws Exception {
		String nuevoIndicador = "OtroIndicador=50\r\n(2dasljadsjkas*2)+OtroIndicador\r\n";

		System.out.println("parsing: " + nuevoIndicador);		
		IndicatorErrorListener indicatorErrorListener = new IndicatorErrorListener(); 
		IndicatorGrammarLexer lexer = new IndicatorGrammarLexer(new ANTLRInputStream(nuevoIndicador));
		lexer.addErrorListener(indicatorErrorListener);
		IndicatorGrammarParser parser = new IndicatorGrammarParser(new CommonTokenStream(lexer));
		parser.addErrorListener(indicatorErrorListener);
		ParseTree tree = parser.prog();
		EvalVisitor visitor = new EvalVisitor();
		
		try{
			visitor.visit(tree);
		}catch(final ParseCancellationException e){
			throw new Exception(e.getMessage());
		}

		Assert.assertTrue(true);
	}
}
