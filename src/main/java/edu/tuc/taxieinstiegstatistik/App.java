package edu.tuc.taxieinstiegstatistik;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Hello world!
 *
 */
public class App
{
    public static void server( String[] args )
    {
        

ResourceConfig config = new ResourceConfig();
 config.packages("taxieinstiegstatistik");
 ServletHolder servlet = new ServletHolder(new ServletContainer(config));

Server server = new Server(8081);
 ServletContextHandler context = new ServletContextHandler(server, "/*");
 context.addServlet(servlet, "/*");

try {
     server.start();
     try {
		server.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
} catch (Exception e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
} finally {
     server.destroy();
 }


    }
}
