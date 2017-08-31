package groupone.java.repositories;

import javax.persistence.EntityManager;

import groupone.java.bean.Indicator;

public class Indicators extends Repository {
	Indicators(EntityManager em) {
		super(em);
	}

	public Indicator findById(Long id) {
		return em.find(Indicator.class, id);
	}

	public void persist(Indicator indicator) {
		em.getTransaction().begin();
		em.persist(indicator);
		em.getTransaction().commit();
	}
}