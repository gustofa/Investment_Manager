package groupone.java.manager;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import groupone.java.bean.Account;
import groupone.java.bean.Company;
import groupone.java.investment.AccountList;
import groupone.java.investment.CompanyList;

public class AccountManager {
	private static AccountManager instance;
	private List<Account> accountList = new ArrayList<Account>();
	
	private AccountManager(){
		
	}
	
	public static AccountManager getInstance(){
		if(instance == null){
			instance = new AccountManager();
		}
			
		return instance;
	}
	
	public void loadAccounts(String pathArchivo) throws IOException {
		JsonReader reader = new JsonReader(new FileReader(pathArchivo));
		Type companyListType = new TypeToken<List<Company>>() {
		}.getType();
		
		CompanyList.companyList = new Gson().fromJson(reader, companyListType);
		
		AccountList.accountList = CompanyList.companyList.stream()
											  .map(c -> c.getAccounts())
											  .flatMap(l -> l.stream())
											  .collect(Collectors.toList());
						
		
		/*for (int i = 0; i < CompanyList.companyList.size(); i++) {
			Company company = CompanyList.companyList.get(i);
			
			for (int j = 0; j < company.getAccounts().size(); j++) {
				Account a = company.getAccounts().get(j);
				CompanyList.MapCompanias.put(a.getName() + company.getName() + a.getYear(), company);
			}.
		}*/
		
	}

	public List<Account> getAccounts() {
		return AccountList.accountList;
	}

	public void printAccounts() {
		for (Account account : AccountList.accountList) {
			this.printAccount(account);
		}
	}

	private void printAccount(Account account) {
		System.out.println(" Empresa: "  + " /  Cuenta: " + account.getName() + " /  Año: "
				+ account.getYear() + " /  Valor (en millones de US$): " + account.getValue());
	}
}
