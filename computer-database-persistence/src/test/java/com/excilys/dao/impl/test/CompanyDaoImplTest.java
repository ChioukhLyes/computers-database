/*
 * root
 * 
 * CompanyDaoImplTest.java - 2015
 */
package com.excilys.dao.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.dao.impl.CompanyDaoImpl;
import com.excilys.models.Company;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyDaoImplTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-persistence-test.xml")
public class CompanyDaoImplTest {

	@Autowired
	private CompanyDaoImpl compmanyDaoImpl;

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
	 * Test find all companies.
	 */
	@Test
	@Transactional
	public final void testFindAllCompanies() {
		// GIVEN
		List<Company> companies;
		// WHENE
		companies = compmanyDaoImpl.findAllCompanies();
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
		companies = compmanyDaoImpl.findAllCompanies(limit, offset);
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
		companies = compmanyDaoImpl.findAllCompanies(limit, offset);
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
		company = compmanyDaoImpl.findCompanyById((long) 20);
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
		company = compmanyDaoImpl.findCompanyById((long) -1);
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
		company = compmanyDaoImpl.findCompanyById((long) 1000);
		// THEN
		assertNull(company);
	}

}
