package groupone.java.investment;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class IndicatorManager {


	public void addIndicators(String filePath) throws IOException {
		JsonReader reader = new JsonReader(new FileReader(filePath));
		Type indicatorsListType = new TypeToken<List<Indicator>>() {
		}.getType();
		IndicatorList.indicatorsList = new Gson().fromJson(reader, indicatorsListType);
	}
}
