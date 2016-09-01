package edu.tuc.taxieinstiegstatistik;

/**
 * Main-Klasse, um Jetty mit der Anwendung zu starten
 */
public final class EmbeddedJetty
{

    /**
     * privater ctor, damit keine zwei Jetty-Instanzen laufen können,
     * Jetty Instanz wird aber hier gestartet
     */
    private EmbeddedJetty()
    {}


    /**
     * main
     * @param p_args
     */
    public static void main( final String[] p_args )
    {
        // instantiiere hier die Klasse
        new EmbeddedJetty();
    }


}
