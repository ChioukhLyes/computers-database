package com.excilys.dao.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.dao.impl.UserDaoImpl;
import com.excilys.models.User;

// TODO: Auto-generated Javadoc
/**
 * filUserDaoImplTest.java
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-persistence-test.xml")
public class UserDaoImplTest {

	@Autowired
	private UserDaoImpl userDaoImpl;

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test find computer by id.
	 */
	@Test
	@Transactional
	public final void testFindComputerById() {
		// GIVEN
		User user;
		// WHENE
		user = userDaoImpl.findUserByName("Admin1");
		// THEN
		assertNotNull(user);
		assertEquals(user.getUsername(), "Admin1");
		assertEquals((long) user.getRole(), (long) 1);
	}

	/**
	 * Test find computer by id error.
	 */
	@Test
	@Transactional
	public final void testFindComputerByIdError() {
		// GIVEN
		User user;
		// WHENE
		user = userDaoImpl.findUserByName("azerty");
		// THEN
		assertNull(user);
	}

	/**
	 * Test find computer by id out index.
	 */
	@Test
	@Transactional
	public final void testFindComputerByIdOutIndex() {
		// GIVEN
		User user;
		// WHENE
		user = userDaoImpl.findUserByName("Visitor");
		// THEN
		assertNotNull(user);
		assertEquals(user.getUsername(), "Visitor");
		assertEquals((long) user.getRole(), (long) 2);
	}
}
