package edu.tuc.taxieinstiegstatistik.service;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "KML")
public class KML {

	private Document document;
	private String xmlns = "http://earth.google.com/kml/2.0";
	private String xmlns_atom = "http://www.w3.org/2005/Atom";

	public Document getDocument() {
		return document;
	}

	@XmlElement(name = "Document")
	public void setDocument(Document dc) {
		this.document = dc;
	}

	public String getXmlns() {
		return xmlns;
	}

	@XmlAttribute
    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getXmlns_atom() {
        return xmlns_atom;
    }

    @XmlAttribute(name = "xmlns:atom")
    public void setXmlns_atom(String xmlns_atom) {
        this.xmlns_atom = xmlns_atom;
    }
}