package groupone.java.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;

import groupone.java.bean.Account;
import groupone.java.bean.Company;;

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
	
	public void merge(Account account) {
		em.getTransaction().begin();
		em.merge(account);
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
	
	public Account getAccountByName(String name, String year, long co_id) {
		Account account = null;
		List<Account> accounts = new ArrayList<Account>();
		accounts = em.createQuery("SELECT a FROM Account a WHERE name=:name and year=:year and company_id=:company_id")
				.setParameter("name", name)
				.setParameter("year", year)
				.setParameter("company_id", co_id)
				.getResultList();
				if (accounts.size()>0) {
					return accounts.get(0);
				} else {
					return account;
				}
		}
	
	public void updateAccount(Account account) throws HibernateException{	
		em.getTransaction().begin();
		em.createQuery("UPDATE Account SET value=:value WHERE name=:name and year=:year and company_id=:company_id")
				.setParameter("value", account.getValue())
				.setParameter("name", account.getName())
				.setParameter("year", account.getYear())
				.setParameter("company_id", account.getCompany().getId())
				.executeUpdate();
		em.getTransaction().commit();
		
		}
	
}