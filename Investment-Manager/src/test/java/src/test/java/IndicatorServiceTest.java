package src.test.java;

import static org.junit.Assert.*;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import groupone.java.bean.Indicator;
import groupone.java.error.IndicatorSyntaxException;
import groupone.java.services.IndicatorService;

public class IndicatorServiceTest {
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
/*
	@Test
	public void loadPredefinedIndicatorsShouldKeepIndicatorsInMemory() throws IOException, IndicatorSyntaxException {
		IndicatorService indicatorService = IndicatorService.getInstance();
		indicatorService.loadPredefinedIndicators();

		Indicator ingresoNetoEnOperacionesContinuas = indicatorService
				.getIndicator("IngresoNetoEnOperacionesContinuas");
		assertTrue(ingresoNetoEnOperacionesContinuas != null);
		assertEquals(ingresoNetoEnOperacionesContinuas.getName(), "IngresoNetoEnOperacionesContinuas");

		Indicator ingresoNetoEnOperacionesDiscontinuadas = indicatorService
				.getIndicator("IngresoNetoEnOperacionesDiscontinuadas");
		assertTrue(ingresoNetoEnOperacionesDiscontinuadas != null);
		assertEquals(ingresoNetoEnOperacionesDiscontinuadas.getName(), "IngresoNetoEnOperacionesDiscontinuadas");

		Indicator ingresoNeto = indicatorService.getIndicator("IngresoNeto");
		assertTrue(ingresoNeto != null);
		assertEquals(ingresoNeto.getName(), "IngresoNeto");
	}
*/
	@Test
	public void createIndicatorWithInclompletedExpressionReturnsSyntacticError() throws IndicatorSyntaxException {
		expectedEx.expect(IndicatorSyntaxException.class);
		expectedEx.expectMessage("Error sintactico en la formula del indicador, en la linea 1, caracter nº: 4");
		IndicatorService indicatorService = IndicatorService.getInstance();
		indicatorService.createIndicator("Indicator1", "1+2+","user");
	}

	@Test
	public void createIndicatorWithWrongCharacterReturnsSyntacticError() throws IndicatorSyntaxException {
		expectedEx.expect(IndicatorSyntaxException.class);
		expectedEx.expectMessage("Caracter no valido en la linea 1, caracter nº: 2");
		IndicatorService indicatorService = IndicatorService.getInstance();
		indicatorService.createIndicator("Indicator1", "1+#+2\r\n","user");
	}
}