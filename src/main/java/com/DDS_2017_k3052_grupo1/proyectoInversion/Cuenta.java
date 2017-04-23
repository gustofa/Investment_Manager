package com.DDS_2017_k3052_grupo1.proyectoInversion;

public class Cuenta {

	private String nombre;
	private String empresa;
	private String anio;
	private Double valor;
	
	public Cuenta(String nombre, String empresa, String anio, Double valor) {
		this.nombre = nombre;
		this.empresa = empresa;
		this.anio = anio;
		this.valor = valor;
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}