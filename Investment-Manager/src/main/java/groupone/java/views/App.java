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
		 
	        //Instanciamos Servicio para Compañias
	        CompanyService serviceCompany = CompanyService.getInstance();
			//repository.companies().getCompanies();

			//Instanciamos Servicio para Cuentas
			AccountService serviceAccount = AccountService.getInstance();
			//repository.accounts().getAccounts();
			
			//Instanciamos Servicio para Indicadores
	        IndicatorService service = IndicatorService.getInstance();
	        
		 	Spark.staticFileLocation("public");	
		 	Spark.port(9002);
		 	//página base que contendrá a todas las demás
		    String layout = "index.vtl";

		    get("/", (request, response) -> {
		      Map<String, Object> model = new HashMap<String, Object>();
		      model.put("template", "home.vtl" );
		      return new ModelAndView(model, layout);
		    }, new VelocityTemplateEngine());	          
	    	        
		    
		   //Vista: Listado de Cuentas
		    get("/accounts", (request, response) -> {
	        	Map<String, Object> model = new HashMap<String, Object>();
	        	model.put("accounts", serviceAccount.getAccounts());
	        	model.put("template", "Views/Account/accounts.vtl" );
	            return new ModelAndView(model, layout);
	        }, new VelocityTemplateEngine());     
		    
		    //Vista: Crear Cuenta
	        get("/account", (request, response) -> {
	        	Map<String, Object> model = new HashMap<String, Object>();
	        	model.put("companies", serviceCompany.getCompanies());
	        	model.put("template", "Views/Account/account.vtl" );
	            return new ModelAndView(model, layout);
	        }, new VelocityTemplateEngine());     
	        
	        post("/create_account", (request, response) -> {
	        	Map<String, Object> model = new HashMap<String, Object>();

	        	String name = request.queryParams("name");
	        	String year = request.queryParams("year");
	        	Double value = Double.parseDouble(request.queryParams("value"));
	        	Long id = Long.parseLong(request.queryParams("company"));
	        	Company company = serviceCompany.getCompanies().stream()
	    				.filter(a -> a.getId().equals(id) )
	    				.findFirst()
	    				.orElse(null); 
	        	
	        	Account account = serviceAccount.createAccount(name, year, value, company);

	        	model.put("template", "Views/Account/create_ok.vtl" );
	        	model.put("title", "Cuentas - Administraci&oacute;n");
	        	model.put("subtitle", "Creaci&oacute;n de Cuentas");
	        	model.put("account", account);
	            return new ModelAndView(model, layout);
	        }, new VelocityTemplateEngine());   
	        
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
	        	model.put("template", "Views/Indicator/indicators.vtl");
	        	return new ModelAndView(model, layout);
	        }, new VelocityTemplateEngine());

	        
	        get("/json", (request, response) -> {
	            return null; 
	        }, new JSONTransformer());
	        
		    //Vista: Crear Indicador
	        get("/indicator", (request, response) -> {
	        	Map<String, Object> model = new HashMap<String, Object>();
	        	model.put("template", "Views/Indicator/indicators.vtl" );
	            return new ModelAndView(model, layout);
	        }, new VelocityTemplateEngine());    
	    }



}
