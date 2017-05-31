package groupone.java.investment;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class EntityJsonLoader {
	public static <T> List<T> getEntities(String filePath) throws IOException {
		JsonReader reader = new JsonReader(new FileReader(filePath));
		Type entityType = new TypeToken<List<T>>() {
		}.getType();
		
		return new Gson().fromJson(reader, entityType);
	}
}