/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.etl.test;

import com.acme.etl.core.User;
import com.acme.etl.core.Users;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import org.apache.log4j.Logger;

/**
 *
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
public class StartCreateXML {

    // Variables declaration
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(StartCreateXML.class);
    // End of variables declaration

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Users users = new Users();
        
        User u1 = new User(21, "Прокофтева");
        User u2 = new User(22, "Марковкина");
        User u3 = new User(23, "Прожогина");
        User u4 = new User(24, "Кушкова");
        users.addUser(u1);
        users.addUser(u2);
        users.addUser(u3);
        users.addUser(u4);
        
        String str = "/home/avnosov/Documents/Training_java_2017_01/java-ee-4-beginners/test1.xml";
        
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(users, System.out);

            jaxbMarshaller.marshal(users, new File(str));
        } catch (JAXBException ex) {
            log.fatal(ex.getMessage(), ex);
        }
    }

}
