package groupone.java.investment;

import org.antlr.v4.runtime.tree.ParseTree;

public interface AbstractIndicator {

	String getName();

	void setName(String name);

	void setParseTree(ParseTree parseTee);
}
