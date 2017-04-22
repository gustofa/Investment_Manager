package com.DDS_2017_k3052_grupo1.proyectoInversion;

public class TestLoader {

	public static void main(String[] args) {
		String path = "C:/cuentas.txt";
		
		Loader load = new Loader();
		load.agregarEmpresas(path);
		load.listarEmpresas();
	}
}
