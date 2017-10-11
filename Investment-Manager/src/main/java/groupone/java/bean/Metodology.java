package groupone.java.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Metodology")
@NamedQuery(name = "getMetodologies", query = "SELECT m FROM Metodology m")
public class Metodology extends Persistible {
	
	private static final long serialVersionUID = 1L;
	
	private String expression;
	private long user_id;
	
	public Metodology() {

	}
	
	public Metodology(String name, String expression, long user_id ) { 
		this.name = name;
		this.expression = expression;
		this.user_id = user_id;
	}
	
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	} 
	
	public long getUserId() {
		return this.user_id;
	}

	public void setUserId(long user_id) {
		this.user_id = user_id;
	}
}