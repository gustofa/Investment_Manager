package groupone.java.investment;

public class Account {

	private String nombre;
	private String empresa;
	private String anio;
	private Double value;
	
	public Account(String nombre, String empresa, String anio, Double value) {
		this.nombre = nombre;
		this.empresa = empresa;
		this.anio = anio;
		this.value = value;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public Double getValue() {
		return this.value;
	}

	public void setValor(Double value) {
		this.value = value;
	}
}