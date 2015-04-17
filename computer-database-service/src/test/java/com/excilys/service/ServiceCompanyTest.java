package com.excilys.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.excilys.models.Company;
import com.excilys.service.ServiceCompany;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceCompanyTest.
 */
public class ServiceCompanyTest {

	/** The service company. */
	ServiceCompany serviceCompany;

	/** The exception. */
	@Rule
	public ExpectedException exception = ExpectedException.none();

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		// TODO Auto-generated method stub
		serviceCompany = new ServiceCompany();
	}

	/**
	 * Tear down.
	 */
	@After
	public void tearDown()  {
		// TODO Auto-generated method stub
		serviceCompany = null;
	}

	/**
	 * Testdelete company exception.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testdeleteCompanyException() {

		// GIVE
		Company company;
		// WHENE
		company = null;
		serviceCompany.deleteCompany(company);
		// THEN
	}

	/**
	 * Testdelete company true.
	 */
	@Test
	public void testdeleteCompanyTrue() {

		boolean stat = false;
		// GIVE
		//Company company = new Company();
		// WHENE
		//stat = serviceCompany.deleteCompany(company);
		// THEN
		assertTrue(stat);
	}

	/**
	 * Testfind company by id exception.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testfindCompanyByIdException() {

		// GIVE
		Long id = null;
		// WHENE
		serviceCompany.findCompanyById(id);
		// THEN
	}

	/**
	 * Testfind company by id zero.
	 */
	@Test
	public void testfindCompanyByIdZero() {

		Company company;
		long id = 0;
		// GIVE
		// WHENE
		company = serviceCompany.findCompanyById(id);
		// THEN
		assertNotNull(company);
		assertEquals(company, new Company());
	}

	/**
	 * Testfind company by id true.
	 */
	@Test
	public void testfindCompanyByIdTrue() {

		Company company;
		long id = 5;
		// GIVE
		// WHENE
		company = serviceCompany.findCompanyById(id);
		// THEN
		assertNotNull(company);
		assertEquals(company.getId(), 5);
	}

}
