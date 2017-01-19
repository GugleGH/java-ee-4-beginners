package com.acme.etl.test;

import com.acme.etl.core.Controller;
import com.acme.etl.extractor.BatchedBufferReader;
import com.acme.etl.extractor.CSVUserReader;

import java.io.*;
import java.util.Arrays;

/**
 * @author vm.andreev
 */
public class ControllerTest {

    /**
     * Test ETL
     *
     * @param args input params
     */
    public static void main(String[] args) throws IOException {
        String str = "/home/avnosov/Documents/Training_java_2017_01/java-ee-4-beginners/test1.csv";
        args = new String[1];
        args[0] = str;
        CSVUserReader csvUserReader = new CSVUserReader(
                new BatchedBufferReader(3, 
                        new BufferedReader(
                                new FileReader(
                                        new File(args[0])))));
        Controller controller = new Controller(
                csvUserReader,
                new UserWriterStub("LDAP"), new UserWriterStub("DB")
        );

        controller.doETL();
        csvUserReader.close();

    }
}

// TODO: Exceptions, Collections, Refactoring