package src.test.java;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Calendar;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
		
		
		CompanyService companyService = CompanyService.getInstance();
		List<Company> companies = companyService.getCompanies();
		
		IndicatorService indicatorService = IndicatorService.getInstance();
		List<Indicator> indicators = indicatorService.getAllIndicators();
		
		//System.out.println(indicators);
		//System.out.println(companies);

		int current_year = Calendar.getInstance().get(Calendar.YEAR);
		
		for (int i=0; i<indicators.size(); i++){
			
			
			for (int j=0; j<companies.size(); j++){
				
				
				for (int y=2014; y<=current_year; y++){
					String year = Integer.toString(y);
					
					PrecalculatedIndicator precalculatedIndicatorToPersist = new PrecalculatedIndicator();
								
					
					
					try {
				         
						PrecalculatedIndicator precalc = repository.precalculatedIndicators().getPrecalculatedIndicator((indicators.get(i)).getId(), year, (companies.get(j)).getId());					 
							
							precalc.setValue(150);
							repository.precalculatedIndicators().updatePrecalculatedIndicator(precalculatedIndicatorToPersist);
						}
				      catch (NoResultException e) { 
				         /* This block will only execute if any Arithmetic exception 
				          * occurs in try block
				          */
				         System.out.println("You should not divide a number by zero");
				         precalculatedIndicatorToPersist.setYear(year);
							precalculatedIndicatorToPersist.setCompanyId((companies.get(j)).getId());
							precalculatedIndicatorToPersist.setIndicatorId((indicators.get(i)).getId());
							precalculatedIndicatorToPersist.setName((indicators.get(i)).getName());
							precalculatedIndicatorToPersist.setValue(indicatorService.apply(companies.get(j), year, indicators.get(i)));
							repository.precalculatedIndicators().persist(precalculatedIndicatorToPersist);
				      }
					
					}
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
