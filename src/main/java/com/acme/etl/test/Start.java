package com.acme.etl.test;

import com.acme.etl.DAO.impl.LoaderUserFromFileCallbackImpl;

/**
 * Тестирование.
 * @author <a href="mailto:av.nosov@jet.su">Nosov A.V.</a>
 */
public class Start {

    /**
     * Test ETL
     * @param args входные параметры
     */
    public static void main(String[] args) {
        String str = "/home/avnosov/Documents/Training_java_2017_01/java-ee-4-beginners/test1.csv";
        args = new String[1];
        args[0] = str;
        
        LoaderUserFromFileCallbackImpl loadUsers = new LoaderUserFromFileCallbackImpl(str);

    }
}