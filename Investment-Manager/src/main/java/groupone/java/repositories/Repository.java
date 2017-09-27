package groupone.java.repositories;

import javax.persistence.EntityManager;


public class Repository {
	private Accounts accounts;
	private IndicatorRepository indicators;
	private Companies companies;
	private Metodologies metodologies;
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

	public IndicatorRepository indicators() {
		if (indicators == null) {
			indicators = new IndicatorRepository(em);
		}
		return indicators;
	}
	
	public Companies companies() {
		if (companies == null) {
			companies = new Companies(em);
		}
		return companies;
	}
	
	public Metodologies metodologies() {
		if (metodologies == null) {
			metodologies = new Metodologies(em);
		}
		return metodologies;
	}
	
	public void close() {
		em.close();
	}
}