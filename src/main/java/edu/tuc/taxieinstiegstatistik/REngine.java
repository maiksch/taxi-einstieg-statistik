package edu.tuc.taxieinstiegstatistik;

import org.renjin.sexp.SEXP;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * gekapselte R Engine
 * @see http://docs.renjin.org/en/latest
 */
public final class REngine
{
    /**
     * Scripting-Engine
     */
    private static final ScriptEngineManager SCRIPTENGINE = new ScriptEngineManager();
    /**
     * Engine Instanz
     */
    private final ScriptEngine m_engine = SCRIPTENGINE.getEngineByName( "Renjin" );
    /**
     * Return Objekt eines Evaluate Aufrufs
     */
    private SEXP m_result;


    /**
     * führt ein Script aus, das aus einer Folge von Strings besteht
     *
     * @param p_scriptline Scriptzeilen
     * @return Eigenreferenz auf das Objekt
     */
    @SuppressWarnings( "unchecked" )
    public final REngine evaluate( final String... p_scriptline )
    {
        Arrays.stream( p_scriptline ).forEach( i -> {
            try {
                m_result = (SEXP) m_engine.eval( i );
            } catch ( final ScriptException l_exception )
            {
                throw new RuntimeException( l_exception );
            }
        });

        return this;
    }

    /**
     * führt ein Script aus, das aus einem beliebigen Stream gelesen wird
     *
     * @param p_input Eingabestream
     * @return Eigenreferenz auf das Objekt
     */
    @SuppressWarnings( "unchecked" )
    public final REngine evalute(final InputStreamReader p_input )
    {
        try {
            m_result = (SEXP) m_engine.eval( p_input );
        } catch ( final ScriptException l_exception )
        {
            throw new RuntimeException( l_exception );
        }

        return this;
    }

    /**
     * prüft, ob das Result eines Evaluates-Aufrufs auf einen
     * bestimmten Klassentyp zuweisen lässt
     *
     * @param p_class Klassentyp, auf den zugewiesen werden soll
     * @return Boolean, ob Zuweisung möglich ist
     */
    public final boolean resultassignableto( final Class<?> p_class )
    {
        return ( m_result == null ) || ( p_class.isAssignableFrom( m_result.getClass() ) );
    }

    /**
     * liefert das Result eines Evaluates und castet es direkt in den Zieltyp
     * @see http://docs.renjin.org/en/latest/library/moving-data-between-java-and-r-code.html
     * @return Zieltyp
     */
    @SuppressWarnings( "unchecked" )
    public final <T extends SEXP> T result()
    {
        return (T) m_result;
    }

    /**
     * liefert die länge des ergebnis zurück
     *
     * @return Länge des Ergebnis
     */
    public final int length()
    {
        return m_result == null ? 0 : m_result.length();
    }

    /**
     * fügt eine Variable in die Engine ein
     *
     * @see http://docs.renjin.org/en/latest/library/moving-data-between-java-and-r-code.html
     * @param p_variablename variablen name
     * @param p_type Java Typ
     * @return Eigenreferenz auf das Objekt
     */
    public final REngine variable( final String p_variablename, final Object p_type )
    {
        m_engine.put( p_variablename, p_type );
        return this;
    }

}
