package groupone.java.repositories;

import javax.persistence.EntityManager;


public class Repository {
	private Accounts accounts;
	private Indicators indicators;
	private Companies companies;
	protected EntityManager em;

	public Repository(EntityManager em) {
		this.em = em;
	}

	public Accounts accounts() {
		if (accounts == null) {
			accounts = new Accounts(em);
		}
		return accounts;
	}

	public Indicators indicators() {
		if (indicators == null) {
			indicators = new Indicators(em);
		}
		return indicators;
	}
	
	public Companies companies() {
		if (companies == null) {
			companies = new Companies(em);
		}
		return companies;
	}
	
	public void close() {
		em.close();
	}
}