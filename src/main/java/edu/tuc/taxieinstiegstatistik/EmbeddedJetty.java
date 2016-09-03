package edu.tuc.taxieinstiegstatistik;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.io.FileWriter;
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
     * @throws Exception bei Initialisierungsfehler
     */
    private EmbeddedJetty(final InetSocketAddress p_bind ) throws Exception
    {

        // WebAppContext, um die web.xml zu verarbeiten
        final WebAppContext l_webapp = new WebAppContext(
                this.getClass().getProtectionDomain().getCodeSource().getLocation().toExternalForm(),
                "/"
        );
        l_webapp.setDescriptor( "WEB-INF/web.xml" );

        final Server l_server = new Server( p_bind );
        l_server.setHandler( l_webapp );
        l_server.start();
    }


    /**
     * main
     * @param p_args command-line Argumente
     * @throws Exception bei Initialisierungsfehler
     */
    public static void main( final String[] p_args ) throws Exception
    {
        // --- definiere Command-Line Parameter ------------------------------------------------------------------------

        final Options l_clioptions = new Options();
        l_clioptions.addOption( "help", false, "zeigt diese Hilfe" );
        l_clioptions.addOption( "port", true, "Port des Server (default 8080)" );
        l_clioptions.addOption( "host", true, "Adresse des Servers (default localhost)" );
        l_clioptions.addOption( "generateconfig", false, "Erzeugt die Anmeldedatei fuer die Datenbank" );
        

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

        if ( l_cli.hasOption( "generateconfig" ) )
        {


        	//Ordner erzeugen
            String path = System.getProperty("user.home") + File.separator + "taxistatistik";
            String fileName = "db.properties";
            File file = new File(path + File.separator + fileName);
            File dir = new File(path);

            if(dir.mkdir()) {
            	file.createNewFile();
                System.out.println("Datei erstellt: " + file.getName());

                FileWriter writer = new FileWriter(file ,true);
                writer.write("port=1234");
                writer.write(System.getProperty("line.separator"));
                writer.write("host=host");
                writer.write(System.getProperty("line.separator"));
                writer.write("db=db");
                writer.write(System.getProperty("line.separator"));
                writer.write("ssl=ssl");
                writer.write(System.getProperty("line.separator"));
                writer.write("usr=usr");
                writer.write(System.getProperty("line.separator"));
                writer.write("pw=pw");
                writer.flush();
                writer.close();
                
                System.out.println("Bitte passe die Datei mit den korrekten Anmeldedaten an.");

            } else {
                System.out.println(dir + " konnte nicht erstellt werden");
            } 
        
            
            
        	System.exit( 0 );
            return;
        }
        
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
        System.out.println("Server started.");
    }


}
