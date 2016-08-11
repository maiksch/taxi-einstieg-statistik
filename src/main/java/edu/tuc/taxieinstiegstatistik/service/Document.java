package edu.tuc.taxieinstiegstatistik.service;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Smadback on 09.08.2016.
 */
public class Document {

    private Folder folder;

    public Folder getFolder() {
        return folder;
    }

    @XmlElement(name = "Folder")
    public void setFolder(Folder folder) {
        this.folder = folder;
    }
}
