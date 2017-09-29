package groupone.java.controllers;

import spark.ModelAndView;
import spark.Route;
import spark.TemplateViewRoute;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import groupone.java.bean.Indicator;
import groupone.java.services.IndicatorService;

public class IndicatorController {
	private static String layout = "index.vtl";

	public static TemplateViewRoute serveIndicatorsPage = (request, response) -> {
		IndicatorService indicatorService = IndicatorService.getInstance();
		List<Indicator> indicators = indicatorService.getIndicators();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("indicators", indicators);
		model.put("template", "Views/Indicator/indicators.vtl");
		return new ModelAndView(model, layout);
	};

	public static TemplateViewRoute serveCreateIndicatorPage = (request, response) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("template", "Views/Indicator/indicator.vtl");
		return new ModelAndView(model, layout);
	};
	
	public static TemplateViewRoute handleCreateIndicatorPost = (request, response) -> {
		String name = request.queryParams("name");
    	String expression = request.queryParams("expression"); 	
    
    	IndicatorService indicatorService = IndicatorService.getInstance();
		indicatorService.createIndicator(name, expression);
    	
		Map<String, Object> model = new HashMap<String, Object>();
    	model.put("template", "Views/Indicator/indicators.vtl");
        return new ModelAndView(model, layout);
	};
}
