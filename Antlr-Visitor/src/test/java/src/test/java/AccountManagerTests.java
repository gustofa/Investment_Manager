package src.test.java;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import groupone.java.investment.Account;
import groupone.java.investment.AccountManager;

public class AccountManagerTests {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Test
	public void agregarCuentasShouldKeepAccountsInMemory() throws IOException{
	    File file = folder.newFile("test.json");
     
        String content = "[{"
             + "\"empresa\": \"Facebook\","
             + "\"nombre\": \"EBITDA\","
             + "\"anio\": \"2016\","
             + "\"valor\": 14870}]";
        
        FileUtils.writeStringToFile(file , content);  
 
		AccountManager accountManager = new AccountManager();
		accountManager.agregarCuentas(file.getPath());
		
		assertTrue(accountManager.getCuentas().size() == 1);
		Account insertedAccount = accountManager.getCuentas().get(0);
		assertEquals(insertedAccount.getEmpresa(), "Facebook");
		assertEquals(insertedAccount.getNombre(), "EBITDA");
		assertEquals(insertedAccount.getAnio(), "2016");
		assertTrue(insertedAccount.getValor() == 14870);
	}
	
	@Test(expected = IOException.class)
	public void agregarCuentasThrowsInputOutputExceptionIfFileDoesNotExist() throws IOException{
		AccountManager accountManager = new AccountManager();
		accountManager.agregarCuentas("FooFolder/FooFile.Foo");
	}
}
