package groupone.java.app;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import groupone.java.bean.Account;
import groupone.java.bean.Company;
import groupone.java.controllers.AccountController;
import groupone.java.controllers.IndicatorController;
import groupone.java.controllers.LoginController;
import groupone.java.controllers.MetodologyController;
import groupone.java.repositories.Repository;
import groupone.java.services.AccountService;
import groupone.java.services.CompanyService;
import groupone.java.services.IndicatorService;
import spark.ModelAndView;
import spark.Spark;
import spark.servlet.SparkApplication;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

import spark.utils.IOUtils;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static spark.Spark.*;

public class App implements SparkApplication{
	private static final String PERSISTENCE_UNIT_NAME = "DDS";

	public static void main( String[] args ) {
		new App().init();
	}
	

	public void init(){
		 	Spark.staticFileLocation("/public");	
		 	Spark.port(9090);
		 	
		 	//página base que contendrá a todas las demás
		    String layout = "index.vtl";
		    
		    get("/", LoginController.serveLoginPage, new VelocityTemplateEngine());
		    post("/login", LoginController.handleLoginPost, new VelocityTemplateEngine());
		    post("/logout", LoginController.handleLogoutPost, new VelocityTemplateEngine());
		    
		    get("/accounts",AccountController.serveAccountsPage, new VelocityTemplateEngine());     
	        get("/account",AccountController.serveCreateAccountPage, new VelocityTemplateEngine());     
	        post("/account", AccountController.handleCreateAccountPost , new VelocityTemplateEngine());   
	        get("/batch_account", AccountController.serveUploadBatch , new VelocityTemplateEngine());   
	        post("/upload", AccountController.handleUploadBatch , new VelocityTemplateEngine());   
	        
	        
	        
	        get("/indicators", IndicatorController.serveIndicatorsPage, new VelocityTemplateEngine());
	        get("/indicator", IndicatorController.serveCreateIndicatorPage, new VelocityTemplateEngine());
	        post("/indicator", IndicatorController.handleCreateIndicatorPost, new VelocityTemplateEngine());   
	        get("/apply-indicator", IndicatorController.serveApplyIndicatorPage, new VelocityTemplateEngine());
	        post("/apply-indicator", IndicatorController.handleApplyIndicatorPage, new VelocityTemplateEngine());   
	 
	        
	        get("/metodologies", MetodologyController.serveMetodologiesPage, new VelocityTemplateEngine()); 
	        get("/metodology", MetodologyController.serveCreateMetodologyPage, new VelocityTemplateEngine());
	        post("/metodology", MetodologyController.handleCreateMetodologyPost, new VelocityTemplateEngine());    
	        
	        get("/json", (request, response) -> {
	            return null; 
	        }, new JSONTransformer());
	 
	    }
}
