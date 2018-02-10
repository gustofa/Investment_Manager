package groupone.java.bean;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import org.antlr.v4.runtime.tree.ParseTree;

@Entity
@Table(name = "PrecalculatedIndicator")
public class PrecalculatedIndicator extends Persistible {
	private static final long serialVersionUID = 1L;
	@JoinColumn(name = "indicator_id", referencedColumnName = "id")
	private long indicator_id;
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	private long company_id;
	private String year;
	private double value;
	
	public PrecalculatedIndicator() {

	}

	public PrecalculatedIndicator(long indicator_id, long company_id, String year, double value ) {
		this.indicator_id = indicator_id;
		this.company_id = company_id;
		this.year = year;
		this.value = value;
	}

	public long getIndicatorId() {
		return this.indicator_id;
	}

	public void setIndicatorId(long indicator_id) {
		this.indicator_id = indicator_id;
	}
	
	public long getCompanyId() {
		return this.company_id;
	}

	public void setCompanyId(long company_id) {
		this.company_id = company_id;
	}
	
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public double getValue() {
		return this.value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
}
