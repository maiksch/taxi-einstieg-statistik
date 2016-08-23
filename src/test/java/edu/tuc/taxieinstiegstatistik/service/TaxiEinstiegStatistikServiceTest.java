package edu.tuc.taxieinstiegstatistik.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xmlunit.builder.DiffBuilder;
public class TaxiEinstiegStatistikServiceTest extends XMLTestCase {
	/**
	 * Test Klasse TaxiEinstiegStatistikService
	 * Das von der Methode "testGetEinstiegStatistik" zurueckgegebene XML wird auf 
	 * seine korrekte Struktur getestet.
	 */

	@Test
	public void testGetEinstiegStatistik() {
		//Test ob Methode KML zurueck gibt
		TaxiEinstiegStatistikService testTESS = new TaxiEinstiegStatistikService();	
		Assert.assertTrue(testTESS.getEinstiegStatistik("00:00:00", "24:00:00") instanceof KML);	
	}
	
	@Test
	public void testXMLContent() throws IOException, SAXException {
		
		String myControlXML = "</coordinates>";
		String inputLine;
        String myTestXML = new String();
		
		URL url = new URL("http://localhost:8080/rest/TaxiEinstiegStatistik");	
        BufferedReader in = new BufferedReader(
        new InputStreamReader(url.openStream()));

        while ((inputLine = in.readLine()) != null)
            myTestXML += inputLine;
        in.close();
		
	//	System.out.println("alt: " +myControlXML);
	//	System.out.println("neu: " +myTestXML);
		
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true);
        
        Diff myDiff = DiffBuilder.compare(myControlXML).withTest(myTestXML)
                .checkForSimilar() // a different order is always 'similar' not equals.
                .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))
                .build();

        Assert.assertFalse("XML similar " + myDiff.toString(), myDiff.hasDifferences());
        
		//	assertXMLEqual("Vergleiche XML Struktur",myControlXML, myTestXML);
		//	assertNodeTest
		//	XMLTestCase.assertEquals(myControlXML, myTestXML);
		//	XMLTestCase.assertSame(myControlXML, myTestXML);
	}
}
