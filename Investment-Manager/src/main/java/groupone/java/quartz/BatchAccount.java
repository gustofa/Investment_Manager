package groupone.java.quartz;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Date;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.HibernateException;
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

public class BatchAccount implements Job {
	
	private static final String PERSISTENCE_UNIT_NAME = "DDS";
	private EntityManagerFactory emFactory;
	private Repository repository;
	private Company company;
	
    public BatchAccount() {
    }

    public void execute(JobExecutionContext context)
        throws JobExecutionException , HibernateException{

               
        emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
 		repository = new Repository(emFactory.createEntityManager());
         
         IndicatorService indicatorService = IndicatorService.getInstance();
         AccountService accountService = AccountService.getInstance();      

                  
         String sDirectorio = "src\\main\\resources\\batches";
         File f = new File(sDirectorio);
         String read = "-read";
         
         if (f.exists()){  
        	 File[] ficheros = f.listFiles(new FilenameFilter() {
        		    public boolean accept(File dir, String name) {
        		        return  name.endsWith(".json");
        		    }
        		});
        	 for (int x=0;x<ficheros.length;x++){
        		 System.out.println(ficheros[x].getName());
        		 System.out.println(ficheros[x].getPath());
        		 
        		 //If the file name contains the string "-read" then the batch is not charged on database
        		 if ( !(ficheros[x].getName().toLowerCase().indexOf(read.toLowerCase()) != -1) ) {
        		 try {
//        				accountService.loadAccounts2(indicatorService.getClass().getClassLoader().getResource(ficheros[x].getName()).getPath());
        				accountService.loadAccounts2(ficheros[x].getPath());
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}   	  		
    	  		
    	         for (Company company1 : CompanyList.companyList) {
    	        	 
    	        	 Company comp = repository.companies().getCompanyByName(company1.getName());
    	        	 if(comp == null){
    	        		 repository.companies().persist(company1);
    	        		 
    	        		 for (Account account : company1.getAccounts()) {
    	      	  			repository.accounts().persist(account);
    	      	 		}
    	        	 } else {
    	        		 
    	        		 for (Account account : company1.getAccounts()) {
     	      	  			
     	      	  			Account acc = repository.accounts().getAccountByName(account.getName(), account.getYear(), comp.getId());
     	      	  			
     	      	  			if(acc == null){
	     	      	  			repository.accounts().persist(account);
		       	        		 		       	        		
		       	        	 } else {
		       	        		account.setCompany(comp);
		       	        		repository.accounts().updateAccount(account);
		       	        	 }
     	      	 		}
    	        		    	        		 
    	        	 }
    	 		}
   	             	  
        		 
    	         //Get the file name without extension
    	         String basename = FilenameUtils.getBaseName(ficheros[x].getName());
    	          	         
    	         File file = new File("src\\main\\resources\\batches\\" + ficheros[x].getName()); 
    	         
    	         File file2 = new File("src\\main\\resources\\batches\\" + basename + "-read.json");

    	         //Rename the file to avoid reading again
    	         boolean correcto = file.renameTo(file2);
    	         if (correcto)
    	        	  System.out.println("El renombrado ha sido correcto");
    	        	else
    	        	  System.out.println("El renombrado no se ha podido realizar");
    	         

    	         } else {

    	            System.out.println("This file was already read");

    	         }
    	       
        	 }
         } 
         
         else { 
        	 
         }    
         
         
        repository.close();
 		emFactory.close();
    }

}
