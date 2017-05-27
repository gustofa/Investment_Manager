package ar.edu.utn.dds.antlr;

import ar.edu.utn.dds.*;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ParseoYOperadoresTest {

    public static final String INPUT_PATH = "/input.txt";

    @Test
    public void testOperadores() {
        IOperador sumar = new OperadorSUM();

        IExpresion termino1 = new Termino(1);
        IExpresion termino2 = new Termino(4);

        /// pruebo el operador SUM
        Assert.assertEquals(5, sumar.operar(termino1, termino2), 0.01);

        IExpresion expresionAuxiliar = new ExpresionCompuesta(termino1, termino2, sumar);
        IExpresion expresionFinal = new ExpresionCompuesta(expresionAuxiliar, new Termino(5), sumar);

        // pruebo expresiones compuestas
        Assert.assertEquals(10, expresionFinal.getResultado(), 0.01);
    }

    @Test
    public void testGramatica() throws IOException {
        String file = this.getInputFilePath();

        // ---- Esto es para evaluar una gramatica y crear expresiones -----------
        DemoCalcLexer lexer = new DemoCalcLexer(new ANTLRFileStream(file));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DemoCalcParser parser = new DemoCalcParser(tokens);
        DemoCalcParser.IndicadorContext indicadorContext = parser.indicador();
        ParseTreeWalker walker = new ParseTreeWalker();
        Listener listener = new Listener();
        walker.walk(listener, indicadorContext);

        Assert.assertEquals(6, listener.getExpresion().getResultado(), 0.01);

    }

    private String getInputFilePath() {
        return this.getClass().getResource(INPUT_PATH).getPath();
    }
}
