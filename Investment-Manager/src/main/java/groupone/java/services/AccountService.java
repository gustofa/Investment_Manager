package groupone.java.services;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import groupone.java.app.AccountList;
import groupone.java.app.CompanyList;
import groupone.java.bean.Account;
import groupone.java.bean.Company;
import groupone.java.repositories.Repository;

public class AccountService {
	private static AccountService instance;
	//private List<Account> accountList = new ArrayList<Account>();
	
	private static final String PERSISTENCE_UNIT_NAME = "DDS";
	private Repository repository;
	
	private AccountService(){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		this.repository = new Repository(emFactory.createEntityManager());
	}
	
	public static AccountService getInstance(){
		if(instance == null){
			instance = new AccountService();
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
	}
	
	public void loadAccounts2(String pathArchivo) throws IOException {
		JsonReader reader = new JsonReader(new FileReader(pathArchivo));
		Type companyListType = new TypeToken<List<Company>>() {
		}.getType();
		
		CompanyList.companyList = new Gson().fromJson(reader, companyListType);						
		
		for (int i = 0; i < CompanyList.companyList.size(); i++) {
			Company company = CompanyList.companyList.get(i);
			
			for (int j = 0; j < company.getAccounts().size(); j++) {
				Account a = company.getAccounts().get(j);
				a.setCompany(company);
				AccountList.accountList.add(a);
			}
		}
		
	}

	public Account createAccount(String name, String year, Double value, Company company){
		Account account = new Account(name, year, value);  		
		company.addAccount(account);
		account.setCompany(company);
		this.repository.accounts().merge(account);
		return account;
	}
	
	public Account getAccount(Long id) {
		return repository.accounts().findById(id);
	}	
	
	public List<Account> getAccounts() {
		return  repository.accounts().getAccounts();
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
