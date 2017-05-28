package src.test.java;

import static org.junit.Assert.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;
import grammar.IndicatorGrammarLexer;
import grammar.IndicatorGrammarParser;
import groupone.java.indicator.EvalVisitor;

public class IndicatorUnitTests {
	@Test
	public void testGramatica() {
		String nuevoIndicador = "OtroIndicador=50\r\n(2*2)+OtroIndicador\r\n";

		System.out.println("parsing: " + nuevoIndicador);

		IndicatorGrammarLexer lexer = new IndicatorGrammarLexer(new ANTLRInputStream(nuevoIndicador));
		IndicatorGrammarParser parser = new IndicatorGrammarParser(new CommonTokenStream(lexer));
		ParseTree tree = parser.prog();
		EvalVisitor visitor = new EvalVisitor();
		visitor.visit(tree);

		Assert.assertTrue(true);
	}
}
