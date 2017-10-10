package groupone.java.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateViewRoute;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import groupone.java.bean.User;
import groupone.java.services.IndicatorService;
import groupone.java.services.LoginService;

public class LoginController {

    public static TemplateViewRoute serveLoginPage = (request,response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("loggedOut", removeSessionAttrLoggedOut(request));
        model.put("loginRedirect", removeSessionAttrLoginRedirect(request));
		return new ModelAndView(model, "Views/Login/login.vtl");  
    };

    public static boolean removeSessionAttrLoggedOut(Request request) {
        Object loggedOut = request.session().attribute("loggedOut");
        request.session().removeAttribute("loggedOut");
        return loggedOut != null;
    }

    public static String removeSessionAttrLoginRedirect(Request request) {
        String loginRedirect = request.session().attribute("loginRedirect");
        request.session().removeAttribute("loginRedirect");
        return loginRedirect;
    }
    
    public static TemplateViewRoute handleLoginPost = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        
        String name = request.queryParams("name");
    	String password = request.queryParams("password"); 
        
    	LoginService loginService = LoginService.getInstance();
        if (!loginService.authenticate(name, password)) {
            model.put("authenticationFailed", true);
            model.put("loggedOut", removeSessionAttrLoggedOut(request));
            model.put("loginRedirect", removeSessionAttrLoginRedirect(request));
            return new ModelAndView(model, "Views/Login/login.vtl");  
        }
        
        request.session().attribute("currentUser", name);
        response.redirect("/accounts");
        return null;  
    };
    
    public static TemplateViewRoute handleLogoutPost = (request, response) -> {
        request.session().removeAttribute("currentUser");
        request.session().attribute("loggedOut", true);
        response.redirect("/");
        return null;
    };
    
    public static void ensureUserIsLoggedIn(Request request, Response response) {
        if (request.session().attribute("currentUser") == null) {
            request.session().attribute("loginRedirect", request.pathInfo());
            response.redirect("/");
        }
    };    
 }