package edu.tuc.taxieinstiegstatistik.service;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Smadback on 09.08.2016.
 *
 * Klasse zur erzeugung der XML Tag-Struktur.
 * Die Document Klasse erzeugt das zweite XML-Tag Element:
 * 
 * <kml> 		
 * 	<Document>		<=====
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
 *	 </Document>	<=====
 * </kml>
 */
public class Document {

    private Folder folder;

    public Folder getFolder() {
        return folder;
    }

    @XmlElement(name = "Folder")
    public void setFolder(Folder folder) {
        this.folder = folder;
    }
}
