package groupone.java.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Company")
public class Company extends Persistible {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Account> accountList = new ArrayList<Account>();
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Account> getAccounts(){
		return this.accountList;
	}
	
	public void addAccount(Account account){
		this.accountList.add(account);
	}
	
}
