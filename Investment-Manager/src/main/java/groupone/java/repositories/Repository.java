package groupone.java.repositories;

import javax.persistence.EntityManager;


public class Repository {
	private Accounts accounts;
	private IndicatorRepository indicators;
	private Companies companies;
	private Metodologies metodologies;
	private UserRepository users;
	private PrecalculatedIndicators precalculated_indicators;
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
	
	public UserRepository users() {
		if (users == null) {
			users = new UserRepository(em);
		}
		return users;
	}
	
	public PrecalculatedIndicators precalculatedIndicators() {
		if (precalculated_indicators == null) {
			precalculated_indicators = new PrecalculatedIndicators(em);
		} 
		return precalculated_indicators;
	}
	
	public void close() {
		em.close();
	}
}