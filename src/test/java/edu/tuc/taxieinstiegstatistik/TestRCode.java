package edu.tuc.taxieinstiegstatistik;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.Vector;

/*
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;
*/

public class TestRCode
{

    private REngine m_engine;

    @Before
    public void initialize()
    {
        m_engine = new REngine();
    }


    @Test
	public void sequence()
    {
        System.out.println( "Sequenz" );
        IntStream.range(
                0,
                m_engine
                        // ausführen des Scripts
                        .evaluate("x <- seq(9)", "y <- x*2")
                        // Länge der Rückgabe ermitteln
                        .length()
        )
                // über das Ergebnis iterieren
                .forEach(i ->
                    System.out.println(
                        m_engine.
                                // Ergebnis in den Zieltyp casten
                                <Vector>result()
                                // elementweise auslesen
                                .getElementAsDouble(i)
                    )
                );
    }


    @Test
    public void variablesequence()
    {
        System.out.println( "Variablensequenz mit Parameter" );
        IntStream.range(
                0,
                m_engine
                        // Variable "i" des Scripts setzen
                        .variable("i", 5)
                        // Script ausführen
                        .evaluate("x <- seq(i)")
                        // Länge der Rückgabe ermitteln
                        .length()
        )
                // über das Ergebnis iterieren
                .forEach(i ->
                    System.out.println(
                        m_engine.
                                // Ergebnis in den Zieltyp casten
                                <Vector>result()
                                // elementweise auslesen
                                .getElementAsDouble(i)
                    )
                );
    }

}
