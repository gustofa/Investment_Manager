package groupone.java.indicator;
//package dds;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Double;

import grammar.*;

public class EvalVisitor extends IndicatorGrammarBaseVisitor<Double> {

    // used to compare floating point numbers
    public static final double SMALL_VALUE = 0.00000000001;

    // store variables (there's only one global scope!)
    private Map<String, Double> memory = new HashMap<String, Double>();

    // count
    @Override
    public Double visitCount(IndicatorGrammarParser.CountContext ctx) {
        String count = ctx.getText();
       //TODO aca habría que ir a buscar esta cuenta en las cuentas de la empresa
        // y devolver su valor
        System.out.println("se detectó la cuenta: "+count);
        return 1.00;
    }    
    
    // assignment/id overrides
    @Override
    public Double visitAssign(IndicatorGrammarParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        Double value = this.visit(ctx.expr());
       // System.out.println("id - valor: "+id+" - "+value.toString());
        memory.put(id, value);
        return value;
    }    
    // expression
    @Override
    public Double visitExpression(IndicatorGrammarParser.ExpressionContext ctx) {
        //String id = ctx.getText();
        Double value = this.visit(ctx.expr());
        System.out.println(value);
        return 0.00;
    }

    @Override
    public Double visitIdAtom(IndicatorGrammarParser.IdAtomContext ctx) {
        String id = ctx.ID().getText();
        //System.out.println(id);
        Double value = memory.get(id);
        if(value == null) {
            throw new RuntimeException("no such variable: " + id);
        }
//        if(id.equals("Indicador")){
//        	return new Value(Double.valueOf(100));
//        } else {
//        	throw new RuntimeException("no such variable: " + id);
//        }
        return value;
    }


    @Override
    public Double visitNumberAtom(IndicatorGrammarParser.NumberAtomContext ctx) {
    	//System.out.println("visitNumberAtom");
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
    	//System.out.println("visitMultiplicationExpr");
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
    	//System.out.println("visitAdditiveExpr");
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
