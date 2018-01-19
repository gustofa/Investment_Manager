package groupone.java.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import groupone.java.bean.Company;

public class Companies extends Repository {
	Companies(EntityManager em) {
		super(em);
	}

	public Company findById(Long id) {
		return em.find(Company.class, id);
	}

	public void persist(Company company) {
		em.getTransaction().begin();
		em.persist(company);
		em.getTransaction().commit();
	}
	
	public void update(Company company) {
		em.getTransaction().begin();
		em.persist(company);
		em.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Company> getCompanies() {
		List<Company> companies = null;
		companies = em.createNamedQuery("getCompanies")
				.getResultList();
		return companies;
		}	
	
	public Company getCompanyByName(String name) {
		Company company = null;
		List<Company> companies = new ArrayList<Company>();
		companies =  em.createQuery("SELECT c FROM Company c WHERE name=:name")
				.setParameter("name", name)
				.getResultList();
			if (companies.size()>0) {
				return companies.get(0);
			} else {
				return company;
			}
		}
	
}