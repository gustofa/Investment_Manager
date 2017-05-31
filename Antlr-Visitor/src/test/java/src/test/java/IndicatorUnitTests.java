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
import groupone.java.investment.Account;
import groupone.java.investment.AccountList;
import groupone.java.investment.EvalVisitor;
import groupone.java.investment.FactoryIndicator;
import groupone.java.investment.Indicator;
import groupone.java.investment.IndicatorErrorListener;
import groupone.java.investment.IndicatorList;

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
		String indicator = "Indicador=5+3+4*(9+8)+4+6/2\r\n";//83

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

	
	@Test
	public void indicatorEvaluationResultIndicatorComplex() throws Exception {
		
		Double valor ;
		
		FactoryIndicator fabrica = new FactoryIndicator();
		
		Indicator indicador1 = fabrica.newIndicator();
		Indicator indicador2 = fabrica.newIndicator();
		Indicator indicador3 = fabrica.newIndicator();

		indicador1.setName("indicador1");
		indicador1.setExpression("200000/2\r\n");
		indicador2.setName("indicador2");
		indicador2.setExpression("100000+2\r\n");
		indicador3.setName("indicador3");
		indicador3.setExpression("(indicador1+indicador2)/2\r\n");		
		
		IndicatorList.indicatorsList.add(indicador1);
		IndicatorList.indicatorsList.add(indicador2);
		IndicatorList.indicatorsList.add(indicador3);
		
		System.out.println("parsing: " + indicador3.getName() + " | " + indicador3.getExpression());		

		
		try{
			//visitor.visit(tree);
			valor = indicador3.getResult("", "");
		}catch(final ParseCancellationException e){
			throw new Exception(e.getMessage());
		}
		
		assertTrue(valor == 100001);
	}
/*	
	@Test
	public void indicatorEvaluationResultIndicatorWithAccount() throws Exception {
		
		Double valor ;
		Account cuenta = new Account("Cuenta", "Empresa", "2017", 10000.0);
		AccountList.listaCuentas.add(cuenta);
		
		FactoryIndicator fabrica = new FactoryIndicator();
		
		Indicator indicador1 = fabrica.newIndicator();
		Indicator indicador2 = fabrica.newIndicator();
		Indicator indicador3 = fabrica.newIndicator();

		indicador1.setName("indicador1");
		indicador1.setExpression("200000/2\r\n");
		indicador2.setName("indicador2");
		indicador2.setExpression("100000+2\r\n");
		indicador3.setName("indicador3");
		indicador3.setExpression("(indicador1+indicador2)/$Cuenta\r\n");		
		
		IndicatorList.indicatorsList.add(indicador1);
		IndicatorList.indicatorsList.add(indicador2);
		IndicatorList.indicatorsList.add(indicador3);
		
		System.out.println("parsing: " + indicador3.getName() + " | " + indicador3.getExpression());		

		
		try{
			//visitor.visit(tree);
			valor = indicador3.getResult("Empresa", "2017");
		}catch(final ParseCancellationException e){
			throw new Exception(e.getMessage());
		}
		
		assertTrue(valor == 102.0);
	}	
	*/
}
