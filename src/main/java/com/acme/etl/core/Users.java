/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.etl.core;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Список пользователей.
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
@XmlRootElement(name = "users")
@XmlAccessorType (XmlAccessType.FIELD)
public class Users {

    // Variables declaration
    @XmlElement(name = "user")
    private List<User> users = null;
    // End of variables declaration

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    public void addUser(User user) {
        if (users == null)
            users = new ArrayList<>();
        users.add(user);
    }
}
