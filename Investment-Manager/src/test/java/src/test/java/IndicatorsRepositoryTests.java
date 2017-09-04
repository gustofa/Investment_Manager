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
import groupone.java.repositories.Repository;;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IndicatorsRepositoryTests {
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
		Indicator indicatorToPersist = new Indicator();
		indicatorToPersist.setName("IndicadorUno");
		indicatorToPersist.setExpression("1+3");
		repository.indicators().persist(indicatorToPersist);
		Indicator persistedIndicator = repository.indicators().findById(1L);
		Assert.assertTrue(persistedIndicator.getName() == indicatorToPersist.getName());
		Assert.assertTrue(persistedIndicator.getExpression() == indicatorToPersist.getExpression());
	}
	
	@After
	public void tearDown() throws Exception {
		repository.close();
		emFactory.close();
	}
}
