package groupone.java.account;

import java.io.IOException;

public class LoadAccountsApp {

	public static void main(String[] args) {
		String path = "/cuentas.json";
		
		AccountManager accountLoader = new AccountManager();
		
		try{
			accountLoader.agregarCuentas(path);
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
		
		accountLoader.imprimirCuentas();
	}
}
