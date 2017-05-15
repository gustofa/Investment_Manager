package com.DDS_2017_k3052_grupo1.proyectoInversion;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class AccountManager {

	private List<Account> listaCuentas = new ArrayList<Account>();

	public void agregarCuentas(String pathArchivo) throws IOException {
			JsonReader reader = new JsonReader(new FileReader(pathArchivo));
			Type tipoListaCuentas = new TypeToken<List<Account>>() {}.getType();
			listaCuentas = new Gson().fromJson(reader, tipoListaCuentas);
	}
	
	public List<Account> getCuentas(){
		return this.listaCuentas;
	}

	public void imprimirCuentas() {
		for (Account cuenta : this.listaCuentas) {
			this.imprimirCuenta(cuenta);
		}
	}

	private void imprimirCuenta(Account cuenta) {
		System.out.println(" Empresa: " + cuenta.getEmpresa() + " /  Cuenta: " + cuenta.getNombre() + " /  Año: "
				+ cuenta.getAnio() + " /  Valor (en millones de US$): " + cuenta.getValor());
	}
}
