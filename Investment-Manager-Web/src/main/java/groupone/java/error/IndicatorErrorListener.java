package groupone.java.error;

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
			errorMessage.append(String.format(Messages.getString("IndicatorErrorListener.syntaxErrorMessage"), line, charPositionInLine)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} else if (e instanceof LexerNoViableAltException ) {
			errorMessage.append(String.format(Messages.getString("IndicatorErrorListener.notValidCharacter"), line, charPositionInLine)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} else if(e instanceof FailedPredicateException || e instanceof NoViableAltException ) {
			errorMessage.append(String.format(Messages.getString("IndicatorErrorListener.errorOnLine"), line, charPositionInLine)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} 
		
	}
}