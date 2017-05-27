package ar.edu.utn.dds;

public class OperadorRES implements IOperador {


    public String getSimbolo() {
        return "RES";
    }

    public double operar(IExpresion expresion1, IExpresion expresion2) {
        if(expresion1 == null || expresion2 == null) {
            throw new RuntimeException("No estan definidos todos los elementos para operar");
        }
        return expresion1.getResultado() - expresion2.getResultado();
    }
}
