package groupone.java.quartz;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BachAccount implements Job {
	
    public BachAccount() {
    }

    public void execute(JobExecutionContext context)
        throws JobExecutionException {

        // Say Hello to the World and display the date/time
         System.out.println("Hola, voy a cargar cuentas! - " + new Date());
    }

}
