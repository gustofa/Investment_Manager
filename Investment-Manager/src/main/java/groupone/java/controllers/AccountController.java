package groupone.java.controllers;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import groupone.java.bean.Account;
import groupone.java.bean.Company;
import groupone.java.services.AccountService;
import groupone.java.services.CompanyService;
import spark.ModelAndView;
import spark.TemplateViewRoute;

public class AccountController {
	private static String layout = "index.vtl";
	
	   //Vista: Listado de Cuentas
	public static TemplateViewRoute serveAccountsPage = (request, response) -> {
		LoginController.ensureUserIsLoggedIn(request, response);
		Boolean addConfirmed = Boolean.parseBoolean(request.queryParams("confirmed"));
    	Map<String, Object> model = new HashMap<String, Object>();
    	AccountService serviceAccount = AccountService.getInstance();
    	model.put("accounts", serviceAccount.getAccounts());
    	model.put("addConfirmed", addConfirmed);
    	model.put("template", "Views/Account/accounts.vtl" );
        return new ModelAndView(model, layout);
    };     
    
    //Vista: Crear Cuenta
    public static TemplateViewRoute serveCreateAccountPage = (request, response) -> {
    	LoginController.ensureUserIsLoggedIn(request, response);
    	Map<String, Object> model = new HashMap<String, Object>();
    	CompanyService serviceCompany = CompanyService.getInstance();
    	model.put("companies", serviceCompany.getCompanies());
    	model.put("template", "Views/Account/account.vtl" );
        return new ModelAndView(model, layout);
    };     
    
    public static TemplateViewRoute handleCreateAccountPost = (request, response) -> {
    	LoginController.ensureUserIsLoggedIn(request, response);
    	AccountService serviceAccount = AccountService.getInstance();
    	String name = request.queryParams("name");
    	String year = request.queryParams("year");
    	Double value = Double.parseDouble(request.queryParams("value"));
    	Long id = Long.parseLong(request.queryParams("company"));
       	Company company = CompanyService.getInstance().getCompanies().stream()
				.filter(a -> a.getId().equals(id) )
				.findFirst()
				.orElse(null); 
    	serviceAccount.createAccount(name, year, value, company);
    	response.redirect("/accounts?confirmed=true");
    	return null;
    };   
	
	
}
