package groupone.java.repositories;

import javax.persistence.EntityManager;

import groupone.java.bean.Account;;

public class Accounts extends Repository {
	Accounts(EntityManager em) {
		super(em);
	}

	public Account buscarPorId(Long id) {
		return em.find(Account.class, id);
	}

	public void persistir(Account account) {
		em.getTransaction().begin();
		em.persist(account);
		em.getTransaction().commit();
	}
}