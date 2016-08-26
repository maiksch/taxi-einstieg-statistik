package edu.tuc.taxieinstiegstatistik.service;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
/**
 * 
 * Klasse zur erzeugung der XML Tag-Struktur.
 * Die Folder Klasse erzeugt das dritte XML-Tag Element
 * zusaetzlich beinhaelt das Folder Tag, das Tag <name>
 * inklusive Wert:
 * 
 * <kml> 		
 * 	<Document>
 * 		<Folder>		<====
 * 			<name></name>
 * 			 <Placemark>
 * 			  <name></name>
 * 			  <magnitude></magnitude>
 * 			  <Point>
 * 				<coordinates></coordinates> 
 *			  </Point>
 *			</Placemark>
 *		</Folder>		<====
 *	 </Document>
 * </kml>
 */
public class Folder {

    private String name;
    private ArrayList<Placemark> placemarks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Placemark> getPlacemarks() {
        return placemarks;
    }

    @XmlElement(name = "Placemark")
    public void setPlacemarks(ArrayList<Placemark> placemarks) {
        this.placemarks = placemarks;
    }
}