package groupone.java.investment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccountList {

	public static List<Account> listaCuentas = new ArrayList<Account>();
	public static HashMap<String,Account> MapCuentas = new HashMap<String,Account>();
	
	public static Account findAccount(String nombre,String empresa, String anio) {
		return MapCuentas.get(nombre+empresa+anio);

	}		
}
