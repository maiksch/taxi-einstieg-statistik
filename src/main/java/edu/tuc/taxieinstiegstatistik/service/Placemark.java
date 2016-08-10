package edu.tuc.taxieinstiegstatistik.service;

public class Placemark {
	
	public String name = "2012 Feb 9 18:52:48.39 UTC";
	public String magnitude = "45.0";
	public Point point;
	
	public Placemark(Point pt){
		this.point = pt;
	}
}
