
package edu.tuc.taxieinstiegstatistik;

import edu.tuc.taxieinstiegstatistik.Jetty.App;

/**
 * Einstiegspunkt in die Applikation
 */
public class TaxiEinstiegStatistik {
    public static void main(String[] args) {

        // Starte den Jetti-Server der den Webservice bereit stellt
        App.server(args);

    }
}

