package com.acme.etl.test;

import com.acme.etl.DAO.callback.impl.LoaderUserCSVCallbackImpl;
import com.acme.etl.DAO.callback.impl.LoaderUserJaxBCallbackImpl;
import org.apache.log4j.Logger;

/**
 * Тестирование.
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
public class Start {

    // Variables declaration
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Start.class);
    // End of variables declaration

    /**
     * Test ETL
     * @param args входные параметры
     */
    public static void main(String[] args) {
        String csv = "/home/avnosov/Documents/Training_java_2017_01/java-ee-4-beginners/test1.csv";
        String xml = "/home/avnosov/Documents/Training_java_2017_01/java-ee-4-beginners/test1.xml";
        args = new String[1];
        args[0] = csv;
        

        LoaderUserCSVCallbackImpl loadCSV = new LoaderUserCSVCallbackImpl(csv);
        
        LoaderUserJaxBCallbackImpl loadJaxB = new LoaderUserJaxBCallbackImpl(xml);
    }
}