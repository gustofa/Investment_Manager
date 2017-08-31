package src.test.java;

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
		Account account = new Account(null, null, null);
		account.setName("unaCuenta");
		repository.accounts().persist(account);
		
		Company company = new Company();
		company.setName("unaCompania");
		company.addAccount(account);
		repository.companies().persist(company);
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

	@Test
	public void buscarPoiPorNombre() {
		List<Poi> pois = repositorio.pois().buscarPoiPorNombre("Alm");
		for (Poi poi : pois) {
			System.out.println(poi.getComuna().getNombre());
			System.out.println(poi.getNombre());
			System.out.println(poi.getGeoref().getLatitud());
			
		}
	}
*/
	@After
	public void tearDown() throws Exception {
		repository.close();
		emFactory.close();
	}
}
