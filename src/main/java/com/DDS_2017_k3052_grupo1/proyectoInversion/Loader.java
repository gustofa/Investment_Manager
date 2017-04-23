package com.DDS_2017_k3052_grupo1.proyectoInversion;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class Loader {

	private List<Cuenta> listaCuentas = new ArrayList<Cuenta>();

	public void agregarCuentas(String pathArchivo) {
		
		try {
			JsonReader reader = new JsonReader(new FileReader(pathArchivo));
			Type tipoListaCuentas = new TypeToken<List<Cuenta>>(){}.getType();
			listaCuentas = new Gson().fromJson(reader, tipoListaCuentas);			

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} 
//		finally {
//			try {
//				if (reader != null)
//					reader.close();
//			} catch (IOException ex) {
//				System.out.println(ex.getMessage());
//			}
//		}
	
	}

	public void listarCuentas() {
		for (Cuenta cuenta : listaCuentas) {
			this.imprimirCuenta(cuenta);
		}
	}

	private void imprimirCuenta(Cuenta cuenta) {
		System.out.println(" Empresa: " + cuenta.getEmpresa() + " /  Cuenta: " + cuenta.getNombre() + " /  Año: " + cuenta.getAnio() + " /  Valor (en millones de US$): " + cuenta.getValor());
	}
}
