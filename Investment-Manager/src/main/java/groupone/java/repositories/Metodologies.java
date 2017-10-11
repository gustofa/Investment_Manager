package groupone.java.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import groupone.java.bean.Account;
import groupone.java.bean.Metodology;

public class Metodologies extends Repository{
	Metodologies(EntityManager em) {
		super(em);
	}

	public Metodology findById(Long id) {
		return em.find(Metodology.class, id);
	}


	
	@SuppressWarnings("unchecked")
	public List<Metodology> getMetodologies(long userId) {
		List<Metodology> metodologies = null;
		metodologies = em.createNamedQuery("getMetodologies")
				.setParameter("user_id", userId)
				.getResultList();
		return metodologies;
		}
	
	public void persist(Metodology metodology) {
		em.getTransaction().begin();
		em.persist(metodology);
		em.getTransaction().commit();
	}
}