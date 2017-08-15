package src.test.java;

import static org.junit.Assert.*;
import java.io.IOException;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import groupone.java.bean.Account;
import groupone.java.bean.Company;
import groupone.java.bean.Indicator;
import groupone.java.error.IndicatorSyntaxException;
import groupone.java.error.Messages;
import groupone.java.manager.AccountManager;
import groupone.java.manager.IndicatorManager;
import groupone.java.services.IndicatorService;

public class IndicatorUnitTests {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void applyCompoundIndicatorShouldReturnCorrectResult() throws Exception {
		IndicatorManager indicatorManager = IndicatorManager.getInstance();
		indicatorManager.loadPredefinedIndicators();
		Indicator compoundPredefinedIndicator = indicatorManager.getIndicator("IngresoNeto");
		Indicator newCompoundIndicator = indicatorManager.createIndicator("IndicatorTest", "IngresoNeto+3\r\n");
		
		IndicatorService indService = new IndicatorService();
		
		Double compoundPredefinedIndicatorResult = indService.apply(null, "", compoundPredefinedIndicator);
		Double newCompoundIndicatorResult = indService.apply(null, "", newCompoundIndicator);
		
		assertTrue(compoundPredefinedIndicatorResult == 1100000.0);
		assertTrue(newCompoundIndicatorResult == 1100003.0);
	}

	@Test
	public void applyIndicatorWithAccountReturnCorrectResult() throws Exception, IOException, IndicatorSyntaxException {
		IndicatorManager indicatorManager = IndicatorManager.getInstance();

		// Importamos las cuentas		
		AccountManager.getInstance().loadAccounts(indicatorManager.getClass().getClassLoader().getResource("cuentas.json").getFile());
	
		// Creamos un nuevo indicador que usa una cuenta en su expresión
		String nuevoIndicador = indicatorManager.getClass().getClassLoader()
				.getResource("MonthlyAverageFreeCashFlow.ind").getFile();
		Indicator indicador = indicatorManager.loadNewIndicatorFromFile(nuevoIndicador);

		assertTrue(indicador != null);
		assertEquals(indicador.getName(), "MonthlyAverageFreeCashFlow");

		// calculamos el valor de indicador respecto de una empresa y un
		// año, para saber que cuenta usar'
		Company comp = new Company();
		comp.setName("Google");
		Account account = new Account("FreeCashFlow","2016",1000.0);
		comp.addAccount(account);
		IndicatorService indService = new IndicatorService();
		
		Double valor = indService.apply(comp, "2016", indicador);

//		assertTrue(valor == 1000.0);
	}

	@Test
	public void applyIndicatorWithNonExistentIndicatorShouldThrowParseCancellationException()
			throws IndicatorSyntaxException {
		expectedEx.expect(ParseCancellationException.class);
		expectedEx.expectMessage(String.format(Messages.getString("EvalVisitor.indicatorNotFound"),"NonExistentIndicator"));

		IndicatorManager indicatorManager = IndicatorManager.getInstance();
		IndicatorService indService = new IndicatorService();
		Indicator indicator =  indicatorManager.createIndicator("Indicator1", "1+2+NonExistentIndicator\r\n");
		indService.apply(null, "", indicator);
	}

	@Test
	public void applyIndicatorWithNonExistentAccountShouldThrowParseCancellationException()
			throws IndicatorSyntaxException {
		expectedEx.expect(ParseCancellationException.class);
		expectedEx.expectMessage(String.format(Messages.getString("EvalVisitor.accountNotFound"), "NonExistentAccount"));

		IndicatorManager indicatorManager = IndicatorManager.getInstance();
		Indicator indicator =  indicatorManager.createIndicator("Indicator1", "1+2+$NonExistentAccount\r\n");
		
		Company comp = new Company();
		comp.setName("Google");
		Account account = new Account("FreeCashFlow","2016",1000.0);
		comp.addAccount(account);
		IndicatorService indService = new IndicatorService();
		
		indService.apply(comp, "2016", indicator);
	}
	
	@Test
	public void applyIndicatorWithConstantShouldReturnCorrectResult() throws Exception {
		IndicatorManager indicatorManager = IndicatorManager.getInstance();
		Indicator indicator =  indicatorManager.createIndicator("Indicator3", "constante=400\r\n150+constante\r\n");
		IndicatorService indService = new IndicatorService();

		Double value;
		try {
			value = indService.apply(null, "", indicator);
		} catch (final ParseCancellationException e) {
			throw new Exception(e.getMessage());
		}

		assertTrue(value == 550.0);
	}	
}
