package groupone.java.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import groupone.java.bean.Account;
import groupone.java.bean.Indicator;
import groupone.java.bean.PrecalculatedIndicator;

public class PrecalculatedIndicators extends Repository {
	PrecalculatedIndicators(EntityManager em) {
		super(em);
	}

	public PrecalculatedIndicator findById(Long id) {
		return em.find(PrecalculatedIndicator.class, id);
	}
	

	public void persist(PrecalculatedIndicator precalculatedIndicator) {
		em.getTransaction().begin();
		em.persist(precalculatedIndicator);
		em.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<PrecalculatedIndicator> getPrecalculatedIndicators() {
		List<PrecalculatedIndicator> precalculatedIndicator = null;
		precalculatedIndicator = em.createNamedQuery("getPrecalculatedIndicators")
				.getResultList();
		return precalculatedIndicator;
		}
}