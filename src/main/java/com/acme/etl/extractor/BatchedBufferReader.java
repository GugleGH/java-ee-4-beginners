package com.acme.etl.extractor;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by vm.andreev on 16.01.17.
 */
public class BatchedBufferReader {
    private int batchSize;
    private BufferedReader bufferedReader;
    private boolean next;
    private String nextLine = null;

    public BatchedBufferReader(int batchSize, BufferedReader bufferedReader) {
        this.batchSize = batchSize;
        this.bufferedReader = bufferedReader;
        this.next = true;

    }

    public String[] readBatch() throws IOException {
        String[] lines = new String[batchSize];
        lines[0] = nextLine;
        for (int counter = 1; counter < batchSize; counter++) {
            String line = bufferedReader.readLine();
            if (line != null) {
                lines[counter] = line;
            } else {
                this.next = false;
                break;
            }
        }
        return lines;
    }

    public void close() throws IOException {
        this.bufferedReader.close();
    }

    public boolean hasNext() throws IOException {
        this.nextLine = this.bufferedReader.readLine();
        return (this.nextLine != null);
    }
}
