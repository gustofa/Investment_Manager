package groupone.java.controllers;

import spark.ModelAndView;
import spark.Route;
import spark.TemplateViewRoute;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import groupone.java.bean.Company;
import groupone.java.bean.Indicator;
import groupone.java.services.CompanyService;
import groupone.java.services.IndicatorService;
import groupone.java.error.IndicatorSyntaxException;

public class IndicatorController {
	private static String layout = "index.vtl";

	public static TemplateViewRoute serveIndicatorsPage = (request, response) -> {
		LoginController.ensureUserIsLoggedIn(request, response);
		Boolean addConfirmed = Boolean.parseBoolean(request.queryParams("confirmed"));
		Boolean editConfirmed = Boolean.parseBoolean(request.queryParams("editConfirmed"));
		IndicatorService indicatorService = IndicatorService.getInstance();
		String username = request.session().attribute("currentUser");	
		List<Indicator> indicators = indicatorService.getIndicators(username);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("indicators", indicators);
		model.put("addConfirmed", addConfirmed);
		model.put("editConfirmed", editConfirmed);
		model.put("template", "Views/Indicator/indicators.vtl");
		return new ModelAndView(model, layout);
	};

	public static TemplateViewRoute serveCreateIndicatorPage = (request, response) -> {
		LoginController.ensureUserIsLoggedIn(request, response);
		Boolean addFailure = Boolean.parseBoolean(request.queryParams("failure"));
		String errorMessage = request.queryParams("message");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("addFailure", addFailure);
		model.put("errorMessage", errorMessage);
		model.put("template", "Views/Indicator/indicator.vtl");
		return new ModelAndView(model, layout);
	};

	public static TemplateViewRoute handleCreateIndicatorPost = (request, response) -> {
		LoginController.ensureUserIsLoggedIn(request, response);
		String name = request.queryParams("name");
		String expression = request.queryParams("expression");
		IndicatorService indicatorService = IndicatorService.getInstance();
		String username = request.session().attribute("currentUser");
		
		try {
			indicatorService.createIndicator(name, expression, username);
		} catch (IndicatorSyntaxException e) {
			// TODO: handle exception
			response.redirect("./indicator?failure=true&message=" + e.getMessage());
		}

		response.redirect("./indicators?confirmed=true");
		return null;
	};

	public static TemplateViewRoute serveApplyIndicatorPage = (request, response) -> {
		LoginController.ensureUserIsLoggedIn(request, response);
		String username = request.session().attribute("currentUser");
		IndicatorService indicatorService = IndicatorService.getInstance();
		List<Indicator> indicators = indicatorService.getIndicators(username);
		CompanyService serviceCompany = CompanyService.getInstance();
		List<Company> companies = serviceCompany.getCompanies();
		Map<String, Object> model = new HashMap<String, Object>();
				
		model.put("companies", companies);
		model.put("indicators", indicators);
		model.put("template", "Views/Indicator/apply-indicator.vtl");
		model.put("result", false);
		return new ModelAndView(model, layout);
	};

	public static TemplateViewRoute handleApplyIndicatorPage = (request, response) -> {
		LoginController.ensureUserIsLoggedIn(request, response);
		Map<String, Object> model = new HashMap<String, Object>();
		String username = request.session().attribute("currentUser");
	
		IndicatorService indicatorService = IndicatorService.getInstance();
		List<Indicator> indicators = indicatorService.getIndicators(username);
		CompanyService serviceCompany = CompanyService.getInstance();
		List<Company> companies = serviceCompany.getCompanies();

		Long company_id = Long.parseLong(request.queryParams("company"));
		String year = request.queryParams("year");
		Long indicator_id = Long.parseLong(request.queryParams("indicator"));

		Company company_selected = CompanyService.getInstance().getCompanies().stream()
				.filter(a -> a.getId().equals(company_id)).findFirst().orElse(null);

		Indicator indicator_selected = IndicatorService.getInstance().getIndicators(username).stream()
				.filter(a -> a.getId().equals(indicator_id)).findFirst().orElse(null);

		Double result = indicatorService.apply(company_selected, year, indicator_selected);
		
		model.put("companies", companies);
		model.put("company_selected", company_selected);
		model.put("year", year);
		model.put("indicator_selected", indicator_selected);
		model.put("indicators", indicators);
		model.put("result", result);
		model.put("template", "Views/Indicator/apply-indicator.vtl");
		return new ModelAndView(model, layout);
	};
	
	public static TemplateViewRoute serveEditIndicatorPage = (request, response) -> {
		LoginController.ensureUserIsLoggedIn(request, response);
		String indicatorName = request.queryParams("ind");	
		String username = request.session().attribute("currentUser");
		Boolean addFailure = Boolean.parseBoolean(request.queryParams("failure"));
		String errorMessage = request.queryParams("message");
		IndicatorService indicatorService = IndicatorService.getInstance();
		
		Indicator indicatorToEdit = indicatorService.getIndicator(indicatorName);
	
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("addFailure", addFailure);
		model.put("errorMessage", errorMessage);
		model.put("indicatorName", indicatorName);
		model.put("template", "Views/Indicator/edit-indicator.vtl");
		model.put("indicatorToEdit", indicatorToEdit);
		model.put("result", false);
		return new ModelAndView(model, layout);
	};
	
	public static TemplateViewRoute serveHandleEditIndicatorPage = (request, response) -> {
		LoginController.ensureUserIsLoggedIn(request, response);
		Map<String, Object> model = new HashMap<String, Object>();	
    	String indicatorName = request.queryParams("name");
    	String expression = request.queryParams("expression");
    	IndicatorService indicatorService = IndicatorService.getInstance();
    	Indicator indicatorToEdit = indicatorService.getIndicator(indicatorName);
    	
		try {
	    	indicatorService.editIndicator(indicatorToEdit, expression);
		} catch (IndicatorSyntaxException e) {
			response.redirect("./edit-indicator?ind="+indicatorName+"&failure=true&message=" + e.getMessage());
		}

		response.redirect("./indicators?editConfirmed=true");
		return null;
	};
	
}
