package edu.tuc.taxieinstiegstatistik.service;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

public class Folder {

    private String name;
    private ArrayList<Placemark> placemarks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Placemark> getPlacemarks() {
        return placemarks;
    }

    @XmlElement(name = "Placemark")
    public void setPlacemarks(ArrayList<Placemark> placemarks) {
        this.placemarks = placemarks;
    }
}