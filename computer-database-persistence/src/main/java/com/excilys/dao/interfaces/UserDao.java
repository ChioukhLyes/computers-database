package com.excilys.dao.interfaces;

import com.excilys.models.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserDao.
 */
public interface UserDao {

	/**
	 * Find user by name.
	 *
	 * @param username
	 *            the username
	 * @return the user
	 */
	User findUserByName(String username);

}