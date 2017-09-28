package groupone.java.services;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.FilenameUtils;
import grammar.IndicatorGrammarLexer;
import grammar.IndicatorGrammarParser;
import groupone.java.bean.Company;
import groupone.java.bean.Indicator;
import groupone.java.error.IndicatorErrorListener;
import groupone.java.error.IndicatorSyntaxException;
import groupone.java.investment.EvalVisitor;
import groupone.java.repositories.Repository;

@SuppressWarnings("deprecation")
public class IndicatorService {

	private static final String PERSISTENCE_UNIT_NAME = "DDS";
	private static IndicatorService instance;
	private Repository repository;
	
	private IndicatorService() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		this.repository = new Repository(emFactory.createEntityManager());		 
	};

	public static IndicatorService getInstance() {
		if (instance == null) {
			instance = new IndicatorService();
		}

		return instance;
	}
	
	public Double apply(Company company, String year, Indicator indicator) {
		EvalVisitor visitor = new EvalVisitor();
		ParseTree parseTree;
		Double evaluationResult = null;
		
		try {
			parseTree = this.parseExpression(indicator.getExpression());
			evaluationResult = visitor.visit(parseTree, company, year);
		} catch (IndicatorSyntaxException e) {
			e.printStackTrace();
		}
		
		return evaluationResult;
	}
	
	public List<Indicator> getIndicators() {
		return  repository.indicators().getIndicators();
	}	
	
	public Indicator getIndicator(String name) {
		return repository.indicators().findByName(name);
	}
	
	public Indicator createIndicator(String name, String expression) throws IndicatorSyntaxException{
		Indicator indicator = new Indicator();
		indicator.setName(name);
		this.parseExpression(expression);
		indicator.setExpression(expression);		
		this.repository.indicators().persist(indicator); 
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
