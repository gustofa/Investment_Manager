package src.test.java;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import groupone.java.bean.Account;
import groupone.java.bean.Company;
import groupone.java.repositories.Repository;;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersistanceTest {
	private static final String PERSISTENCE_UNIT_NAME = "DDS";
	private EntityManagerFactory emFactory;
	private Repository repository;

	@Before
	public void setUp() throws Exception {
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repository = new Repository(emFactory.createEntityManager());
	}

	@Test
	public void aPersistir() {
		Company company = new Company();
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
		List<Account> cuentas = repository.accounts().getAccountbyNameCo("unaCuenta2",2);
		for (Account cuenta : cuentas) {
			System.out.println(cuenta.toString());
			System.out.println("Año: " + cuenta.getYear());
			System.out.println("Valor: " + cuenta.getValue().toString());
			
		}
	}	
	
	/*
	@Test
	public void buscarComunaPorId() {
		Comuna comuna = repositorio.comunas().buscarPorId(1L);
		System.out.println("Comuna encontrada por ID: " + comuna.getNombre());
	}

	@Test
	public void buscarPoiPorId() {
	Poi poi = repositorio.pois().buscarPorId(2L);
	System.out.println("Poi encontrado por ID: " + poi.getNombre());
	}


*/
	@After
	public void tearDown() throws Exception {
		repository.close();
		emFactory.close();
	}
}
