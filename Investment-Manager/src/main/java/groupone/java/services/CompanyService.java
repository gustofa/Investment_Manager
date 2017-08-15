package groupone.java.services;
import groupone.java.bean.Account;
import groupone.java.bean.Company;

public class CompanyService {
	
	public Account getAccount(String name, String year, Company company){	
		return company.getAccounts().stream()
				.filter(a ->  a.getName().equals(name) && a.getYear().equals(year))
				.findFirst()
				.orElse(null);	
	}

}
