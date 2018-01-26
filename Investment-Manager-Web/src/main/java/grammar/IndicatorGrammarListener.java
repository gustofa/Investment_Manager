// Generated from grammar\IndicatorGrammar.g4 by ANTLR 4.7
package grammar;



import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IndicatorGrammarParser}.
 */
public interface IndicatorGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IndicatorGrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(IndicatorGrammarParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link IndicatorGrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(IndicatorGrammarParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expression}
	 * labeled alternative in {@link IndicatorGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterExpression(IndicatorGrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expression}
	 * labeled alternative in {@link IndicatorGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitExpression(IndicatorGrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link IndicatorGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssign(IndicatorGrammarParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link IndicatorGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssign(IndicatorGrammarParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blank}
	 * labeled alternative in {@link IndicatorGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBlank(IndicatorGrammarParser.BlankContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blank}
	 * labeled alternative in {@link IndicatorGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBlank(IndicatorGrammarParser.BlankContext ctx);
	/**
	 * Enter a parse tree produced by the {@code paren}
	 * labeled alternative in {@link IndicatorGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParen(IndicatorGrammarParser.ParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code paren}
	 * labeled alternative in {@link IndicatorGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParen(IndicatorGrammarParser.ParenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryMinusExpr}
	 * labeled alternative in {@link IndicatorGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMinusExpr(IndicatorGrammarParser.UnaryMinusExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryMinusExpr}
	 * labeled alternative in {@link IndicatorGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMinusExpr(IndicatorGrammarParser.UnaryMinusExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplicationExpr}
	 * labeled alternative in {@link IndicatorGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicationExpr(IndicatorGrammarParser.MultiplicationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplicationExpr}
	 * labeled alternative in {@link IndicatorGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicationExpr(IndicatorGrammarParser.MultiplicationExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link IndicatorGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpr(IndicatorGrammarParser.AtomExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link IndicatorGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpr(IndicatorGrammarParser.AtomExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code additiveExpr}
	 * labeled alternative in {@link IndicatorGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr(IndicatorGrammarParser.AdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code additiveExpr}
	 * labeled alternative in {@link IndicatorGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr(IndicatorGrammarParser.AdditiveExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numberAtom}
	 * labeled alternative in {@link IndicatorGrammarParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterNumberAtom(IndicatorGrammarParser.NumberAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numberAtom}
	 * labeled alternative in {@link IndicatorGrammarParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitNumberAtom(IndicatorGrammarParser.NumberAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idAtom}
	 * labeled alternative in {@link IndicatorGrammarParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterIdAtom(IndicatorGrammarParser.IdAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idAtom}
	 * labeled alternative in {@link IndicatorGrammarParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitIdAtom(IndicatorGrammarParser.IdAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Account}
	 * labeled alternative in {@link IndicatorGrammarParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAccount(IndicatorGrammarParser.AccountContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Account}
	 * labeled alternative in {@link IndicatorGrammarParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAccount(IndicatorGrammarParser.AccountContext ctx);
}