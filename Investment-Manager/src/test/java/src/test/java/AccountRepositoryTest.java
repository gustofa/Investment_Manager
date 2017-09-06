package src.test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runners.MethodSorters;
import groupone.java.bean.Account;
import groupone.java.bean.Company;
import groupone.java.investment.AccountList;
import groupone.java.investment.CompanyList;
import groupone.java.repositories.Repository;
import groupone.java.services.AccountService;
import groupone.java.services.CompanyService;
import groupone.java.services.IndicatorService;;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountRepositoryTest {
	private static final String PERSISTENCE_UNIT_NAME = "DDS";
	private EntityManagerFactory emFactory;
	private Repository repository;
	private Company company;
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() throws Exception {
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repository = new Repository(emFactory.createEntityManager());
	}

	@Before
	public void persistAccountAndCompany() {
		company = new Company();
		company.setName("unaCompania2");
		
		Account account1 = new Account(null, "2016", 1000.00);
		account1.setName("unaCuenta1");

		Account account2 = new Account(null, "2015", 2500.00);
		account2.setName("unaCuenta2");
		
		company.addAccount(account1);
		company.addAccount(account2);
		account1.setCompany(company);
		account2.setCompany(company);
		repository.accounts().persist(account1);
		repository.accounts().persist(account2);
		repository.companies().persist(company);
	}

	@Test
	public void buscarCuentaPorNombreyCompania() throws IOException{
		
		System.out.println("Company ID : " + company.getId().toString()); 
		System.out.println("Company Name : " + company.getName()); 
		
		List<Account> cuentas = repository.accounts().getAccountbyNameCo("unaCuenta2",company.getId());

	    assertNotNull(cuentas);
	    assertEquals(1, cuentas.size());
		assertEquals(cuentas.get(0).getName(), "unaCuenta2");
		
	}	
	

	@After
	public void tearDown() throws Exception {
		repository.close();
		emFactory.close();
	}
}
