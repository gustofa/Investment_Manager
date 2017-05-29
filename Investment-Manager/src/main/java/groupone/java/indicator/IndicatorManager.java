package groupone.java.indicator;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class IndicatorManager {
	
	private List<Indicator> listaIndicadores = new ArrayList<Indicator>();

	public void agregarIndicadores(String pathArchivo) throws IOException {
			JsonReader reader = new JsonReader(new FileReader(pathArchivo));
			Type tipoListaIndicadores = new TypeToken<List<Indicator>>() {}.getType();
			listaIndicadores = new Gson().fromJson(reader, tipoListaIndicadores);
	}
	
	public List<Indicator> getIndicadores(){
		return this.listaIndicadores;
	}

	public void imprimirIndicadores() {
		for (Indicator indicador : this.listaIndicadores) {
			this.imprimirIndicator(indicador);
		}
	}

	private void imprimirIndicator(Indicator indicador) {
		System.out.println(" Indicador: " + indicador.getName() + " /  Expresion: " + indicador.getExpression());
	}

}
