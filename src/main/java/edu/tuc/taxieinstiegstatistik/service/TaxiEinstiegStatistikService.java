package edu.tuc.taxieinstiegstatistik.service;

import edu.tuc.taxieinstiegstatistik.datenbank.DatenbankAdapter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Smadback on 09.08.2016.
 * <p>
 * Benoetigte XML/KML Struktur zur Verarbeitung in OpenLayer
 * <p>
 * <?xml version="1.0" encoding="UTF-8"?>
 * <KML xmlns="http://earth.google.com/kml/2.0" xmlns:atom="http://www.w3.org/2005/Atom">
 * * <kml> 		
 * 	<Document>
 * 		<Folder>
 * 			<name></name>
 * 			 <Placemark>
 * 			  <name></name>
 * 			  <magnitude></magnitude>
 * 			  <Point>
 * 				<coordinates></coordinates> 
 *			  </Point>
 *			</Placemark>
 *		</Folder>
 *	 </Document>
 * </kml>		
 * <p>
 * Die XML-Tagstruktur wird durch die Klassennamen und das Einbeziehen von weiteren Klassenobjekten aufgebaut.
 */
@Path("/TaxiEinstiegStatistik")
public class TaxiEinstiegStatistikService {

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public KML getEinstiegStatistik(@DefaultValue("00:00") @QueryParam("ab") String ab,
                                    @DefaultValue("23:59") @QueryParam("bis") String bis) {


        //Datenbank Abfrage
        DatenbankAdapter daba = DatenbankAdapter.getInstance();
        List<SimpleEntry<String,Date>> objects = daba.getStartingPointCoordinates(ab+":00", bis+":59");
        //Placemark Liste
        ArrayList<Placemark> placemarks = new ArrayList<>();
        //fuer jedes enthaltene Koordinaten Paar eine Point und Placemark Instanz
        //die erzeugte XML-Struktur kann dem Klassenvorwort oder den einzelnen XML-Tag-Struktur Klassen entnommen werden
        for (SimpleEntry<String, Date> p : objects) {
            Point point = new Point();
            point.setCoordinates("" + p.getKey() + ",0");
            Placemark placemark = new Placemark();
            placemark.setName(p.getValue().toString());
            placemark.setPoint(point);
            placemarks.add(placemark);
        }
        //Verbinden der <Placemark><Point><coordinates> Folge mit
        //der aeusseren XML-Struktur und dem root Element
        Folder folder = new Folder();
        folder.setName("Taxi Einstieg Statistik");
        folder.setPlacemarks(placemarks);

        Document document = new Document();
        document.setFolder(folder);

        KML kml = new KML();
        kml.setDocument(document);

        return kml;
    }

}


