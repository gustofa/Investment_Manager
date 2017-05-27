package ar.edu.utn.dds;


public class OperadorSUM  implements IOperador{

    public String getSimbolo() {
        return "SUM";
    }

    public double operar(IExpresion expresion1, IExpresion expresion2) {
        return expresion1.getResultado() + expresion2.getResultado();
    }
}
