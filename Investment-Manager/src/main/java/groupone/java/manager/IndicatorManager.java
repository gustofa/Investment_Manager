package groupone.java.manager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.FilenameUtils;
import grammar.IndicatorGrammarLexer;
import grammar.IndicatorGrammarParser;
import groupone.java.bean.Indicator;
import groupone.java.error.IndicatorErrorListener;
import groupone.java.error.IndicatorSyntaxException;

public class IndicatorManager {

	private static IndicatorManager instance;
	private HashMap<String, Indicator> indicators = new HashMap<String, Indicator>();

	private IndicatorManager() {
	};

	public static IndicatorManager getInstance() {
		if (instance == null) {
			instance = new IndicatorManager();
		}

		return instance;
	}

	public Indicator getIndicator(String name) {
		return this.indicators.get(name);
	}
	
	public Indicator createIndicator(String name, String expression) throws IndicatorSyntaxException{
		Indicator indicator = new Indicator();
		indicator.setName(name);
		ParseTree parseTree = this.parseExpression(expression);
		indicator.setParseTree(parseTree);
		
		this.indicators.put(name, indicator);
		return indicator;
	}

	public void loadPredefinedIndicators() throws IOException, IndicatorSyntaxException {
		// Gets files from file system
		String predefinedIndicatorsFolder = getClass().getResource("/predefinedIndicators").getFile();
		File[] predefinedIndicators = new File(predefinedIndicatorsFolder).listFiles();
		for (File indicatorFile : predefinedIndicators) {
			this.createIndicator(indicatorFile);
		}
	}

	public Indicator loadNewIndicatorFromFile(String pathfile) throws IOException, IndicatorSyntaxException {
		File fileNewIndicator = new File(pathfile);
		return this.createIndicator(fileNewIndicator);
	}
	
	private Indicator createIndicator(File file) throws IOException, IndicatorSyntaxException{
		String indicatorName = FilenameUtils.getBaseName(file.getName());
		String expression = new String(Files.readAllBytes(Paths.get(file.getPath())),
				StandardCharsets.UTF_8);
		
		return this.createIndicator(indicatorName, expression);
	}

	private ParseTree parseExpression(String expression) throws IndicatorSyntaxException {
		IndicatorErrorListener indicatorErrorListener = new IndicatorErrorListener();
		@SuppressWarnings("deprecation")
		IndicatorGrammarLexer lexer = new IndicatorGrammarLexer(new ANTLRInputStream(expression));
		lexer.addErrorListener(indicatorErrorListener);
		IndicatorGrammarParser parser = new IndicatorGrammarParser(new CommonTokenStream(lexer));
		parser.addErrorListener(indicatorErrorListener);
		ParseTree treeParse = parser.prog();

		if (indicatorErrorListener.hasErrors()) {
			throw new IndicatorSyntaxException(indicatorErrorListener.getErrorMessage());
		}

		return treeParse;
	}
}
