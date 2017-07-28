package groupone.java.bean;

public class Account {

	private String name;
	private String company;
	private String year;
	private Double value = 0.0;
	
	public Account(String nombre, String empresa, String anio, Double value) {
		this.name = nombre;
		this.company = empresa;
		this.year = anio;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String empresa) {
		this.company = empresa;
	}

	public String getAnio() {
		return year;
	}

	public void setAnio(String anio) {
		this.year = anio;
	}

	public Double getValue() {
		return this.value;
	}

	public void setValor(Double value) {
		this.value = value;
	}
}