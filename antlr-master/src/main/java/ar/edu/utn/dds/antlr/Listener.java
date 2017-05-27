package ar.edu.utn.dds.antlr;

import ar.edu.utn.dds.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;
import java.util.Map;

public class Listener extends DemoCalcBaseListener {

    private Map<String, IOperador> operadores;
    private ExpresionCompuesta expresionActual;

    public Listener() {
        super();

        this.cargarOperadores();
    }



    @Override
    public void enterIndicador(DemoCalcParser.IndicadorContext ctx) {

        // reinicio la expresion actual
        this.expresionActual = new ExpresionCompuesta();

        this.iterateNodes(ctx);
    }

    public IExpresion getExpresion() {
        return this.expresionActual;
    }

    private void iterateNodes(ParserRuleContext ctx) {
        for(int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree child = ctx.getChild(i);

            if(child instanceof ErrorNode){
                System.err.printf("Error en nodo: %s\n", child.getText());


                // todo: manejar el error

            } else {
                System.out.printf("Nodo valido: %s\n", child.getText());

                // si el texto de este child matchea con un operador
                IOperador operador = this.operadores.get(child.getText());
                if(operador != null){
                    this.expresionActual.setOperador(operador);
                } else {
                    // si no es operador, es un termino
                    Termino termino = new Termino(Double.parseDouble(child.getText()));

                    // lo seteo en la expresion
                    if(expresionActual.getExpresion1() == null) {
                        this.expresionActual.setExpresion1(termino);
                    } else {
                        this.expresionActual.setExpresion2(termino);
                    }
                }
            }
        }
    }


    private void cargarOperadores() {
        IOperador sumar = new OperadorSUM();
        IOperador restar = new OperadorRES();

        this.operadores = new HashMap<String, IOperador>();
        this.operadores.put(sumar.getSimbolo(), sumar);
        this.operadores.put(restar.getSimbolo(), restar);
    }

}
