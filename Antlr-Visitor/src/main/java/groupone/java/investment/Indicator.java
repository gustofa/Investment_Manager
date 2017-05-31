package groupone.java.investment;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

import grammar.IndicatorGrammarLexer;
import grammar.IndicatorGrammarParser;


public class Indicator implements AbstractIndicator{
	  
	private String name;
	private String expression;
	
	public Indicator(){
		
	}
	
	public Indicator(String name, String expression) {
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
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public Double getResult(String empresa, String anio){
		
		String nueva_expresion = getExpression();
		nueva_expresion = "ParamEmpresa="+empresa+"\r\n"+"ParamAnio="+anio+"\r\n"+nueva_expresion;
		
        @SuppressWarnings("deprecation")
		IndicatorGrammarLexer lexer = new IndicatorGrammarLexer(new ANTLRInputStream(nueva_expresion));
        IndicatorGrammarParser parser = new IndicatorGrammarParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.prog();
        EvalVisitor visitor = new EvalVisitor();
        return visitor.visit(tree);
        		
	}
	
	
	public String validateSyntax() throws Throwable{
		IndicatorErrorListener indicatorErrorListener = new IndicatorErrorListener(); 
		@SuppressWarnings("deprecation")
		IndicatorGrammarLexer lexer = new IndicatorGrammarLexer(new ANTLRInputStream(this.getExpression()));
		lexer.addErrorListener(indicatorErrorListener);
		IndicatorGrammarParser parser = new IndicatorGrammarParser(new CommonTokenStream(lexer));
		parser.addErrorListener(indicatorErrorListener);
		ParseTree tree = parser.prog();
		EvalVisitor visitor = new EvalVisitor();
		
		try{
			visitor.visit(tree);
			return "ok";
		}catch(final ParseCancellationException e){
			throw new Exception(e.getMessage());

		}
	}

}