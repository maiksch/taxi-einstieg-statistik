package edu.tuc.taxieinstiegstatistik.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.custommonkey.xmlunit.XMLTestCase;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

@Ignore
public class TaxiEinstiegStatistikServiceTest extends XMLTestCase {
	/**
	 * Test Klasse TaxiEinstiegStatistikService
	 * Das von der Methode "testGetEinstiegStatistik" zurueckgegebene XML wird auf 
	 * seine korrekte Struktur getestet.
	 */
/*
	@Test
	public void testGetEinstiegStatistik() {
		//Test ob Methode KML zurueck gibt
		TaxiEinstiegStatistikService testTESS = new TaxiEinstiegStatistikService();	
		Assert.assertTrue(testTESS.getEinstiegStatistik("00:00:00", "24:00:00") instanceof KML);	
	}
*/
	@Test
	public void testXMLContent() throws IOException, SAXException {
		//Test auf XML- Inhalt: wenn Tag </coordinates> enthalten dann xml valide 
		String myControlXML = "</coordinates>"; //Referenz Tag
		String inputLine;	//input via URL
        String myTestXML = new String();	//fertiges xml als String
		//Abruf vom jetty server via rest
		URL url = new URL("http://localhost:8080/rest/TaxiEinstiegStatistik");	
        BufferedReader in = new BufferedReader(
        new InputStreamReader(url.openStream()));
        //zuweisen des vom jetty erhaltenen xml
        while ((inputLine = in.readLine()) != null)
            myTestXML += inputLine;
        in.close();
        //Test ob Tag enthalten
        boolean test = myTestXML.contains(myControlXML);  
        Assert.assertTrue(test);       
	}
}
