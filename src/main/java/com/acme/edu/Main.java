/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.edu;


import java.io.*;
import java.sql.SQLException;

/**
 *
 * @author eugene
 */
public class Main {
    public static void main(String[] args) {
        try {
            readFile();
            //.....
            //......
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void readFile() throws MyBLException {
        /*
        if (true) try {
            throw new IOException("kdjfhkdfjghdkf!!!!!!");
        } catch (IOException e) {
            ///......
            if(1==1) throw new RuntimeException("rrr");
            e.printStackTrace();
            throw new MyBLException("fdkgjhdfgkjdhfgkjdhg", e);
        } finally {
            throw new RuntimeException("d");
        }
        */

        try (My my = new My()) {
            throw new IOException("1");
        } catch (IOException e) {
            throw new RuntimeException("2");
        }
        //....
    }
}

class My implements Closeable {
    @Override
    public void close() throws IOException {
        throw new IOException("qqqqq");
    }
}
