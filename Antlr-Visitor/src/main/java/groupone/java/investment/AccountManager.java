package groupone.java.investment;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class AccountManager {

	//private List<Account> listaCuentas = new ArrayList<Account>();

	public void agregarCuentas(String pathArchivo) throws IOException {
			JsonReader reader = new JsonReader(new FileReader(pathArchivo));
			Type tipoListaCuentas = new TypeToken<List<Account>>() {}.getType();
			AccountList.listaCuentas = new Gson().fromJson(reader, tipoListaCuentas);
			
			for (Account cuenta : AccountList.listaCuentas) {
				AccountList.MapCuentas.put(cuenta.getNombre()+cuenta.getEmpresa()+cuenta.getAnio(), cuenta);
			}
	}
	
	public List<Account> getCuentas(){
		return AccountList.listaCuentas;
	}

	public void imprimirCuentas() {
		for (Account cuenta : AccountList.listaCuentas) {
			this.imprimirCuenta(cuenta);
		}
	}

	private void imprimirCuenta(Account cuenta) {
		System.out.println(" Empresa: " + cuenta.getEmpresa() + " /  Cuenta: " + cuenta.getNombre() + " /  Año: "
				+ cuenta.getAnio() + " /  Valor (en millones de US$): " + cuenta.getValue());
	}
}
