package groupone.java.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
@NamedQuery(name = "getAccountbyNameCo", query = "SELECT p FROM Account p WHERE p.name LIKE :pname AND company_id=:pco_id")
public class Account extends Persistible {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String year;
	private Double value = 0.0;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	private Company company;
	
	public Account() {
		
	}
	
	public Account(String nombre, String anio, Double value) {
		this.name = nombre;
		this.setYear(anio);
		this.value = value;
	}

	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
		
	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public Double getValue() {
		return this.value;
	}

	public void setValor(Double value) {
		this.value = value;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}