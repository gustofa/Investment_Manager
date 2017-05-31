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
import groupone.java.investment.IndicatorErrorListener;

public class IndicatorUnitTests {
	
	@Test
	public void indicatorEvaluationWithSyntacticalErrorsGeneratesErrorMessages() throws Exception {
		String indicator = "+++++OtroIndicador=50tertrter\r\n(2*2)+OtroIndicador+$9954\r\n";

		System.out.println("parsing: " + indicator);		
		IndicatorErrorListener indicatorErrorListener = new IndicatorErrorListener(); 
		@SuppressWarnings("deprecation")
		IndicatorGrammarLexer lexer = new IndicatorGrammarLexer(new ANTLRInputStream(indicator));
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

		Assert.assertFalse(indicatorErrorListener.getErrorMessage().isEmpty());
	}
	
	@Test
	public void indicatorEvaluationRespectOperatorsPriority() throws Exception {
		String indicator = "Indicador=5+3+4*(9+8)+4+6/2";//83

		System.out.println("parsing: " + indicator);		
		IndicatorErrorListener indicatorErrorListener = new IndicatorErrorListener(); 
		@SuppressWarnings("deprecation")
		IndicatorGrammarLexer lexer = new IndicatorGrammarLexer(new ANTLRInputStream(indicator));
		lexer.addErrorListener(indicatorErrorListener);
		IndicatorGrammarParser parser = new IndicatorGrammarParser(new CommonTokenStream(lexer));
		parser.addErrorListener(indicatorErrorListener);
		ParseTree tree = parser.prog();
		EvalVisitor visitor = new EvalVisitor();

		Double result;
		try{
			result = visitor.visit(tree);
		}catch(final ParseCancellationException e){
			throw new Exception(e.getMessage());
		}

		Assert.assertTrue(result==83);
	}

}
