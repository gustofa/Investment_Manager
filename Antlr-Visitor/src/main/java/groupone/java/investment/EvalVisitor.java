package groupone.java.investment;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;
import java.util.Map;
import java.lang.Double;

import grammar.*;

@SuppressWarnings("deprecation")
public class EvalVisitor extends IndicatorGrammarBaseVisitor<Double> {

	// used to compare floating point numbers
	public static final double SMALL_VALUE = 0.00000000001;

	// store variables (there's only one global scope!)
	private Map<String, String> memory = new HashMap<String, String>();
	private String company;
	private String year;

	public Double visit(ParseTree parseTree, String company, String year) {
		this.company = company;
		this.year = year;
		return super.visit(parseTree);
	}

	// count
	@Override
	public Double visitAccount(IndicatorGrammarParser.AccountContext ctx) {
		Double value;
		String account = ctx.getText().replace("$", "");
		System.out.println("la cuenta "+account+" de la empresa "+this.company+" del año "+this.year);
		Account unaCuenta = AccountList.findAccount(account, this.company, this.year);
		if (unaCuenta == null) {
			throw new RuntimeException("Cuenta no encontrada: " + account);
		} else {
			value = unaCuenta.getValor();
		}

		return value;
	}

	// assignment/id overrides
	@Override
	public Double visitAssign(IndicatorGrammarParser.AssignContext ctx) {
		String id = ctx.ID().getText();
		Double value = this.visit(ctx.expr());
		// System.out.println("id - valor: "+id+" - "+value.toString());
		memory.put(id, value.toString());
		return value;
	}

	// expression
	@Override
	public Double visitExpression(IndicatorGrammarParser.ExpressionContext ctx) {
		// String id = ctx.getText();
		Double value = this.visit(ctx.expr());
		// System.out.println(value);
		return value;
	}

	@Override
	public Double visitIdAtom(IndicatorGrammarParser.IdAtomContext ctx) {
		String id = ctx.ID().getText();
		// System.out.println(id);

		Double value = memory.get(id) != null ? Double.parseDouble(memory.get(id)) : null;
		if (value == null) {
			// throw new RuntimeException("no such variable: " + id);
			// si es no está en memory es porque no está calculado en líneas
			// anteriores de la expresión
			// se estima entonces que es un indicador
			Indicator otroIndicador = IndicatorManager.getInstance().getIndicator(id);
			if (otroIndicador == null) {
				throw new RuntimeException("Indicador no encontrado: " + id);
			} else {
				String empresa = memory.get("ParamEmpresa");
				String anio = memory.get("ParamAnio");
				value = otroIndicador.apply(empresa, anio);
			}
		}

		return value;
	}

	@Override
	public Double visitNumberAtom(IndicatorGrammarParser.NumberAtomContext ctx) {
		// System.out.println("visitNumberAtom");
		Double value = Double.valueOf(ctx.getText());
		return value;
	}

	// expr overrides
	@Override
	public Double visitParen(IndicatorGrammarParser.ParenContext ctx) {
		return this.visit(ctx.expr());
	}

	@Override
	public Double visitUnaryMinusExpr(IndicatorGrammarParser.UnaryMinusExprContext ctx) {
		Double value = -(this.visit(ctx.expr()));

		return value;
	}

	@Override
	public Double visitMultiplicationExpr(@NotNull IndicatorGrammarParser.MultiplicationExprContext ctx) {
		// System.out.println("visitMultiplicationExpr");
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
			throw new RuntimeException("unknown operator: " + IndicatorGrammarParser.tokenNames[ctx.op.getType()]);
		}
	}

	@Override
	public Double visitAdditiveExpr(@NotNull IndicatorGrammarParser.AdditiveExprContext ctx) {
		// System.out.println("visitAdditiveExpr");
		Double left = this.visit(ctx.expr(0));
		Double right = this.visit(ctx.expr(1));

		switch (ctx.op.getType()) {
		case IndicatorGrammarParser.PLUS:
			return left + right;
		case IndicatorGrammarParser.MINUS:
			return left - right;
		default:
			throw new RuntimeException("unknown operator: " + IndicatorGrammarParser.tokenNames[ctx.op.getType()]);
		}
	}

}