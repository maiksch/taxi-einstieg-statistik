package edu.tuc.taxieinstiegstatistik.service;

import edu.tuc.taxieinstiegstatistik.datenbank.DatenbankAdapter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Created by Smadback on 09.08.2016.
 * <p>
 * Benoetigte XML/KML Struktur zur Verarbeitung in OpenLayer
 * <p>
 * <?xml version="1.0" encoding="UTF-8"?>
 * <KML xmlns="http://earth.google.com/kml/2.0" xmlns:atom="http://www.w3.org/2005/Atom">
 * <Document>
 * <Folder>
 * <name>Magnitude 5</name>
 * <Placemark id="2012 Feb 9 18:52:48.39 UTC">
 * <name>2012 Feb 9 18:52:48.39 UTC</name>
 * <magnitude>45.0</magnitude>
 * <Point>
 * <coordinates>10.521111,52.269167,0</coordinates>
 * </Point>
 * </Placemark>
 * <Placemark id="2012 Feb 9 18:52:48.39 UTC">
 * <name>2012 Feb 9 18:52:48.39 UTC</name>
 * <magnitude>5.9</magnitude>
 * <Point>
 * <coordinates>10.521111,52.269167,0</coordinates>
 * </Point>
 * </Placemark>
 * </Folder>
 * </Document>
 * </KML>
 * <p>
 * Die XML-Tagstruktur wird durch die Klassennamen und das Einbeziehen von weiteren Klassenobjekten aufgebaut.
 */
@Path("/TaxiEinstiegStatistik")
public class TaxiEinstiegStatistikService {

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public KML getEinstiegStatistik(@DefaultValue("00:00:00") @QueryParam("ab") String ab,
                                    @DefaultValue("24:00:00") @QueryParam("bis") String bis) {


        //Datenbank Abfrage
        DatenbankAdapter daba = DatenbankAdapter.getInstance();
        ArrayList<org.postgis.Point> objects = daba.getStartingPointCoordinates(ab, bis);
        //Placemark Liste
        ArrayList<Placemark> placemarks = new ArrayList<>();
        //fuer jedes enthaltene Koordinaten Paar eine Point und Placemark Instanz
        for (org.postgis.Point p : objects) {
            Point point = new Point();
            point.setCoordinates("" + p.getX() + "," + p.getY() + ",0");
            Placemark placemark = new Placemark();
            placemark.setPoint(point);
            placemarks.add(placemark);
        }

        Folder folder = new Folder();
        folder.setName("Magnitude 5");
        folder.setPlacemarks(placemarks);

        Document document = new Document();
        document.setFolder(folder);

        KML kml = new KML();
        kml.setDocument(document);

        return kml;
    }

}


