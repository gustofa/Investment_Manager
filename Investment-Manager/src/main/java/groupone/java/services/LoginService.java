package groupone.java.services;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.FilenameUtils;
import grammar.IndicatorGrammarLexer;
import grammar.IndicatorGrammarParser;
import groupone.java.app.EvalVisitor;
import groupone.java.bean.Company;
import groupone.java.bean.Indicator;
import groupone.java.bean.User;
import groupone.java.error.IndicatorErrorListener;
import groupone.java.error.IndicatorSyntaxException;
import groupone.java.repositories.Repository;

@SuppressWarnings("deprecation")
public class LoginService {

	private static final String PERSISTENCE_UNIT_NAME = "DDS";
	private static LoginService instance;
	private Repository repository;
	
	private LoginService() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		this.repository = new Repository(emFactory.createEntityManager());		 
	};

	public static LoginService getInstance() {
		if (instance == null) {
			instance = new LoginService();
		}

		return instance;
	}
	
	public boolean authenticate(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        
        User user = this.repository.users().findByName(username);
        if (user == null) {
            return false;
        }
        
        return Objects.equals(user.getPassword(),password); 
    };
}
