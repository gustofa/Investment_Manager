package groupone.java.indicator;

public class FactoryIndicator {

	AbstractIndicator newIndicator(){
		Indicator indicador = new Indicator();
		return indicador;
	}
}
