package edu.tuc.taxieinstiegstatistik.service;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Placemark {

    private String name = "";
    private String magnitude = "45.0";
    private Point point;

    public Point getPoint() {
        return point;
    }

    @XmlElement(name = "Point")
    public void setPoint(Point point) {
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
    	this.name = name;
    }
    
    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }


}