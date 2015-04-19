package com.excilys.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.dao.interfaces.UserDao;
import com.excilys.models.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDaoImpl.
 */
@Repository
public class UserDaoImpl implements UserDao {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.dao.interfaces.UserDao#findUserByName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public User findUserByName(String username) {

		List<User> users=null;
		User user = null;

		users = sessionFactory.getCurrentSession()
				.createQuery("from User where username=:username")
				.setParameter("username", username).list();
		
		if (users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

}
