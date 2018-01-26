package groupone.java.app;
import groupone.java.controllers.AccountController;
import groupone.java.controllers.IndicatorController;
import groupone.java.controllers.LoginController;
import groupone.java.controllers.MetodologyController;
import groupone.java.quartz.CronJob;
import spark.ModelAndView;
import spark.Spark;
import spark.servlet.SparkApplication;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import spark.utils.IOUtils;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
public class App implements SparkApplication{
	private static final String PERSISTENCE_UNIT_NAME = "DDS";

//	public App(){
//	    CronJob job = new CronJob();
//	    try {
//			job.run();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public static void main( String[] args ) {
		new App().init();
	}
	

	public void init(){
		 	Spark.staticFileLocation("/public");	
		 	Spark.port(9090);

	       //staticFiles.externalLocation("/batches");
		 	
		 	//pagina base que contendra a todas las demas
		    String layout = "index.vtl";
		    
		    get("/", LoginController.serveLoginPage, new VelocityTemplateEngine());
		    post("/login", LoginController.handleLoginPost, new VelocityTemplateEngine());
		    post("/logout", LoginController.handleLogoutPost, new VelocityTemplateEngine());
		    
		    get("/accounts",AccountController.serveAccountsPage, new VelocityTemplateEngine());     
	        get("/account",AccountController.serveCreateAccountPage, new VelocityTemplateEngine());     
	        post("/account", AccountController.handleCreateAccountPost , new VelocityTemplateEngine());   
	        get("/batch_account", AccountController.serveUploadBatch , new VelocityTemplateEngine());   
	        post("/upload", "multipart/form-data", AccountController.handleUploadBatch , new VelocityTemplateEngine());   
	        
	        
	        
	        get("/indicators", IndicatorController.serveIndicatorsPage, new VelocityTemplateEngine());
	        get("/indicator", IndicatorController.serveCreateIndicatorPage, new VelocityTemplateEngine());
	        post("/indicator", IndicatorController.handleCreateIndicatorPost, new VelocityTemplateEngine());   
	        get("/apply-indicator", IndicatorController.serveApplyIndicatorPage, new VelocityTemplateEngine());
	        post("/apply-indicator", IndicatorController.handleApplyIndicatorPage, new VelocityTemplateEngine());   
	        get("/edit-indicator", IndicatorController.serveEditIndicatorPage, new VelocityTemplateEngine());
	        post("/edit-indicator", IndicatorController.serveHandleEditIndicatorPage, new VelocityTemplateEngine());
	 
	        
	        get("/metodologies", MetodologyController.serveMetodologiesPage, new VelocityTemplateEngine()); 
	        get("/metodology", MetodologyController.serveCreateMetodologyPage, new VelocityTemplateEngine());
	        post("/metodology", MetodologyController.handleCreateMetodologyPost, new VelocityTemplateEngine());    
	        
	        get("/json", (request, response) -> {
	            return null; 
	        }, new JSONTransformer());
	 
	    }
}
