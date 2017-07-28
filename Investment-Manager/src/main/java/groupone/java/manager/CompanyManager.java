package groupone.java.manager;

import java.util.Collection;
import java.util.HashMap;

import groupone.java.bean.Company;

public class CompanyManager {
	private static CompanyManager instance;
	private HashMap<String, Company> companies = new HashMap<String, Company>();

	private CompanyManager() {
	};

	public static CompanyManager getInstance() {
		if (instance == null) {
			instance = new CompanyManager();
		}

		return instance;
	}
	
	public Collection<Company> getCompanies(){
		return this.companies.values();
	}
}
