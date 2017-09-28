package groupone.java.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.antlr.v4.runtime.tree.ParseTree;

@Entity
@Table(name = "Indicator")
public class Indicator extends Persistible {
	private static final long serialVersionUID = 1L;
	private String name;
	private String expression;
	
	public Indicator() {

	}

	public Indicator(String name, String expression ) {
		this.name = name;
		this.expression = expression;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpression() {
		return this.expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
}