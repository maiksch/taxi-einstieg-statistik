package edu.tuc.taxieinstiegstatistik.service;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
//@XmlRootElement
public class Folder {
	
	public String name;
	public Placemark Placemark;//Großschreibung da XML case sensitive
	
	public Folder(){
		
	}
	
	public Folder(String name,Placemark pl){
		this.name = name;
		this.Placemark = pl;
	}
	
	//Anmerkung getter und setter werden vom Server nicht vertragen
	//fuehrt zu "internal Server error" im Browser
/*	
	public String getname(){
		return name;
	}
	
@XmlElement
	public void setname(String name){
		this.name = name;
	}
	 
	public Placemark getplacemark(){
		return placemark;
	}
@XmlElement	
	public void setplacemark(Placemark pl){
		this.placemark = pl;
	}
*/

}