package groupone.java.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Company")
public class Company extends Persistible {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Account> accountList = new ArrayList<Account>();
	
	public List<Account> getAccounts(){
		return this.accountList;
	}
	
	public void addAccount(Account account){
		this.accountList.add(account);
	}
	
}
