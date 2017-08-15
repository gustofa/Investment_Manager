package groupone.java.services;

import groupone.java.bean.Company;
import groupone.java.bean.Indicator;
import groupone.java.investment.EvalVisitor;

public class IndicatorService {
	
	public Double apply(Company company, String year, Indicator indicator) {
		EvalVisitor visitor = new EvalVisitor();
		return visitor.visit(indicator.getParseTree(), company, year);
	}

}
