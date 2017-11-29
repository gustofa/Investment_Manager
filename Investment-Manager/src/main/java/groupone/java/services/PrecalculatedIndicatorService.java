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
import groupone.java.app.EvalVisitor;
import groupone.java.bean.Account;
import groupone.java.bean.Company;
import groupone.java.bean.Indicator;
import groupone.java.bean.PrecalculatedIndicator;
import groupone.java.bean.User;
import groupone.java.error.IndicatorErrorListener;
import groupone.java.error.IndicatorSyntaxException;
import groupone.java.repositories.Repository;

@SuppressWarnings("deprecation")
public class PrecalculatedIndicatorService {

	private static final String PERSISTENCE_UNIT_NAME = "DDS";
	private static PrecalculatedIndicatorService instance;
	private Repository repository;
	
	private PrecalculatedIndicatorService() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		this.repository = new Repository(emFactory.createEntityManager());		 
	};

	public static PrecalculatedIndicatorService getInstance() {
		if (instance == null) {
			instance = new PrecalculatedIndicatorService();
		}

		return instance;
	}
	
	public List<PrecalculatedIndicator> getPrecalculatedIndicators() {
		return  repository.precalculatedIndicators().getPrecalculatedIndicators();
	}
	
}
