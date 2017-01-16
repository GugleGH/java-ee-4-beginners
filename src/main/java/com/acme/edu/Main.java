/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.edu;


import com.sun.jmx.snmp.SnmpUnknownSubSystemException;

import java.io.*;
import java.nio.file.Path;

/**
 *
 * @author eugene
 */
public class Main {
    public static void main(String[] args) throws IOException {
        final File targetFile = new File(new File("target"), "test.txt");

        final PrintWriter printWriter = new PrintWriter(
            new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(targetFile, true),
                    "windows-1251"
                )
            )
        );

        printWriter.println("привет 01 !");
        printWriter.println("привет 02 !");
        printWriter.close();

        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(">>>>" + in.readLine());
        in.close();
    }
}
