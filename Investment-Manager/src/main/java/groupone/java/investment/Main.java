package groupone.java.investment;

import java.io.IOException;
import java.lang.Exception;

import groupone.java.services.AccountService;

public class Main {

    public static void main(String[] args) throws Exception {
        String path = "/cuentas.json";		
		
		try{
			AccountService.getInstance().loadAccounts(path);
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
		
		AccountService.getInstance().printAccounts();
    }
}
