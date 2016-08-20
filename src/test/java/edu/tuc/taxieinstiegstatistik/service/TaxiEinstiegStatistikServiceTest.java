package edu.tuc.taxieinstiegstatistik.service;

import java.io.IOException;

import org.custommonkey.xmlunit.XMLTestCase;
import org.junit.Test;
import org.xml.sax.SAXException;

public class TaxiEinstiegStatistikServiceTest extends XMLTestCase {
	/**
	 * Test Klasse TaxiEinstiegStatistikService
	 * Das von der Methode "testGetEinstiegStatistik" zurueckgegebene XML wird auf 
	 * seine korrekte Struktur getestet.
	 */

	@Test
	public void testGetEinstiegStatistik() {
		TaxiEinstiegStatistikService testTESS = new TaxiEinstiegStatistikService();
		KML testKML = new KML();
		testKML = testTESS.getEinstiegStatistik("00:00:00", "24:00:00");
		String myTestXML = testKML.toString();
		String myControlXML = "<kml><Document><Folder><name></name><Placemark><name></name><magnitude></magnitude><Point><coordinates></coordinates></Point></Placemark><Placemark><name></name><magnitude></magnitude><Point><coordinates></coordinates></Point></Placemark></Folder></Document></kml>";
				
		try {
			assertXMLEqual("Comparing test xml to control xml",myControlXML, myTestXML);
		} catch (SAXException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
