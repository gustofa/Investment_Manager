package groupone.java.investment;

public class FactoryIndicator implements AbstractFactoryIndicators{

	public Indicator newIndicator(){
		Indicator indicador = new Indicator();
		return indicador;
	}
}
