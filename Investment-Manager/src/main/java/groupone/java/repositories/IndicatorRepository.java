package groupone.java.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import groupone.java.bean.Account;
import groupone.java.bean.Indicator;

public class IndicatorRepository extends Repository {
	IndicatorRepository(EntityManager em) {
		super(em);
	}

	public Indicator findById(Long id) {
		return em.find(Indicator.class, id);
	}
	
	public Indicator findByName(String name) {
		return em.find(Indicator.class, name);
	}

	@SuppressWarnings("unchecked")
	public List<Indicator> getIndicators() {
		List<Indicator> indicators = null;
		indicators = em.createQuery("SELECT i FROM Indicator i").getResultList();
		return indicators;
	}

	public void persist(Indicator indicator) {
		em.getTransaction().begin();
		em.persist(indicator);
		em.getTransaction().commit();
	}
}