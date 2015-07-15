package com.cognitomobile.ffiq.rabbit.integration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "eventB")
public class EventB implements Serializable {

    @XmlElement(required = true)
    private int id;

    private String txt;

    public EventB() {

    }

    public EventB(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(final String txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return "EventB: id = " + id;
    }

}
