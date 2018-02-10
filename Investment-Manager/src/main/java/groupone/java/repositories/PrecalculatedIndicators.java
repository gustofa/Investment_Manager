package groupone.java.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;

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
	
	public PrecalculatedIndicator getPrecalculatedIndicator(long ind_id, String year, long comp_id) { 
		PrecalculatedIndicator precalculatedIndicator = null;
		List<PrecalculatedIndicator> precalculatedIndicators = new ArrayList<PrecalculatedIndicator>();
		precalculatedIndicators = em.createQuery("SELECT a FROM PrecalculatedIndicator a WHERE indicator_id=:indicator_id and year=:year and company_id=:company_id")
				.setParameter("indicator_id", ind_id)
				.setParameter("year", year)
				.setParameter("company_id", comp_id)
				.getResultList();
				if (precalculatedIndicators.size()>0) {
					return precalculatedIndicators.get(0);
				} else {
					return precalculatedIndicator;
				}
		
		}
	
	public void updatePrecalculatedIndicator(PrecalculatedIndicator precalculatedIndicator) throws HibernateException{	
		em.getTransaction().begin();
		em.createQuery("UPDATE PrecalculatedIndicator SET value=:value WHERE name=:name and year=:year and company_id=:company_id and indicator_id=:indicator_id")
				.setParameter("value", precalculatedIndicator.getValue())
				.setParameter("name", precalculatedIndicator.getName())
				.setParameter("year", precalculatedIndicator.getYear())
				.setParameter("company_id", precalculatedIndicator.getCompanyId())
				.setParameter("indicator_id", precalculatedIndicator.getIndicatorId())
				.executeUpdate();
		em.getTransaction().commit();
		
		}
}