package groupone.java.repositories;

import javax.persistence.EntityManager;


public class Repository {
	private Accounts account;
	protected EntityManager em;

	public Repository(EntityManager em) {
		this.em = em;
	}

	public Accounts accounts() {
		if (account == null) {
			account = new Accounts(em);
		}
		return account;
	}

	public void close() {
		em.close();
	}
}