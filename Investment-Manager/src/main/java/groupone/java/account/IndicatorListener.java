package groupone.java.account;
import antlr.java.*;

public class IndicatorListener extends IndicatorGrammarBaseListener {
	public IndicatorListener() {
		super();
	}
	
	@Override
	public void enterIndicador(IndicatorGrammarParser.IndicadorContext ctx) {
		System.out.println("Hello world!");
	}

}