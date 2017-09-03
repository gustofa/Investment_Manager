package src.test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import groupone.java.services.IndicatorService;

public class IndicatorUnitTests {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void applyCompoundIndicatorShouldReturnCorrectResult() throws Exception {
		IndicatorService indicatorService = IndicatorService.getInstance();
		indicatorService .loadPredefinedIndicators();
		Indicator compoundPredefinedIndicator = indicatorService .getIndicator("IngresoNeto");
		Indicator newCompoundIndicator = indicatorService .createIndicator("IndicatorTest", "IngresoNeto+3\r\n");
		
		Double compoundPredefinedIndicatorResult = indicatorService .apply(null, "", compoundPredefinedIndicator);
		Double newCompoundIndicatorResult = indicatorService .apply(null, "", newCompoundIndicator);
		
		assertTrue(compoundPredefinedIndicatorResult == 1100000.0);
		assertTrue(newCompoundIndicatorResult == 1100003.0);
	}

	@Test
	public void applyIndicatorWithAccountReturnCorrectResult() throws Exception, IOException, IndicatorSyntaxException {
		IndicatorService indicatorService = IndicatorService.getInstance();
		
		// Importamos las cuentas		
		AccountManager.getInstance().loadAccounts(indicatorService.getClass().getClassLoader().getResource("cuentas.json").getFile());
	
		// Creamos un nuevo indicador que usa una cuenta en su expresión
		String nuevoIndicador = indicatorService.getClass().getClassLoader()
				.getResource("MonthlyAverageFreeCashFlow.ind").getFile();
		Indicator indicador = indicatorService.loadNewIndicatorFromFile(nuevoIndicador);

		assertTrue(indicador != null);
		assertEquals(indicador.getName(), "MonthlyAverageFreeCashFlow");

		// calculamos el valor de indicador respecto de una empresa y un
		// año, para saber que cuenta usar'
		Company company = new Company();
		company.setName("Google");
		Account account = new Account("FreeCashFlow","2016",1200.0);
		company.addAccount(account);
		
		Double valor = indicatorService.apply(company, "2016", indicador);

		assertTrue(valor == 100);
	}

	@Test
	public void applyIndicatorWithNonExistentIndicatorShouldThrowParseCancellationException()
			throws IndicatorSyntaxException {
		expectedEx.expect(ParseCancellationException.class);
		expectedEx.expectMessage(String.format(Messages.getString("EvalVisitor.indicatorNotFound"),"NonExistentIndicator"));

		IndicatorService indicatorService = IndicatorService.getInstance();
		Indicator indicator =  indicatorService.createIndicator("Indicator1", "1+2+NonExistentIndicator\r\n");
		indicatorService.apply(null, "", indicator);
	}

	@Test
	public void applyIndicatorWithNonExistentAccountShouldThrowParseCancellationException()
			throws IndicatorSyntaxException {
		expectedEx.expect(ParseCancellationException.class);
		expectedEx.expectMessage(String.format(Messages.getString("EvalVisitor.accountNotFound"), "NonExistentAccount"));

		IndicatorService indicatorService = IndicatorService.getInstance();
		Indicator indicator =  indicatorService.createIndicator("Indicator1", "1+2+$NonExistentAccount\r\n");
		
		Company comp = new Company();
		comp.setName("Google");
		Account account = new Account("FreeCashFlow","2016",1000.0);
		comp.addAccount(account);
		
		indicatorService.apply(comp, "2016", indicator);
	}
	
	@Test
	public void applyIndicatorWithConstantShouldReturnCorrectResult() throws Exception {
		IndicatorService indicatorService = IndicatorService.getInstance();
		Indicator indicator =  indicatorService.createIndicator("Indicator3", "constante=400\r\n150+constante\r\n");

		Double value;
		try {
			value = indicatorService.apply(null, "", indicator);
		} catch (final ParseCancellationException e) {
			throw new Exception(e.getMessage());
		}

		assertTrue(value == 550.0);
	}	
}