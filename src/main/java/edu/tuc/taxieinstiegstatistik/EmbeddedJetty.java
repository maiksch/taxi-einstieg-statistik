package edu.tuc.taxieinstiegstatistik;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.eclipse.jetty.server.Server;

import java.net.InetSocketAddress;

/**
 * Main-Klasse, um Jetty mit der Anwendung zu starten
 */
public final class EmbeddedJetty
{
    /**
     * Default-Host-Adresse
     */
    private static final String DEFAULTHOST = "localhost";
    /**
     * Default-Host-Port
     */
    private static final String DEFAULTPORT = "8080";


    /**
     * privater ctor, damit keine zwei Jetty-Instanzen laufen können,
     * Jetty Instanz wird aber hier gestartet
     *
     * @param p_bind Binding Adresse
     */
    private EmbeddedJetty(final InetSocketAddress p_bind )
    {
        final Server l_server = new Server( p_bind );


    }


    /**
     * main
     * @param p_args command-line Argumente
     */
    public static void main( final String[] p_args )
    {
        // --- definiere Command-Line Parameter ------------------------------------------------------------------------

        final Options l_clioptions = new Options();
        l_clioptions.addOption( "help", false, "zeigt diese Hilfe" );
        l_clioptions.addOption( "port", true, "Port des Server (default 8080)" );
        l_clioptions.addOption( "host", true, "Adresse des Servers (default localhost)" );

        final CommandLine l_cli;
        try
        {
            l_cli = new DefaultParser().parse( l_clioptions, p_args );
        }
        catch ( final Exception l_exception )
        {
            System.err.println( "Command-Line Parsing Fehler" );
            System.exit( -1 );
            return;
        }

        // --- verarbeite Command-Line Parameter -----------------------------------------------------------------------

        if ( l_cli.hasOption( "help" ) )
        {
            new HelpFormatter().printHelp( new java.io.File( EmbeddedJetty.class.getProtectionDomain().getCodeSource().getLocation().getPath() ).getName(), l_clioptions );
            System.exit( 0 );
            return;
        }


        // instantiiere hier die Embedded-Jetty-Klasse
        // und lese aus den Parametern die Daten bzw. nutze die Default-Daten
        new EmbeddedJetty(
          new InetSocketAddress(
            l_cli.getOptionValue( "host", DEFAULTHOST ),
            Integer.parseInt( l_cli.getOptionValue( "port", DEFAULTPORT ) )
          )
        );
    }


}
