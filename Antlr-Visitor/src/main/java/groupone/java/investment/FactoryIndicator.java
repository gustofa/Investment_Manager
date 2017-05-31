package groupone.java.investment;

public class FactoryIndicator {

	AbstractIndicator newIndicator(){
		Indicator indicador = new Indicator();
		return indicador;
	}
}
