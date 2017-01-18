package com.acme.etl.loader;

import com.acme.etl.core.User;

/**
 *
 * @author vm.andreev
 */
public interface UserWriter {

	void save(User... users);
}
