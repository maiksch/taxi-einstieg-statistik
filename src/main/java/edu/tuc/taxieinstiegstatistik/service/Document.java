package edu.tuc.taxieinstiegstatistik.service;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Smadback on 09.08.2016.
 */
@XmlRootElement(name="Document")
public class Document {

    private Folder folder;


    public Document() {

    }
    

    public Document(Folder fl) {
        this.folder = fl;
    }
  
	public Folder getFolder() {
		return folder;
	}
@XmlElement(name="Folder")
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
}
