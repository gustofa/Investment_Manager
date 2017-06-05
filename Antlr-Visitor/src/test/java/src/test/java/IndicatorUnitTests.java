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

public class IndicatorUnitTests {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void applyCompoundIndicatorShouldReturnCorrectResult() throws Exception {
		IndicatorManager indicatorManager = IndicatorManager.getInstance();
		indicatorManager.loadPredefinedIndicators();
		Indicator indicator = indicatorManager.getIndicator("IngresoNeto");

		Double value;
		try {
			value = indicator.apply("", "");
		} catch (final ParseCancellationException e) {
			throw new Exception(e.getMessage());
		}

		assertTrue(value == 1100000.0);
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
		expectedEx.expectMessage("Indicador no encontrado: NonExistentIndicator");

		IndicatorManager indicatorManager = IndicatorManager.getInstance();
		Indicator indicator = new Indicator();
		ParseTree parseTree = indicatorManager.parseExpression("1+2+NonExistentIndicator");
		indicator.setParseTree(parseTree);
		indicator.apply("", "");
		assertTrue(true);
	}

	@Test
	public void applyIndicatorWithNonExistentAccountShouldThrowParseCancellationException()
			throws IndicatorSyntaxException {
		expectedEx.expect(ParseCancellationException.class);
		expectedEx.expectMessage("Cuenta no encontrada: NonExistentAccount");

		IndicatorManager indicatorManager = IndicatorManager.getInstance();
		Indicator indicator = new Indicator();
		ParseTree parseTree = indicatorManager.parseExpression("1+2+$NonExistentAccount");
		indicator.setParseTree(parseTree);
		indicator.apply("", "");
		assertTrue(true);
	}
}
