package groupone.java.services;
import java.util.Collection;
import java.util.HashMap;

import groupone.java.bean.Account;
import groupone.java.bean.Company;

public class CompanyService {
	
	private static CompanyService instance;
	private HashMap<String, Company> companies = new HashMap<String, Company>();
	
	public static CompanyService getInstance() {
		if (instance == null) {
			instance = new CompanyService();
		}

		return instance;
	}
	
	public Account getAccount(String name, String year, Company company){	
		return company.getAccounts().stream()
				.filter(a ->  a.getName().equals(name) && a.getYear().equals(year))
				.findFirst()
				.orElse(null);	
	}
	
	public Collection<Company> getCompanies(){
		return this.companies.values();
	}

}
