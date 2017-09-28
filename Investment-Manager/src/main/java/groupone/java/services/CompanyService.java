package groupone.java.services;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import groupone.java.bean.Account;
import groupone.java.bean.Company;
import groupone.java.bean.Indicator;
import groupone.java.repositories.Repository;

public class CompanyService {
	
	private static CompanyService instance;
	//private HashMap<String, Company> companies = new HashMap<String, Company>();
	
	private static final String PERSISTENCE_UNIT_NAME = "DDS";
	private Repository repository;
	
	private CompanyService() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		this.repository = new Repository(emFactory.createEntityManager());
	}
	
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
		return  repository.companies().getCompanies();
	}

	public Company getCompany(Long id) {
		return repository.companies().findById(id);
	}	

}
