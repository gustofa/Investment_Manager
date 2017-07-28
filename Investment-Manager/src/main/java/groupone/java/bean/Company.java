package groupone.java.bean;

import java.util.ArrayList;
import java.util.List;

public class Company {
	private String name;
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
	
	public Account getAccount(String name, String year){
		return this.accountList.stream()
						.filter(a -> a.getName().equals(name) && a.getAnio().equals(year))
						.findFirst()
						.orElse(null);		
	}
}
