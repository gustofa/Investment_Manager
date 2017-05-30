package groupone.java.indicator;
//package dds;


import grammar.*;
import java.lang.Exception;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {

    public static void main(String[] args) throws Exception {

//        if (args.length == 0) {
//            args = new String[]{"src/main/java/test."};
//        }
    	String nuevoIndicador = "OtroIndicador=50\r\n(2*2)+OtroIndicador\r\n";
    	
       // System.out.println("parsing: " + nuevoIndicador);

        IndicatorGrammarLexer lexer = new IndicatorGrammarLexer(new ANTLRInputStream(nuevoIndicador));
        IndicatorGrammarParser parser = new IndicatorGrammarParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.prog();
        EvalVisitor visitor = new EvalVisitor();
        visitor.visit(tree);

    }
}
