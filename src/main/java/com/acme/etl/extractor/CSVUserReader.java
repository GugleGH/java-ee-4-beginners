package com.acme.etl.extractor;

import com.acme.etl.core.User;

import java.io.IOException;

/**
 * Created by vm.andreev on 16.01.17.
 */
public class CSVUserReader implements UserReader {

    private BatchedBufferReader batchedBufferReader;

    public CSVUserReader(BatchedBufferReader batchedBufferReader) {
        this.batchedBufferReader = batchedBufferReader;
    }

    @Override
    public User[] readUsers() throws IOException {
        if (batchedBufferReader.hasNext()) {
            String[] userStrings = batchedBufferReader.readBatch();
            User[] users = new User[userStrings.length];
            int i = 0;
            for (String userString : userStrings) {
                if (userString != null) {
                    String[] userParameters = userString.split(",");
                    users[i] = new User(Integer.parseInt(userParameters[0]), userParameters[1]);
                    i++;
                } else {
                    break;
                }
            }
            return users;
        } else {
            return null;
        }
    }

    public void close() throws IOException {
        this.batchedBufferReader.close();
    }
}
