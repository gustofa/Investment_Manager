package src.test.java;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import groupone.java.bean.Account;
import groupone.java.services.AccountService;

public class AccountServiceTests {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Test
	public void agregarCuentasShouldKeepAccountsInMemory() throws IOException{
	    File file = folder.newFile("test.json");
     
        String content = "[{"
             + "\"name\": \"Facebook\","
             + "\"accountList\":[{"
			             + "\"name\": \"EBITDA\","
			             + "\"year\": \"2016\","
			             + "\"value\": 14870}] }]";
        
        FileUtils.writeStringToFile(file , content);  
 		
        AccountService.getInstance().loadAccounts(file.getPath());
		
//		assertTrue(accountManager.getAccounts().size() == 1);
		Account insertedAccount = AccountService.getInstance().getAccounts().get(0);

		assertEquals(insertedAccount.getName(), "EBITDA");
		assertEquals(insertedAccount.getYear(), "2016");
		assertTrue(insertedAccount.getValue() == 14870);
	}
	
	@Test(expected = IOException.class)
	public void agregarCuentasThrowsInputOutputExceptionIfFileDoesNotExist() throws IOException{

		AccountService.getInstance().loadAccounts("FooFolder/FooFile.Foo");
	}
}
