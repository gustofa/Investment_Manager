package groupone.java.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

@Entity
@Table(name = "User")

@NamedQueries({@NamedQuery(name = "getUser", query = "SELECT u FROM User u WHERE u.name = :pname")
}) 
public class User extends Persistible {
	private static final long serialVersionUID = 1L;
	private String password;
	
	public User() {
		
	}

	public String getName() {
		return this.name;
	}
	
	public String getPassword() {
		return this.password;
	}	
}