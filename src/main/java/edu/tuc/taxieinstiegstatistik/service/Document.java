package edu.tuc.taxieinstiegstatistik.service;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Smadback on 09.08.2016.
 */
@XmlRootElement
public class Document {

    private String x;
    private String y;
    private Date datum;
    private Folder folder;


    public Document() {

    }

    public Document(String x, String y, Date datum) {
        this.x = x;
        this.y = y;
        this.datum = datum;
    }

    public String getX() {
        return x;
    }

    @XmlElement
    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    @XmlElement
    public void setY(String y) {
        this.y = y;
    }

    public Date getDatum() {
        return datum;
    }

    @XmlElement
    public void setDatum(Date datum) {
        this.datum = datum;
    }

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}
}
