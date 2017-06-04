package groupone.java.investment;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.LexerNoViableAltException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class IndicatorErrorListener extends BaseErrorListener {
	public IndicatorErrorListener() {
	}

	private StringBuilder errorMessage = new StringBuilder("");
	
	public String getErrorMessage() {
		return errorMessage.toString();
	}
	
	public boolean hasErrors(){
		return this.getErrorMessage().length() > 0;
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		
		if(e instanceof InputMismatchException ){
			errorMessage.append("Error sintactico en la formula del indicador, en la linea " + line + ", caracter nº: " + charPositionInLine  + "\r\n");
		} else if (e instanceof LexerNoViableAltException ) {
			errorMessage.append("Caracter no valido en la linea " + line +", caracter nº: " + charPositionInLine + "\r\n");
		} else if(e instanceof FailedPredicateException ) {
			errorMessage.append("Error en la linea " + line + ", caracter nº: " + charPositionInLine  + "\r\n");
		} else if (e instanceof NoViableAltException ){
			errorMessage.append("Error en la linea " + line + ", caracter nº: " + charPositionInLine  + "\r\n");
		}
	}
}