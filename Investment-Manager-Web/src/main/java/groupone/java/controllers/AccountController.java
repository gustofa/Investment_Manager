package groupone.java.controllers;

import static spark.Spark.get;
import static spark.Spark.post;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import groupone.java.bean.Account;
import groupone.java.bean.Company;
import groupone.java.services.AccountService;
import groupone.java.services.CompanyService;
import spark.ModelAndView;
import spark.Request;
import spark.TemplateViewRoute;

import static spark.Spark.*;

import spark.utils.IOUtils;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

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
    	response.redirect("./accounts?confirmed=true");
    	return null;
    };   
    
    public static TemplateViewRoute serveUploadBatch = (request, response) -> {
    	Boolean addConfirmed = Boolean.parseBoolean(request.queryParams("confirmed"));
    	
    	Map<String, Object> model = new HashMap<String, Object>();
    	AccountService serviceAccount = AccountService.getInstance();

    	model.put("addConfirmed", addConfirmed);
    	model.put("template", "Views/Account/batch_account.vtl" );
        return new ModelAndView(model, layout);
    };   
    
    public static TemplateViewRoute handleUploadBatch = (request, response) -> {
    	//logInfo("aca empezando el handleUploadBatch");
    	String location = AccountController.class.getClassLoader().getResource("/").getPath().substring(1)+ "batches";  // the directory location where files will be stored
    	long maxFileSize = 100000000;  // the maximum size allowed for uploaded files
    	long maxRequestSize = 100000000;  // the maximum size allowed for multipart/form-data requests
    	int fileSizeThreshold = 1024;  // the size threshold after which files will be written to disk
    	MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold);
    	request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
    	
    	Collection<Part> parts = request.raw().getParts();
    	for(Part part : parts) {
    		logInfo("Name: "+part.getName());
    		logInfo("Size: "+String.valueOf(part.getSize()));
    		logInfo("Filename: "+part.getSubmittedFileName());
    	}
    	
    	String fName = request.raw().getPart("myfile").getSubmittedFileName();
    	
    	Part uploadedFile = request.raw().getPart("myfile");
    	Path out = Paths.get(location+"/"+fName);
    	try (final InputStream in = uploadedFile.getInputStream()) {
    		Files.copy(in, out);
    		logFileInfo(request, out);
    		uploadedFile.delete();
    	}
    	// cleanup
    	multipartConfigElement = null;
    	parts = null;
    	uploadedFile = null;   	
    	
        response.redirect("./batch_account?confirmed=true");
        return null;
    };   
    
    
    
    // methods used for logging
    private static void logFileInfo(Request req, Path tempFile) throws IOException, ServletException {
        	System.out.println("Uploaded file '" + getFileName(req.raw().getPart("myfile")) + "' saved as '" + tempFile.toAbsolutePath() + "'");
    }   
    
    private static void logInfo(String info) throws IOException, ServletException {
    	System.out.println(info);
    }     
    
    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }    
    
}
