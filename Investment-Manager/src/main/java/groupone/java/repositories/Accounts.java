package groupone.java.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import groupone.java.bean.Account;;

public class Accounts extends Repository {
	Accounts(EntityManager em) {
		super(em);
	}

	public Account findById(Long id) {
		return em.find(Account.class, id);
	}

	public void persist(Account account) {
		em.getTransaction().begin();
		em.persist(account);
		em.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> getAccountbyNameCo(String name, long co_id) {
		List<Account> accounts = null;
		accounts = em.createNamedQuery("getAccountbyNameCo")
				.setParameter("pname", "%" + name + "%")
				.setParameter("pco_id", co_id)
				.getResultList();
		return accounts;
		}
	
	@SuppressWarnings("unchecked")
	public List<Account> getAccounts() {
		List<Account> accounts = null;
		accounts = em.createNamedQuery("getAccounts")
				.getResultList();
		return accounts;
		}
	
}