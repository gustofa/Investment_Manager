package src.test.java;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import groupone.java.bean.Company;
import groupone.java.bean.Indicator;
import groupone.java.bean.PrecalculatedIndicator;
import groupone.java.repositories.Repository;
import groupone.java.services.IndicatorService;
import groupone.java.services.CompanyService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrecalculatedIndicatorsRepositoryTests {
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
		PrecalculatedIndicator precalculatedIndicatorToPersist = new PrecalculatedIndicator();
		precalculatedIndicatorToPersist.setYear("2017");
		precalculatedIndicatorToPersist.setCompanyId(15);
		precalculatedIndicatorToPersist.setIndicatorId(20);
		repository.precalculatedIndicators().persist(precalculatedIndicatorToPersist);
		PrecalculatedIndicator persistedPrecalculatedIndicator = repository.precalculatedIndicators().findById(precalculatedIndicatorToPersist.getId());
		Assert.assertTrue(persistedPrecalculatedIndicator.getYear() == precalculatedIndicatorToPersist.getYear());
		Assert.assertTrue(persistedPrecalculatedIndicator.getCompanyId() == precalculatedIndicatorToPersist.getCompanyId());
		
		CompanyService companyService = CompanyService.getInstance();
		List<Company> companies = companyService.getCompanies();
		
		IndicatorService indicatorService = IndicatorService.getInstance();
		List<Indicator> indicators = indicatorService.getAllIndicators();
		
		//System.out.println(indicators);
		//System.out.println(companies);
		
		
		for (int i=0; i<indicators.size(); i++){
			//System.out.print(indicators.get(i));
			//System.out.print((indicators.get(i)).getId());
			
			for (int j=0; j<companies.size(); j++){
				//System.out.print((companies.get(i)).getId());
				
				System.out.print(indicatorService.apply(companies.get(j), "2016", indicators.get(i)));
			}
		}
	
		
		assertNotNull(indicators);
	}
	
	@After
	public void tearDown() throws Exception {
		repository.close();
		emFactory.close();
	}
}
