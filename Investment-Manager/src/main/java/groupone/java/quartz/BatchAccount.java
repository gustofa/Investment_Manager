package groupone.java.quartz;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
import groupone.java.bean.Indicator;
import groupone.java.bean.PrecalculatedIndicator;
import groupone.java.controllers.AccountController;
import groupone.java.repositories.Repository;
import groupone.java.services.AccountService;
import groupone.java.services.CompanyService;
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
         CompanyService companyService = CompanyService.getInstance();
 		 List<Company> companies = companyService.getCompanies();		
	 	 List<Indicator> indicators = indicatorService.getAllIndicators();

	 	 String sDirectorio = BatchAccount.class.getClassLoader().getResource("/").getPath().substring(1)+ "batches";         
         //String sDirectorio = "src\\main\\resources\\batches";
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

        		//If the file name contains the string "-read" then the batch is not charged on database
        		 if ( !(ficheros[x].getName().toLowerCase().indexOf(read.toLowerCase()) != -1) ) {
        		 try {
        			 	accountService.loadAccounts2(ficheros[x].getPath());
        				//accountService.loadAccounts2(indicatorService.getClass().getClassLoader().getResource(ficheros[x].getName()).getFile());
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
    	          	         
    	         //File file = new File("src\\main\\resources\\batches\\" + ficheros[x].getName()); 
    	         File file = new File(sDirectorio + "\\" + ficheros[x].getName()); 

    	         //Rename the file to avoid reading again
                 //file.renameTo(new File("src\\main\\resources\\batches\\" + basename + "-read.json"));
    	         file.renameTo(new File(sDirectorio + "\\" + basename + "-read.json"));

    	           	         
    	         //Precalcular indicadores si no existen y actualizarlos si ya existen
    	
    	 		int current_year = Calendar.getInstance().get(Calendar.YEAR);
    	 		
    	 		for (int i=0; i<indicators.size(); i++){    	 			
    	 			for (int j=0; j<companies.size(); j++){		
    	 				
    	 				for (int y=2014; y<=current_year; y++){
    	 					String year = Integer.toString(y);
    	
    	 					PrecalculatedIndicator precalculatedIndicatorToPersist = new PrecalculatedIndicator();

    	 					try {   	 				       
    	 						PrecalculatedIndicator precalc = repository.precalculatedIndicators().getPrecalculatedIndicator((indicators.get(i)).getId(), year, (companies.get(j)).getId());					 
    	 						precalc.setValue(indicatorService.apply(companies.get(j), year, indicators.get(i)));
    	 						repository.precalculatedIndicators().updatePrecalculatedIndicator(precalculatedIndicatorToPersist);
    	 						}
    	 				      catch (NoResultException e) { 
    	 				         /* This block will only execute if any NoResultException
    	 				          * occurs in try block
    	 				          */
    	 				         
    	 				         precalculatedIndicatorToPersist.setYear(year);
    	 							precalculatedIndicatorToPersist.setCompanyId((companies.get(j)).getId());
    	 							precalculatedIndicatorToPersist.setIndicatorId((indicators.get(i)).getId());
    	 							precalculatedIndicatorToPersist.setName((indicators.get(i)).getName());
    	 							precalculatedIndicatorToPersist.setValue(indicatorService.apply(companies.get(j), year, indicators.get(i)));
    	 							repository.precalculatedIndicators().persist(precalculatedIndicatorToPersist);
    	 				      }   	 					
	 					}
 					}
	 			}
    	 		
    	        } else {

    	            System.out.println("This file was already read");

    	        }
    	       
        	 }
         } 
         
         else { 
        	 System.out.println("No files to read");
         }    
         
         
        repository.close();
 		emFactory.close();
    }

}
