/*
 * root
 * 
 * ServiceCompanyTest.java - 2015
 */
package com.excilys.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.models.Company;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceCompanyTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-service.xml")
public class ServiceCompanyTest {

	/** The service company. */
	@Autowired
	ServiceCompany serviceCompany;

	/** The exception. */
	@Rule
	public ExpectedException exception = ExpectedException.none();

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
	}

	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {
	}

	/**
	 * Testdelete company exception.
	 */
	@Test(expected = IllegalArgumentException.class)
	@Transactional
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
	@Transactional
	public void testdeleteCompanyTrue() {

		// GIVE
		Company company = new Company();
		serviceCompany.deleteCompany(company);
		// WHENE
		// THEN
	}

	/**
	 * Testfind company by id exception.
	 */
	@Test(expected = IllegalArgumentException.class)
	@Transactional
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
	@Transactional
	public void testfindCompanyByIdZero() {
		// GIVE
		Company company;
		long id = 0;
		// WHENE
		company = serviceCompany.findCompanyById(id);
		// THEN
		assertNull(company);
	}

	/**
	 * Testfind company by id true.
	 */
	@Test
	@Transactional
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

	/**
	 * Test find all companies.
	 */
	@Test
	@Transactional
	public final void testFindAllCompanies() {
		// GIVEN
		List<Company> companies;
		// WHENE
		companies = serviceCompany.findAllCompanies();
		// THEN
		assertNotNull(companies);
		assertTrue(companies.get(0) instanceof Company);
	}
	
	
	/**
	 * Test find all companies pages.
	 */
	@Test
	@Transactional
	public final void testFindAllCompaniesPages() {
		// GIVEN
		int limit = 5;
		int offset = 0;
		List<Company> companies;
		// WHENE
		companies = serviceCompany.findAllCompanies(limit, offset);
		// THEN
		assertNotNull(companies);
		assertTrue(companies.get(0) instanceof Company);
		assertEquals(companies.size(), 5);
	}
	
	
	/**
	 * Test find all companies pages error.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	@Transactional
	public final void testFindAllCompaniesPagesError() {
		// GIVEN
		int limit = -1;
		int offset = -1;
		List<Company> companies;
		// WHENE
		companies = serviceCompany.findAllCompanies(limit, offset);
		// THEN
		assertNotNull(companies);
		assertTrue(companies.get(0) instanceof Company);
	}

	/**
	 * Test find company by id.
	 */
	@Test
	@Transactional
	public final void testFindCompanyById() {
		// GIVEN
		Company company;
		// WHENE
		company = serviceCompany.findCompanyById((long) 20);
		// THEN
		assertNotNull(company);
		assertEquals(company.getId(), (long) 20);
	}

	/**
	 * Test find company by id error.
	 */
	@Test
	@Transactional
	public final void testFindCompanyByIdError() {
		// GIVEN
		Company company;
		// WHENE
		company = serviceCompany.findCompanyById((long) -1);
		// THEN
		assertNull(company);
	}

	/**
	 * Test find company by id out index.
	 */
	@Test
	@Transactional
	public final void testFindCompanyByIdOutIndex() {
		// GIVEN
		Company company;
		// WHENE
		company = serviceCompany.findCompanyById((long) 1000);
		// THEN
		assertNull(company);
	}

	
	
}
