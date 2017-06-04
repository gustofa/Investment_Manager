package src.test.java;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import groupone.java.investment.Indicator;
import groupone.java.investment.IndicatorManager;

public class IndicatorManagerTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Test
	public void loadPredefinedIndicatorsShouldKeepIndicatorsInMemory() throws IOException{
		IndicatorManager indicatorManager = IndicatorManager.getInstance();
		indicatorManager.loadPredefinedIndicators();
		
		Indicator ingresoNetoEnOperacionesContinuas = indicatorManager.getIndicator("IngresoNetoEnOperacionesContinuas");
		assertTrue(ingresoNetoEnOperacionesContinuas != null);
		assertEquals(ingresoNetoEnOperacionesContinuas.getName(),"IngresoNetoEnOperacionesContinuas");
		assertEquals(ingresoNetoEnOperacionesContinuas.getExpression(),"500000*2\r\n");

		Indicator ingresoNetoEnOperacionesDiscontinuadas = indicatorManager.getIndicator("IngresoNetoEnOperacionesDiscontinuadas");
		assertTrue(ingresoNetoEnOperacionesDiscontinuadas != null);
		assertEquals(ingresoNetoEnOperacionesDiscontinuadas.getName(),"IngresoNetoEnOperacionesDiscontinuadas");
		assertEquals(ingresoNetoEnOperacionesDiscontinuadas.getExpression(),"200000/2\r\n");
		
		Indicator ingresoNeto = indicatorManager.getIndicator("IngresoNeto");		
		assertTrue(ingresoNeto != null);
		assertEquals(ingresoNeto.getName(),"IngresoNeto");
		assertEquals(ingresoNeto.getExpression(),"IngresoNetoEnOperacionesContinuas+IngresoNetoEnOperacionesDiscontinuadas\r\n");
	}		
}
