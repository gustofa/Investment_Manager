package src.test.java;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import groupone.java.investment.Indicator;
import groupone.java.investment.IndicatorList;
import groupone.java.investment.IndicatorManager;



public class IndicatorManagerTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Test
	public void agregarCuentasShouldKeepAccountsInMemory() throws IOException{
	    File file = folder.newFile("test.json");
     
        String content = "[{"
             + "\"name\": \"IngresoNetoEnOperacionesContinuas\","
             + "\"expression\": \"500000*2\"},"
             + "{\"name\": \"IngresoNetoEnOperacionesDiscontinuadas\","
             + "\"expression\": \"200000/2\"},"
             + "{\"name\": \"IngresoNeto\","
             + "\"expression\": \"IngresoNetoEnOperacionesContinuas+IngresoNetoEnOperacionesDiscontinuadas\"}"
             + "]";
        
        FileUtils.writeStringToFile(file , content);  
 
        IndicatorManager indicatorManager = new IndicatorManager();
		indicatorManager.addIndicators(file.getPath());
		Indicator insertedIndicator = IndicatorList.indicatorsList.get(0);
		assertEquals(insertedIndicator.getName(), "IngresoNetoEnOperacionesContinuas");
		assertEquals(insertedIndicator.getExpression(), "500000*2");
		//assertEquals(IndicatorList.listaIndicadores.size(),3);
	}
	
	@Test(expected = IOException.class)
	public void agregarIndicadoresThrowsInputOutputExceptionIfFileDoesNotExist() throws IOException{
		IndicatorManager indicatorManager = new IndicatorManager();
		indicatorManager.addIndicators("FooFolder/FooFile.Foo");
	}
}
