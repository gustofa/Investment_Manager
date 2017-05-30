package groupone.test.account;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import groupone.java.indicator.Indicator;
import groupone.java.indicator.IndicatorManager;



public class IndicatorManagerTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Test
	public void agregarCuentasShouldKeepAccountsInMemory() throws IOException{
	    File file = folder.newFile("test.json");
     
        String content = "[{"
             + "\"name\": \"IngresoNetoEnOperacionesContinuas\","
             + "\"expression\": \"800000*2\"},"
             + "{\"name\": \"IngresoNetoEnOperacionesDiscontinuadas\","
             + "\"expression\": \"200000/2\"},"
             + "{\"name\": \"IngresoNeto\","
             + "\"expression\": \"IngresoNetoEnOperacionesContinuas+IngresoNetoEnOperacionesDiscontinuadas\"}"
             + "]";
        
        FileUtils.writeStringToFile(file , content);  
 
        IndicatorManager indicatorManager = new IndicatorManager();
		indicatorManager.agregarIndicadores(file.getPath());
		
		assertTrue(indicatorManager.getIndicadores().size() == 1);
		Indicator insertedIndicator = indicatorManager.getIndicadores().get(2);
		assertEquals(insertedIndicator.getName(), "IngresoNeto");
		assertEquals(insertedIndicator.getExpression(), "IngresoNetoEnOperacionesContinuas+IngresoNetoEnOperacionesDiscontinuadas");
	}
	
	@Test(expected = IOException.class)
	public void agregarIndicadoresThrowsInputOutputExceptionIfFileDoesNotExist() throws IOException{
		IndicatorManager indicatorManager = new IndicatorManager();
		indicatorManager.agregarIndicadores("FooFolder/FooFile.Foo");
	}
}
