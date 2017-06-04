package src.test.java;

import static org.junit.Assert.*;
import java.io.IOException;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import groupone.java.investment.Indicator;
import groupone.java.investment.IndicatorManager;
import groupone.java.investment.IndicatorSyntaxException;

public class IndicatorManagerTest {
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void loadPredefinedIndicatorsShouldKeepIndicatorsInMemory() throws IOException, IndicatorSyntaxException{
		IndicatorManager indicatorManager = IndicatorManager.getInstance();
		indicatorManager.loadPredefinedIndicators();
		
		Indicator ingresoNetoEnOperacionesContinuas = indicatorManager.getIndicator("IngresoNetoEnOperacionesContinuas");
		assertTrue(ingresoNetoEnOperacionesContinuas != null);
		assertEquals(ingresoNetoEnOperacionesContinuas.getName(),"IngresoNetoEnOperacionesContinuas");

		Indicator ingresoNetoEnOperacionesDiscontinuadas = indicatorManager.getIndicator("IngresoNetoEnOperacionesDiscontinuadas");
		assertTrue(ingresoNetoEnOperacionesDiscontinuadas != null);
		assertEquals(ingresoNetoEnOperacionesDiscontinuadas.getName(),"IngresoNetoEnOperacionesDiscontinuadas");
		
		Indicator ingresoNeto = indicatorManager.getIndicator("IngresoNeto");		
		assertTrue(ingresoNeto != null);
		assertEquals(ingresoNeto.getName(),"IngresoNeto");
	}
	
	@Test
	public void parseExpression() throws IndicatorSyntaxException {
		expectedEx.expect(IndicatorSyntaxException.class);
		expectedEx.expectMessage("Error sintactico en la formula del indicador, en la linea 1, caracter nº: 4");
		
		IndicatorManager indicatorManager = IndicatorManager.getInstance();
		indicatorManager.parseExpression("1+3+");	
	}
}
