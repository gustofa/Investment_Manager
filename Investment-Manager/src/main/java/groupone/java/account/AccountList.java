package groupone.java.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountList {

	public static List<Account> listaCuentas = new ArrayList<Account>();


	private static void imprimirCuenta(Account cuenta) {
		System.out.println(" Cuenta: " + cuenta.getNombre() + " / Empresa: " + cuenta.getEmpresa() + " / Año: " + cuenta.getAnio());
	}

	public static Account findAccount(String nombre,String empresa, String anio) {
		Optional<Account> cuenta = listaCuentas.stream()
				.filter(p -> p.getNombre().equals(nombre)
			        && p.getEmpresa().equals(empresa)
			        && p.getAnio().equals(anio))
			    .findFirst();
		return cuenta.isPresent() ? cuenta.get() : null;
	}		
			
	
}
