package src.test.java;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import groupone.java.bean.Company;
import groupone.java.repositories.Repository;;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CompanyRepositoryTests {
	private static final String PERSISTENCE_UNIT_NAME = "DDS";
	private EntityManagerFactory emFactory;
	private Repository repository;

	@Before
	public void setUp() throws Exception {
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repository = new Repository(emFactory.createEntityManager());
	}

	@Test
	public void persistIndicator() {
		Company companyToPersist = new Company();
		companyToPersist.setName("CompanyTest1");
		repository.companies().persist(companyToPersist);
		Company persistedCompany = repository.companies().findById(companyToPersist.getId());
		Assert.assertTrue(persistedCompany.getName() == companyToPersist.getName());
	}
	
	@After
	public void tearDown() throws Exception {
		repository.close();
		emFactory.close();
	}
}
