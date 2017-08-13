package groupone.java.investment;

import java.io.IOException;
import java.lang.Exception;

import groupone.java.manager.AccountManager;

public class Main {

    public static void main(String[] args) throws Exception {
        String path = "/cuentas.json";		
		
		try{
			AccountManager.getInstance().loadAccounts(path);
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
		
		AccountManager.getInstance().printAccounts();
    }
}
