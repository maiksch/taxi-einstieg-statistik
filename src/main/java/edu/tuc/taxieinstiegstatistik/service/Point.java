package edu.tuc.taxieinstiegstatistik.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Point")
public class Point {

    private String coordinates;

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}