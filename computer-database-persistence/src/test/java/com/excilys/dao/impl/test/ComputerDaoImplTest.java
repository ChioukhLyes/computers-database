/*
 * root
 * 
 * ComputerDaoImplTest.java - 2015
 */
package com.excilys.dao.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.dao.impl.ComputerDaoImpl;
import com.excilys.dto.ComputerDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDaoImplTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-persistence-test.xml")
public class ComputerDaoImplTest {

	@Autowired
	private ComputerDaoImpl computerDaoImpl;

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
	 * Test find all computers.
	 */
	@Test
	@Transactional
	public final void testFindAllComputers() {

		// GIVEN
		List<ComputerDTO> computers;
		// WHENE
		computers = computerDaoImpl.findAllComputers();
		// THEN
		assertNotNull(computers);
		assertTrue(computers.get(0) instanceof ComputerDTO);
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
		List<ComputerDTO> computers;
		// WHENE
		computers = computerDaoImpl.findAllComputers(limit, offset);
		// THEN
		assertNotNull(computers);
		assertTrue(computers.get(0) instanceof ComputerDTO);
		assertEquals(computers.size(), 5);
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
		List<ComputerDTO> computers;
		// WHENE
		computers = computerDaoImpl.findAllComputers(limit, offset);
		// THEN
		assertNotNull(computers);
		assertTrue(computers.get(0) instanceof ComputerDTO);
	}

	/**
	 * Test find computer by id.
	 */
	@Test
	@Transactional
	public final void testFindComputerById() {
		// GIVEN
		ComputerDTO computer;
		// WHENE
		computer = computerDaoImpl.findComputerById((long) 100);
		// THEN
		assertNotNull(computer);
		assertEquals(computer.getId(), (long) 100);
	}

	/**
	 * Test find computer by id error.
	 */
	@Test
	@Transactional
	public final void testFindComputerByIdError() {
		// GIVEN
		ComputerDTO computer;
		// WHENE
		computer = computerDaoImpl.findComputerById((long) -1);
		// THEN
		assertNull(computer);
	}

	/**
	 * Test find computer by id out index.
	 */
	@Test
	@Transactional
	public final void testFindComputerByIdOutIndex() {
		// GIVEN
		ComputerDTO computer;
		// WHENE
		computer = computerDaoImpl.findComputerById((long) 1000);
		// THEN
		assertNull(computer);
	}

	/**
	 * Test insert computer null.
	 */
	@Test(expected = NullPointerException.class)
	@Transactional
	public final void testInsertComputerNull() {

		// GIVEN
		ComputerDTO computer;
		// WHENE
		computer = null;
		computerDaoImpl.insertComputer(computer);
		// THEN
		assertNull(computer);
	}

	/**
	 * Test insert computer error.
	 */
	@SuppressWarnings("null")
	@Test(expected = NullPointerException.class)
	@Transactional
	public final void testInsertComputerError() {

		// GIVEN
		ComputerDTO computer = null;
		// WHENE
		computer.setName("aaaa");
		computer.setIntroduced(LocalDate.parse("12-12-2000"));
		computer.setDiscontinued(LocalDate.parse("12-12-2000"));
		computer.setCompanyName(null);
		computer.setCompanyId((long) 2);
		computerDaoImpl.insertComputer(computer);
		// THEN
		assertNotNull(computer);
	}

	/**
	 * Test delete computer error.
	 */
	@Test(expected = NullPointerException.class)
	@Transactional
	public final void testDeleteComputerError() {
		// GIVEN
		ComputerDTO computer;
		// WHENE
		computer = null;
		computerDaoImpl.deleteComputer(computer);
		// THEN
		assertNull(computer);
	}

	/**
	 * Test update computer error.
	 */
	@Test(expected = IllegalArgumentException.class)
	@Transactional
	public final void testUpdateComputerError() {
		// GIVEN
		ComputerDTO computer;
		// WHENE
		computer = null;
		computerDaoImpl.updateComputer(computer);
		// THEN
		assertNull(computer);
	}

	/**
	 * Test update computer.
	 */
	@Test
	@Transactional
	public final void testUpdateComputer() {
		// GIVEN
		ComputerDTO computer = computerDaoImpl.findComputerById((long) 450);
		// WHENE
		computer.setName("Updated");
		computerDaoImpl.updateComputer(computer);
		// THEN
		assertNotNull(computer);
	}

}
