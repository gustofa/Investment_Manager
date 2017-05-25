// Generated from IndicatorGrammar.g4 by ANTLR 4.4
package main.g4.grammar;

	
	

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IndicatorGrammarParser}.
 */
public interface IndicatorGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IndicatorGrammarParser#indicador}.
	 * @param ctx the parse tree
	 */
	void enterIndicador(@NotNull IndicatorGrammarParser.IndicadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicatorGrammarParser#indicador}.
	 * @param ctx the parse tree
	 */
	void exitIndicador(@NotNull IndicatorGrammarParser.IndicadorContext ctx);
	/**
	 * Enter a parse tree produced by {@link IndicatorGrammarParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(@NotNull IndicatorGrammarParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicatorGrammarParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(@NotNull IndicatorGrammarParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link IndicatorGrammarParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull IndicatorGrammarParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicatorGrammarParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull IndicatorGrammarParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link IndicatorGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(@NotNull IndicatorGrammarParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicatorGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(@NotNull IndicatorGrammarParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link IndicatorGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(@NotNull IndicatorGrammarParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicatorGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(@NotNull IndicatorGrammarParser.FactorContext ctx);
}