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
		Indicator indicator = (Indicator) em.createQuery("SELECT i FROM Indicator i WHERE name=:name")
								.setParameter("name", name)
								.getSingleResult();
		return indicator;
	}

	@SuppressWarnings("unchecked")
	public List<Indicator> getIndicators(long userId) {
		List<Indicator> indicators = null;
		indicators = em.createQuery("SELECT i FROM Indicator i WHERE user_id=:user_id")
						.setParameter("user_id", userId)
						.getResultList();
		return indicators;
	}

	public void persist(Indicator indicator) {
		em.getTransaction().begin();
		em.persist(indicator);
		em.getTransaction().commit();
	}
}