package groupone.java.repositories;

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
}