package com.acme.etl.core;

import com.acme.etl.extractor.UserReader;
import com.acme.etl.loader.UserWriter;

import java.io.IOException;

/**
 *
 * @author vm.andreev
 */
public class Controller {

	private final UserReader userReader;
	private final UserWriter[] userWriters;

	/**
	 *
	 * @param userReader
	 * @param userWriters
	 */
	public Controller(UserReader userReader, UserWriter... userWriters) {
		this.userReader = userReader;
		this.userWriters = userWriters;
	}

	public void doETL() throws IOException {
		User[] users = userReader.readUsers();
		while (users != null && users.length > 0) {
			for (UserWriter userWriter : userWriters) {
				userWriter.save(users);
			}
			users = userReader.readUsers();
		}

	}
}
