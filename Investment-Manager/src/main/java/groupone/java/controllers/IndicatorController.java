package groupone.java.controllers;

import spark.ModelAndView;
import spark.Route;
import spark.TemplateViewRoute;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import groupone.java.bean.Indicator;
import groupone.java.services.IndicatorService;
import groupone.java.error.IndicatorSyntaxException;

public class IndicatorController {
	private static String layout = "index.vtl";

	public static TemplateViewRoute serveIndicatorsPage = (request, response) -> {
		Boolean addConfirmed = Boolean.parseBoolean(request.queryParams("confirmed"));
		IndicatorService indicatorService = IndicatorService.getInstance();
		List<Indicator> indicators = indicatorService.getIndicators();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("indicators", indicators);
		model.put("addConfirmed", addConfirmed);
		model.put("template", "Views/Indicator/indicators.vtl");
		return new ModelAndView(model, layout);
	};

	public static TemplateViewRoute serveCreateIndicatorPage = (request, response) -> {
		Boolean addFailure = Boolean.parseBoolean(request.queryParams("failure"));
		String errorMessage = request.queryParams("message");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("addFailure", addFailure);
		model.put("errorMessage", errorMessage);
		model.put("template", "Views/Indicator/indicator.vtl");
		return new ModelAndView(model, layout);
	};
	
	public static TemplateViewRoute handleCreateIndicatorPost = (request, response) -> {
		String name = request.queryParams("name");
    	String expression = request.queryParams("expression"); 	
    	IndicatorService indicatorService = IndicatorService.getInstance();

    	try {
    		indicatorService.createIndicator(name, expression);
		} catch (IndicatorSyntaxException e) {
			// TODO: handle exception			
			response.redirect("/indicator?failure=true&message=" + e.getMessage());
		}
    	
    	response.redirect("/indicators?confirmed=true");
    	return null;
	};
}
