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
import groupone.java.bean.Metodology;
import groupone.java.repositories.Repository;;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MetodologiesRepositoryTest {
	private static final String PERSISTENCE_UNIT_NAME = "DDS";
	private EntityManagerFactory emFactory;
	private Repository repository;

	@Before
	public void setUp() throws Exception {
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repository = new Repository(emFactory.createEntityManager());
	}

	@Test
	public void persistMetodology() {
		Metodology metodology = new Metodology();
		metodology.setName("MetodologyTest");
		repository.metodologies().persist(metodology);
	}


	@After
	public void tearDown() throws Exception {
		repository.close();
		emFactory.close();
	}
}
