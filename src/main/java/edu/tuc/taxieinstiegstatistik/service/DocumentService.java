package edu.tuc.taxieinstiegstatistik.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * Created by Smadback on 09.08.2016.
 * 
 * Benoetigte XML/KML Struktur zur Verarbeitung in OpenLayer
 * 
 *<?xml version="1.0" encoding="UTF-8"?>
 *<kml xmlns="http://earth.google.com/kml/2.0" xmlns:atom="http://www.w3.org/2005/Atom">
 *   <Document>
 *       <Folder>
 *           <name>Magnitude 5</name>
 *           <Placemark id="2012 Feb 9 18:52:48.39 UTC">
 *               <name>2012 Feb 9 18:52:48.39 UTC</name>
 *               <magnitude>45.0</magnitude>
 *               <Point>
 *                   <coordinates>10.521111,52.269167,0</coordinates>
 *               </Point>
 *           </Placemark>
 *           <Placemark id="2012 Feb 9 18:52:48.39 UTC">
 *               <name>2012 Feb 9 18:52:48.39 UTC</name>
 *               <magnitude>5.9</magnitude>
 *               <Point>
 *                   <coordinates>10.521111,52.269167,0</coordinates>
 *               </Point>
 *           </Placemark>
 *       </Folder>
 *   </Document>
 *</kml>
 * 
 * Die XML-Tagstruktur wird durch die Klassennamen und das Einbeziehen von weiteren Klassenobjekten aufgebaut.
 * 
 */
@Path("/getDocument")
public class DocumentService {

    @GET
    @Produces(MediaType.APPLICATION_XML)
//    @XmlElementWrapper(name="refs",  namespace="http://reference.com/ref")
    public Document getDocument() {

    	Point point = new Point();
    	Placemark pl = new Placemark(point);
    	Folder fl = new Folder("Magnitude 5",pl);
        Document dc = new Document(fl);        
     //   dc.setFolder(fl);
     //   kml kmltag = new kml(dc);

        return dc;
    }

}
