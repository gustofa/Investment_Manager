package src.test.java;

import static org.junit.Assert.*;
import java.io.IOException;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import groupone.java.investment.AccountManager;
import groupone.java.investment.Indicator;
import groupone.java.investment.IndicatorManager;
import groupone.java.investment.IndicatorSyntaxException;
import groupone.java.investment.Messages;

public class IndicatorUnitTests {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void applyCompoundIndicatorShouldReturnCorrectResult() throws Exception {
		IndicatorManager indicatorManager = IndicatorManager.getInstance();
		indicatorManager.loadPredefinedIndicators();
		Indicator compoundPredefinedIndicator = indicatorManager.getIndicator("IngresoNeto");
		Indicator newCompoundIndicator = indicatorManager.createIndicator("IndicatorTest", "IngresoNeto+3\r\n");
		
		Double compoundPredefinedIndicatorResult = compoundPredefinedIndicator.apply("", "");
		Double newCompoundIndicatorResult = newCompoundIndicator.apply("", "");
		
		assertTrue(compoundPredefinedIndicatorResult == 1100000.0);
		assertTrue(newCompoundIndicatorResult == 1100003.0);
	}

	@Test
	public void applyIndicatorWithAccountReturnCorrectResult() throws Exception, IOException, IndicatorSyntaxException {
		IndicatorManager indicatorManager = IndicatorManager.getInstance();

		// Importamos las cuentas
		AccountManager accountManager = new AccountManager();
		accountManager.agregarCuentas(indicatorManager.getClass().getClassLoader().getResource("cuentas.json").getFile());
	
		// Creamos un nuevo indicador que usa una cuenta en su expresión
		String nuevoIndicador = indicatorManager.getClass().getClassLoader()
				.getResource("MonthlyAverageFreeCashFlow.ind").getFile();
		Indicator indicador = indicatorManager.loadNewIndicatorFromFile(nuevoIndicador);

		assertTrue(indicador != null);
		assertEquals(indicador.getName(), "MonthlyAverageFreeCashFlow");

		// calculamos el valor de indicador respecto de una empresa y un
		// año, para saber que cuenta usar
		Double valor = indicador.apply("Google", "2016");

		assertTrue(valor == 1000.00);
	}

	@Test
	public void applyIndicatorWithNonExistentIndicatorShouldThrowParseCancellationException()
			throws IndicatorSyntaxException {
		expectedEx.expect(ParseCancellationException.class);
		expectedEx.expectMessage(String.format(Messages.getString("EvalVisitor.indicatorNotFound"),"NonExistentIndicator"));

		IndicatorManager indicatorManager = IndicatorManager.getInstance();
		Indicator indicator =  indicatorManager.createIndicator("Indicator1", "1+2+NonExistentIndicator\r\n");
		indicator.apply("", "");
	}

	@Test
	public void applyIndicatorWithNonExistentAccountShouldThrowParseCancellationException()
			throws IndicatorSyntaxException {
		expectedEx.expect(ParseCancellationException.class);
		expectedEx.expectMessage(String.format(Messages.getString("EvalVisitor.accountNotFound"), "NonExistentAccount"));

		IndicatorManager indicatorManager = IndicatorManager.getInstance();
		Indicator indicator =  indicatorManager.createIndicator("Indicator1", "1+2+$NonExistentAccount\r\n");
		indicator.apply("", "");
	}
	
	@Test
	public void applyIndicatorWithConstantShouldReturnCorrectResult() throws Exception {
		IndicatorManager indicatorManager = IndicatorManager.getInstance();
		Indicator indicator =  indicatorManager.createIndicator("Indicator3", "constante=400\r\n150+constante\r\n");

		Double value;
		try {
			value = indicator.apply("", "");
		} catch (final ParseCancellationException e) {
			throw new Exception(e.getMessage());
		}

		assertTrue(value == 550.0);
	}	
}