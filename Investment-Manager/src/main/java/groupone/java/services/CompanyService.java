package groupone.java.services;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import groupone.java.bean.Account;
import groupone.java.bean.Company;
import groupone.java.bean.Indicator;

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
	
	public List<Company> getCompanies(){
		return  new ArrayList<Company>(this.companies.values());
	}

}
