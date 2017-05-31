package groupone.java.investment;

import grammar.*;

import java.io.IOException;
import java.lang.Exception;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {

    public static void main(String[] args) throws Exception {

    	String nuevoIndicador = "OtroIndicador=50\r\n(2*2)+OtroIndicador\r\n";

        IndicatorGrammarLexer lexer = new IndicatorGrammarLexer(new ANTLRInputStream(nuevoIndicador));
        IndicatorGrammarParser parser = new IndicatorGrammarParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.prog();
        EvalVisitor visitor = new EvalVisitor();
        visitor.visit(tree);
        
        String path = "/cuentas.json";
		
		AccountManager accountLoader = new AccountManager();
		
		try{
			accountLoader.agregarCuentas(path);
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
		
		accountLoader.imprimirCuentas();
    }
}
