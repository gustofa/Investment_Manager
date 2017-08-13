package groupone.java.investment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import groupone.java.bean.Company;

public class CompanyList {

	public static List<Company> companyList = new ArrayList<Company>();
	public static HashMap<String,Company> MapCompanias = new HashMap<String,Company>();
	
	public static Company findCompany(String nombre,String empresa, String anio) {
		return MapCompanias.get(nombre+empresa+anio);
	}		
}
