package groupone.java.investment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IndicatorList {

		public static List<Indicator> listaIndicadores = new ArrayList<Indicator>();

		public static void imprimirIndicadores() {
			for (Indicator indicador : listaIndicadores) {
				printIndicator(indicador);
			}
		}

		private static void printIndicator(Indicator indicador) {
			System.out.println(" Indicador: " + indicador.getName() + " /  Expresion: " + indicador.getExpression());
		}

		public static Indicator findIndicator(String name) {
		    Optional<Indicator> indicator = listaIndicadores.stream()
		        .filter(p -> p.getName().equals(name))
		        .findFirst();
		    return indicator.isPresent() ? indicator.get() : null;
		}		
		
}
