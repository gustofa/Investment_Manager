package src.test.java;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import groupone.java.bean.Indicator;
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
		Metodology metodologyToPersist = new Metodology();
		metodologyToPersist.setName("MetodologyTest");
		metodologyToPersist.setExpression("1+5");
		metodologyToPersist.setUserId(1);
		repository.metodologies().persist(metodologyToPersist);
		Metodology persistedMetodology = repository.metodologies().findById(metodologyToPersist.getId());
		Assert.assertTrue(persistedMetodology.getName() == metodologyToPersist.getName());
	}

	@After
	public void tearDown() throws Exception {
		repository.close();
		emFactory.close();
	}
}
