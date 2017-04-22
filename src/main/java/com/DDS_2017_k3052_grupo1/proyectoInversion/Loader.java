package com.DDS_2017_k3052_grupo1.proyectoInversion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Loader {

	private List<Empresa> listaEmpresas = new ArrayList<Empresa>();

	public void agregarEmpresas(String pathArchivo) {
		File file = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		try {
			file = new File(pathArchivo);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String linea;

			while ((linea = bufferedReader.readLine()) != null) {

				String[] partesDelString = linea.split(";");
				String s = partesDelString[0];
				String d = partesDelString[1];

				Empresa empresa = new Empresa(0, s, d);
				listaEmpresas.add(empresa);
			}
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if (fileReader != null)
					fileReader.close();
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public void listarEmpresas() {
		for (Empresa empresa : listaEmpresas) {
			this.imprimirEmpresa(empresa);
		}
	}

	private void imprimirEmpresa(Empresa empresa) {
		System.out.println(" Nombre :   " + empresa.getNombre() + "  Cuenta:   " + empresa.getCuenta());
	}
}
