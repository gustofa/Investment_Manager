// Generated from ar/edu/utn/dds/antlr/DemoCalc.g4 by ANTLR 4.7
package ar.edu.utn.dds.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DemoCalcParser}.
 */
public interface DemoCalcListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DemoCalcParser#indicador}.
	 * @param ctx the parse tree
	 */
	void enterIndicador(DemoCalcParser.IndicadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DemoCalcParser#indicador}.
	 * @param ctx the parse tree
	 */
	void exitIndicador(DemoCalcParser.IndicadorContext ctx);
}