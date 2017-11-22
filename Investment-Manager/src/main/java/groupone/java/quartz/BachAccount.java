package groupone.java.quartz;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import groupone.java.app.AccountList;
import groupone.java.app.CompanyList;
import groupone.java.bean.Account;
import groupone.java.bean.Company;
import groupone.java.repositories.Repository;
import groupone.java.services.AccountService;
import groupone.java.services.IndicatorService;

public class BachAccount implements Job {
	
	private static final String PERSISTENCE_UNIT_NAME = "DDS";
	private EntityManagerFactory emFactory;
	private Repository repository;
	private Company company;
	
    public BachAccount() {
    }

    public void execute(JobExecutionContext context)
        throws JobExecutionException {

        // Say Hello to the World and display the date/time
         System.out.println("Hola, voy a cargar cuentas! - " + new Date());
         
        emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
 		repository = new Repository(emFactory.createEntityManager());
         
         IndicatorService indicatorService = IndicatorService.getInstance();
         AccountService accountService = AccountService.getInstance();
         
         String sDirectorio = "src\\main\\resources";
         File f = new File(sDirectorio);
         
         if (f.exists()){  
        	 File[] ficheros = f.listFiles();
        	 for (int x=0;x<ficheros.length;x++){
        		 
        		 try {
        				accountService.loadAccounts2(indicatorService.getClass().getClassLoader().getResource(ficheros[x].getName()).getFile());
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}

    	  		for (Account account : AccountList.accountList) {
    	  			repository.accounts().persist(account);
    	 		}
    	  		
    	         for (Company company1 : CompanyList.companyList) {
    	         	repository.companies().persist(company1);
    	 		}
        		 
        		 
        	   System.out.println(ficheros[x].getName());
        	 }
         } else { 
        	 
         }
         
         
         
        repository.close();
 		emFactory.close();
    }

}
