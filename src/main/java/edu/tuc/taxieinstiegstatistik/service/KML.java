package edu.tuc.taxieinstiegstatistik.service;

import javax.xml.bind.annotation.XmlAttribute;

public class KML {
	
	public Document dc;
	
	public KML(){
		
	}

	public KML(Document dc){
		this.dc = dc;
	}
	
	public void setDocument(Document dc){
		this.dc = dc;
		
	}
	
	public Document getDocument(){
	return dc;
	}
}
