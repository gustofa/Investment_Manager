package groupone.java.controllers;

import spark.ModelAndView;
import spark.Route;
import spark.TemplateViewRoute;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import groupone.java.bean.Metodology;
import groupone.java.bean.User;
import groupone.java.services.CompanyService;
import groupone.java.services.MetodologyService;
import groupone.java.error.IndicatorSyntaxException;

public class MetodologyController {
	private static String layout = "index.vtl";

	public static TemplateViewRoute serveMetodologiesPage = (request, response) -> {
		LoginController.ensureUserIsLoggedIn(request, response);
		Boolean addConfirmed = Boolean.parseBoolean(request.queryParams("confirmed"));
		MetodologyService metodologyService = MetodologyService.getInstance();
		String username = request.session().attribute("currentUser");	
		List<Metodology> metodologies = metodologyService.getMetodologies(username);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("metodologies", metodologies);
		model.put("addConfirmed", addConfirmed);
		model.put("template", "Views/Metodology/metodologies.vtl");
		return new ModelAndView(model, layout);
	};

	public static TemplateViewRoute serveCreateMetodologyPage = (request, response) -> {
		LoginController.ensureUserIsLoggedIn(request, response);
		Boolean addFailure = Boolean.parseBoolean(request.queryParams("failure"));
		String errorMessage = request.queryParams("message");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("addFailure", addFailure);
		model.put("errorMessage", errorMessage);
		model.put("template", "Views/Metodology/metodology.vtl");
		return new ModelAndView(model, layout);
	};

	public static TemplateViewRoute handleCreateMetodologyPost = (request, response) -> {
		LoginController.ensureUserIsLoggedIn(request, response);
		String name = request.queryParams("name");
		String expression = request.queryParams("expression");
		MetodologyService metodologyService = MetodologyService.getInstance();
		String username = request.session().attribute("currentUser");
		
		
		
		metodologyService.createMetodology(name, expression, username);
	
		response.redirect("/metodologies?confirmed=true");
		return null;
	};
}
