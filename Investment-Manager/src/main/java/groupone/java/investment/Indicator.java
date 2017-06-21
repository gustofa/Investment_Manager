package groupone.java.investment;

import org.antlr.v4.runtime.tree.ParseTree;

public class Indicator {

	private String name;
	private ParseTree parseTree;

	public Indicator() {

	}

	public Indicator(String name, ParseTree parseTree) {
		this.name = name;
		this.parseTree = parseTree;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setParseTree(ParseTree parseTree) {
		this.parseTree = parseTree;
	}

	public Double apply(String company, String year) {
		EvalVisitor visitor = new EvalVisitor();
		return visitor.visit(this.parseTree, company, year);
	}
}