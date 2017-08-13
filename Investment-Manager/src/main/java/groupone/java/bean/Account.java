package groupone.java.bean;

public class Account {

	private String name;
	private String year;
	private Double value = 0.0;
	
	public Account(String nombre, String anio, Double value) {
		this.name = nombre;
		this.setYear(anio);
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public Double getValue() {
		return this.value;
	}

	public void setValor(Double value) {
		this.value = value;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}