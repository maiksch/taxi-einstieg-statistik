package edu.tuc.taxieinstiegstatistik.service;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * Klasse zur erzeugung der XML Tag-Struktur.
 * Die Point Klasse erzeugt das fuenfte XML-Tag Element
 * zusaetzlich enthaelt Point das Tag <coordinates>
 * inklusive Wert:
 * 
 * <kml> 		
 * 	<Document>
 * 		<Folder>		
 * 			<name></name>
 * 			 <Placemark> 	
 * 			  <name></name>
 * 			  <magnitude></magnitude>
 * 			  <Point>		<=====
 * 				<coordinates></coordinates> 
 *			  </Point>		<=====
 *			</Placemark> 	
 *		</Folder>		
 *	 </Document>
 * </kml>
 */
@XmlRootElement(name = "Point")
public class Point {

    private String coordinates;

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}