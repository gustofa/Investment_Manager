package groupone.java.views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import groupone.java.bean.Account;
import groupone.java.bean.Company;
import groupone.java.bean.Indicator;
import groupone.java.error.IndicatorSyntaxException;
import groupone.java.repositories.Repository;
import groupone.java.services.AccountService;
import groupone.java.services.CompanyService;
import groupone.java.services.IndicatorService;
import spark.ModelAndView;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
	private static final String PERSISTENCE_UNIT_NAME = "DDS";
	private static EntityManagerFactory emFactory;
	
	 public static void main( String[] args ) {

			emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			Repository repository = new Repository(emFactory.createEntityManager());		 
		 
	        //Instancio las compañias
	        CompanyService serviceCompany = CompanyService.getInstance();
	        
		 	Spark.staticFileLocation("public");	
		 	Spark.port(9000);
		 	//página base que contendrá a todas las demás
		    String layout = "index.vtl";

		    get("/", (request, response) -> {
		      Map<String, Object> model = new HashMap<String, Object>();
		      model.put("template", "home.vtl" );
		      return new ModelAndView(model, layout);
		    }, new VelocityTemplateEngine());	          
	    	        
	        get("/account", (request, response) -> {
	        	Map<String, Object> model = new HashMap<String, Object>();
	        	List<Company> companies = serviceCompany.getCompanies();
	        	model.put("companies", companies);
	        	model.put("template", "account.vtl" );
	            return new ModelAndView(model, layout);
	        }, new VelocityTemplateEngine());     
	        
	        post("/create_account", (request, response) -> {
	        	Map<String, Object> model = new HashMap<String, Object>();
	        	//List<Company> companies = serviceCompany.getCompanies();
	        	
	        	String name = request.queryParams("name");
	        	String year = request.queryParams("year");
	        	Double value = Double.parseDouble(request.queryParams("value"));
	        	Company company = repository.companies().findById(Long.parseLong(request.queryParams("company")));       	
	    		Account account = new Account(name, year, value);  		
	    		company.addAccount(account);
	    		account.setCompany(company);
	    		repository.accounts().persist(account);
	    		repository.companies().persist(company);

	        	model.put("template", "create_ok.vtl" );
	        	model.put("title", "Cuentas - Administraci&oacute;n");
	        	model.put("subtitle", "Creaci&oacute;n de Cuentas");
	            return new ModelAndView(model, layout);
	        }, new VelocityTemplateEngine());   
	        
	        IndicatorService service = IndicatorService.getInstance();
	    	try {
				service.createIndicator("pepito", "1+2");
				service.createIndicator("mengano", "1+2");
				service.createIndicator("fulano", "1+2");
				
	    	
	    	} catch (IndicatorSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
	        get("/indicators", (request, response) -> {
	        	
	        	 Map<String, Object> model = new HashMap<String, Object>();
	        	List<Indicator> indicators = service.getIndicators();
	        	
	        	model.put("indicators", indicators);
	            // The wm files are located under the resources directory
	            return new ModelAndView(model, "indicators.vm");
	        }, new VelocityTemplateEngine());

	        
	        get("/json", (request, response) -> {
	            return null; 
	        }, new JSONTransformer());
	    }



}
