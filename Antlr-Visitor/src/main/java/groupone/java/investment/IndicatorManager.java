package groupone.java.investment;

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

	public void loadPredefinedIndicators() throws IOException, IndicatorSyntaxException {

		// Get files from file system
		String predefinedIndicatorsFolder = getClass().getResource("/predefinedIndicators").getFile();
		File[] predefinedIndicators = new File(predefinedIndicatorsFolder).listFiles();

		// Creates and load predefined indicators
		for (File indicatorFile : predefinedIndicators) {
			Indicator newIndicator = new Indicator();
			String indicatorName = FilenameUtils.getBaseName(indicatorFile.getName());
			newIndicator.setName(indicatorName);
			String expression = new String(Files.readAllBytes(Paths.get(indicatorFile.getPath())),
					StandardCharsets.UTF_8);
			ParseTree parseTree = this.parseExpression(expression);
			newIndicator.setParseTree(parseTree);
			this.indicators.put(newIndicator.getName(), newIndicator);
		}
	}

	public Indicator loadNewIndicatorFromFile(String pathfile) throws IOException, IndicatorSyntaxException {

		// Get file from file system
		File fileNewIndicator = new File(pathfile);

		// Creates and load predefined indicators
		Indicator newIndicator = new Indicator();
		String indicatorName = FilenameUtils.getBaseName(fileNewIndicator.getName());
		newIndicator.setName(indicatorName);
		String expression = new String(Files.readAllBytes(Paths.get(fileNewIndicator.getPath())),
				StandardCharsets.UTF_8);
		ParseTree parseTree = this.parseExpression(expression);
		newIndicator.setParseTree(parseTree);
		this.indicators.put(newIndicator.getName(), newIndicator);

		return newIndicator;
	}

	public ParseTree parseExpression(String expression) throws IndicatorSyntaxException {
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
