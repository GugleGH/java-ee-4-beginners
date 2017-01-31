package com.acme.etl.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Пользователь.
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
@XmlRootElement(name = "user")
@XmlAccessorType (XmlAccessType.FIELD)
public class User {

    @XmlAttribute
    private int id;
    @XmlElement
    private String name;

    public User() {
        
    }
    
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

//    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

//    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("<User id: %d, name: %s>", id, name);
    }
    
}
