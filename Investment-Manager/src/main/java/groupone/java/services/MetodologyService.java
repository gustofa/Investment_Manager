package groupone.java.services;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import groupone.java.bean.User;
import groupone.java.bean.Metodology;
import groupone.java.error.IndicatorErrorListener;
import groupone.java.error.IndicatorSyntaxException;
import groupone.java.repositories.Repository;
import groupone.java.repositories.Metodologies;

@SuppressWarnings("deprecation")
public class MetodologyService {

	private static final String PERSISTENCE_UNIT_NAME = "DDS";
	private static MetodologyService instance;
	private Repository repository;
	
	private MetodologyService() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		this.repository = new Repository(emFactory.createEntityManager());		 
	};

	public static MetodologyService getInstance() {
		if (instance == null) {
			instance = new MetodologyService();
		}

		return instance;
	}
	
	
	public List<Metodology> getMetodologies(String username) {
		User user = this.repository.users().findByName(username);
		return  repository.metodologies().getMetodologies(user.getId());
	}	

	public Metodology createMetodology(String name, String expression, String username){
		User user = this.repository.users().findByName(username);
		
		Metodology metodology = new Metodology();
		metodology.setName(name);
		metodology.setExpression(expression);		
		metodology.setUserId(user.getId());		
		this.repository.metodologies().persist(metodology); 
		return metodology;
	}
}
