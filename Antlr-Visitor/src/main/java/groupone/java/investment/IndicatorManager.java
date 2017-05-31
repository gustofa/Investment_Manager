package groupone.java.investment;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class IndicatorManager {
	
	//private List<Indicator> listaIndicadores = new ArrayList<Indicator>();

	public void agregarIndicadores(String pathArchivo) throws IOException {
			JsonReader reader = new JsonReader(new FileReader(pathArchivo));
			Type tipoListaIndicadores = new TypeToken<List<Indicator>>() {}.getType();
			IndicatorList.listaIndicadores = new Gson().fromJson(reader, tipoListaIndicadores);
			
			//IndicatorList.imprimirIndicadores();
			
			//Rearmo las expresiones de aquellos indicadores que contengan otros indicadores
			//IndicatorList.listaIndicadores
	}

}
