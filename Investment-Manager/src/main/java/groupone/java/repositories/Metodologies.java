package groupone.java.repositories;

import javax.persistence.EntityManager;

import groupone.java.bean.Metodology;

public class Metodologies extends Repository{
	Metodologies(EntityManager em) {
		super(em);
	}

	public Metodology findById(Long id) {
		return em.find(Metodology.class, id);
	}

	public void persist(Metodology metodology) {
		em.getTransaction().begin();
		em.persist(metodology);
		em.getTransaction().commit();
	}
}