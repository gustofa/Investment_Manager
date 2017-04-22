package com.DDS_2017_k3052_grupo1.proyectoInversion;

public class Empresa {

	private int codigo;
	private String nombre;
	private String cuenta;

	public Empresa(int codigo, String nombre, String cuenta) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.cuenta = cuenta;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
}
