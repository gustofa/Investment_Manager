package groupone.java.investment;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class IndicatorManager {

	private List<Indicator> indicators = new ArrayList<Indicator>();

	public void addIndicators(String filePath) throws IOException {
		JsonReader reader = new JsonReader(new FileReader(filePath));
		Type indicatorsListType = new TypeToken<List<Indicator>>() {
		}.getType();
		IndicatorList.indicatorsList = new Gson().fromJson(reader, indicatorsListType);
	}
	
	public File[] LoadPredefinedIndicators() throws IOException{
		String predefinedIndicatorsFolder = getClass().getResource("/predefinedIndicators").getFile();
	    File[] predefinedIndicators = new File(predefinedIndicatorsFolder).listFiles();
	    
	    for(File indicatorFile : predefinedIndicators){
	    	Indicator newIndicator = new Indicator();
	    	newIndicator.setName(indicatorFile.getName());
	    	String expression = new String(Files.readAllBytes(Paths.get(indicatorFile.getPath())), StandardCharsets.UTF_8);
	    	newIndicator.setExpression(expression);
	    	this.indicators.add(newIndicator);
	    }
	    
	    return predefinedIndicators;
	}
}
