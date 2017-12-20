package groupone.java.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;

import groupone.java.bean.Account;
import groupone.java.bean.Company;
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

	
	@SuppressWarnings("unchecked")
	public List<Indicator> getAllIndicators() {
		List<Indicator> allIndicators = null;
		allIndicators =  em.createQuery("SELECT i FROM Indicator i")
				.getResultList();
		return allIndicators;
		}	
	
	public void persist(Indicator indicator) {
		em.getTransaction().begin();
		em.persist(indicator);
		em.getTransaction().commit();
	}
	
	public void updateIndicatorExpression(Indicator indicator) throws HibernateException{	
		em.getTransaction().begin();
		em.createQuery("UPDATE Indicator SET expression=:expression WHERE name=:name and id=:id")
				.setParameter("expression", indicator.getExpression())
				.setParameter("name", indicator.getName())
				.setParameter("id", indicator.getId())
				.executeUpdate();
		em.getTransaction().commit();
		
		}
}