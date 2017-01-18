package com.acme.etl.extractor;

import com.acme.etl.core.User;

import java.io.IOException;

/**
 *
 * @author vm.andreev
 */
public interface UserReader {

	User[] readUsers() throws IOException;
}
