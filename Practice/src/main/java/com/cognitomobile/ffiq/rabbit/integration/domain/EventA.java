package com.cognitomobile.ffiq.rabbit.integration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "eventA")
public class EventA implements Serializable {

    @XmlElement(required = true)
    private int id;
    private String txt;

    public EventA() {
    }

    public EventA(final int id, final String txt) {
        this.id = id;
        this.txt = txt;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(final String txt) {
        this.txt = txt;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EventA: id = " + id + " txt=" + txt;
    }

}
