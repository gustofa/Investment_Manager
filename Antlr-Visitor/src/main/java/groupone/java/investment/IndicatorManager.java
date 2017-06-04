package groupone.java.investment;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import org.apache.commons.io.FilenameUtils;

public class IndicatorManager {

	private static IndicatorManager instance;
	private HashMap<String,Indicator> indicators = new HashMap<String,Indicator>();
	
	private IndicatorManager(){};
	
	public static IndicatorManager getInstance(){
		if(instance == null){
			instance = new IndicatorManager();
		}
		
		return instance;
	}
	
	public Indicator getIndicator(String name){
		return this.indicators.get(name);
	}

	public void loadPredefinedIndicators() throws IOException{
		
		// Get files from file system
		String predefinedIndicatorsFolder = getClass().getResource("/predefinedIndicators").getFile();
	    File[] predefinedIndicators = new File(predefinedIndicatorsFolder).listFiles();
	    
	    // Creates and load predefined indicators
	    for(File indicatorFile : predefinedIndicators){
	    	Indicator newIndicator = new Indicator();
	    	String indicatorName = FilenameUtils.getBaseName(indicatorFile.getName());
	    	newIndicator.setName(indicatorName);
	    	String expression = new String(Files.readAllBytes(Paths.get(indicatorFile.getPath())), StandardCharsets.UTF_8);
	    	newIndicator.setExpression(expression);
	    	
	    	this.indicators.put(newIndicator.getName(),newIndicator);
	    }
	}
}
