package groupone.java.manager;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import groupone.java.bean.Account;
import groupone.java.investment.AccountList;

public class AccountManager {
	public void loadAccounts(String pathArchivo) throws IOException {
		JsonReader reader = new JsonReader(new FileReader(pathArchivo));
		Type accountListType = new TypeToken<List<Account>>() {
		}.getType();
		
		AccountList.accountList = new Gson().fromJson(reader, accountListType);

		for (Account account : AccountList.accountList) {
			AccountList.MapCuentas.put(account.getName() + account.getCompany() + account.getAnio(), account);
		}
	}

	public List<Account> getAccounts() {
		return AccountList.accountList;
	}

	public void printAccounts() {
		for (Account cuenta : AccountList.accountList) {
			this.printAccount(cuenta);
		}
	}

	private void printAccount(Account account) {
		System.out.println(" Empresa: " + account.getCompany() + " /  Cuenta: " + account.getName() + " /  Año: "
				+ account.getAnio() + " /  Valor (en millones de US$): " + account.getValue());
	}
}
