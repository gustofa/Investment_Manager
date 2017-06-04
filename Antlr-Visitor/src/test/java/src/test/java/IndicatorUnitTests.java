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
import groupone.java.investment.EvalVisitor;
import groupone.java.investment.Indicator;
import groupone.java.investment.IndicatorErrorListener;
import groupone.java.investment.IndicatorManager;

@SuppressWarnings("deprecation")
public class IndicatorUnitTests {
	
	@Test
	public void applyCompoundIndicatorShouldReturnCorrectResult() throws Exception {
		IndicatorManager indicatorManager = IndicatorManager.getInstance();
		indicatorManager.loadPredefinedIndicators();
		Indicator indicator = indicatorManager.getIndicator("IngresoNeto");
	
		Double value;
		try{
			value = indicator.apply("", "");
		}catch(final ParseCancellationException e){
			throw new Exception(e.getMessage());
		}
		
		assertTrue(value == 1100000.0);
	}
}
