package com.acme.etl.test;

import com.acme.etl.core.Controller;
import com.acme.etl.extractor.BatchedBufferReader;
import com.acme.etl.extractor.CSVUserReader;

import java.io.*;

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
        CSVUserReader csvUserReader = new CSVUserReader(new BatchedBufferReader(3, new BufferedReader(new FileReader(new File(args[0])))));
        Controller controller = new Controller(
                csvUserReader,
                new UserWriterStub("LDAP"), new UserWriterStub("DB")
        );

        controller.doETL();
        csvUserReader.close();

    }
}

// TODO: Exceptions, Collections, Refactoring