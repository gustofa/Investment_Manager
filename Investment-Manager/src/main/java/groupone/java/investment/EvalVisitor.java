package groupone.java.investment;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

import grammar.IndicatorGrammarBaseVisitor;
import grammar.IndicatorGrammarParser;
import groupone.java.bean.Account;
import groupone.java.bean.Company;
import groupone.java.bean.Indicator;
import groupone.java.error.Messages;
import groupone.java.services.CompanyService;
import groupone.java.services.IndicatorService;

@SuppressWarnings("deprecation")
public class EvalVisitor extends IndicatorGrammarBaseVisitor<Double> {

	// used to compare floating point numbers
	public static final double SMALL_VALUE = 0.00000000001;

	// store variables (there's only one global scope!)
	private Map<String, String> memory = new HashMap<String, String>();
	private Company company;
	private String year;

	public Double visit(ParseTree parseTree, Company company, String year) {
		this.company = company;
		this.year = year;
		return super.visit(parseTree);
	}

	@Override
	public Double visitAccount(IndicatorGrammarParser.AccountContext ctx) {
		Double value;
		String accountName = ctx.getText().replace("$", "");
		CompanyService compService = CompanyService.getInstance();
		
		Account account = compService.getAccount(accountName, this.year, this.company);
		 
		if (account == null) {
			throw new ParseCancellationException(String.format(Messages.getString("EvalVisitor.accountNotFound"), accountName)); //$NON-NLS-1$
		} else {
			value = account.getValue();
		}

		return value;
	}

	// assignment/id overrides
	@Override
	public Double visitAssign(IndicatorGrammarParser.AssignContext ctx) {
		String id = ctx.ID().getText();
		Double value = this.visit(ctx.expr());
		memory.put(id, value.toString());
		return value;
	}

	// expression
	@Override
	public Double visitExpression(IndicatorGrammarParser.ExpressionContext ctx) {
		return this.visit(ctx.expr());
	}

	@Override
	public Double visitIdAtom(IndicatorGrammarParser.IdAtomContext ctx) {
		String id = ctx.ID().getText();
		Double value = memory.get(id) != null ? Double.parseDouble(memory.get(id)) : null;
		
		if (value == null) {
			Indicator embebedIndicator = IndicatorService.getInstance().getIndicator(id);
			if (embebedIndicator == null) {
				throw new ParseCancellationException(String.format(Messages.getString("EvalVisitor.indicatorNotFound"), id)); //$NON-NLS-1$
			} else {
				value = IndicatorService.getInstance().apply(company, year,embebedIndicator);
			}
		}

		return value;
	}

	@Override
	public Double visitNumberAtom(IndicatorGrammarParser.NumberAtomContext ctx) {
		return Double.valueOf(ctx.getText());
	}

	// expr overrides
	@Override
	public Double visitParen(IndicatorGrammarParser.ParenContext ctx) {
		return this.visit(ctx.expr());
	}

	@Override
	public Double visitUnaryMinusExpr(IndicatorGrammarParser.UnaryMinusExprContext ctx) {
		return -this.visit(ctx.expr());
	}

	@Override
	public Double visitMultiplicationExpr(@NotNull IndicatorGrammarParser.MultiplicationExprContext ctx) {
		Double left = this.visit(ctx.expr(0));
		Double right = this.visit(ctx.expr(1));

		switch (ctx.op.getType()) {
		case IndicatorGrammarParser.MULT:
			return left * right;
		case IndicatorGrammarParser.DIV:
			return left / right;
		case IndicatorGrammarParser.MOD:
			return left % right;
		default:
			throw new ParseCancellationException("unknown operator: " + IndicatorGrammarParser.tokenNames[ctx.op.getType()]);
		}
	}

	@Override
	public Double visitAdditiveExpr(@NotNull IndicatorGrammarParser.AdditiveExprContext ctx) {
		Double left = this.visit(ctx.expr(0));
		Double right = this.visit(ctx.expr(1));

		switch (ctx.op.getType()) {
		case IndicatorGrammarParser.PLUS:
			return left + right;
		case IndicatorGrammarParser.MINUS:
			return left - right;
		default:
			throw new ParseCancellationException("unknown operator: " + IndicatorGrammarParser.tokenNames[ctx.op.getType()]);
		}
	}
}
