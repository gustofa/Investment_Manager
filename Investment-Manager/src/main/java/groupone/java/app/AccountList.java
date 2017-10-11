package groupone.java.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import groupone.java.bean.Account;

public class AccountList {

	public static List<Account> accountList = new ArrayList<Account>();
	public static HashMap<String,Account> MapCuentas = new HashMap<String,Account>();
	
	public static Account findAccount(String nombre,String empresa, String anio) {
		return MapCuentas.get(nombre+empresa+anio);
	}		
}
