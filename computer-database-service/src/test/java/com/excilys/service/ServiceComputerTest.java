package com.excilys.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.dto.ComputerDTO;
import com.excilys.models.Company;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-service.xml")
public class ServiceComputerTest {

	@Autowired
	private ServiceComputer serviceComputer;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testfindComputerByIdException() {

		// GIVE
		Long id = null;
		// WHENE
		serviceComputer.findComputerById(id);
		// THEN
	}

	@Test
	@Transactional
	public void testfindComputerByIdZero() {

		ComputerDTO computerDTO;
		long id = 0;
		// GIVE
		// WHENE
		computerDTO = serviceComputer.findComputerById(id);
		// THEN
		assertNull(computerDTO);
	}

	@Test
	@Transactional
	public void testfindComputerByIdTrue() {

		ComputerDTO computerDTO;
		long id = 5;
		// GIVE
		// WHENE
		computerDTO = serviceComputer.findComputerById(id);
		// THEN
		assertNotNull(computerDTO);
		assertEquals(computerDTO.getId(), 5);
	}

	@Test(expected = IllegalArgumentException.class)
	@Transactional
	public void testinsertComputerException() {

		ComputerDTO computerDTO;
		// GIVE
		computerDTO = null;
		// WHENE
		serviceComputer.insertComputer(computerDTO);
		// THEN
	}

	@Test(expected = NullPointerException.class)
	@Transactional
	public void testinsertComputerTrue() {

		ComputerDTO computerDTO;
		// GIVE
		computerDTO = new ComputerDTO();
		// WHENE
		serviceComputer.insertComputer(computerDTO);
		// THEN

	}

	@Test(expected = IllegalArgumentException.class)
	@Transactional
	public void testdeleteComputerException() {

		ComputerDTO computerDTO;
		// GIVE
		computerDTO = null;
		// WHENE
		serviceComputer.deleteComputer(computerDTO);
		// THEN
	}

	@Test
	@Transactional
	public void testdeleteComputerTrue() {

		ComputerDTO computerDTO;
		// GIVE
		computerDTO = new ComputerDTO();
		// WHENE
		serviceComputer.deleteComputer(computerDTO);
		// THEN

	}

	@Test(expected = IllegalArgumentException.class)
	@Transactional
	public void testupdateComputerException() {

		ComputerDTO computerDTO;
		// GIVE
		computerDTO = null;
		// WHENE
		serviceComputer.updateComputer(computerDTO);
		// THEN
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	public void testupdateComputerNull() {

		ComputerDTO computerDTO;
		// GIVE
		computerDTO = new ComputerDTO();
		// WHENE
		serviceComputer.updateComputer(computerDTO);
		// THEN
	}

	@Test
	@Transactional
	public void testupdateComputerTrue() {

		ComputerDTO computerDTO;
		// GIVE
		computerDTO = new ComputerDTO();
		computerDTO.setId(12);
		// WHENE
		serviceComputer.updateComputer(computerDTO);
		// THEN

	}

	@Test(expected = IllegalArgumentException.class)
	@Transactional
	public void testdeleteComputerByCompanyIdException() {

		Company company;
		// GIVE
		company = null;
		// WHENE
		serviceComputer.deleteComputerByCompanyId(company);
		// THEN
	}

	@Test
	@Transactional
	public void testdeleteComputerByCompanyIdTrue() {

		Company company;
		boolean stat = false;
		// GIVE
		company = new Company();
		// WHENE
		stat = serviceComputer.deleteComputerByCompanyId(company);
		// THEN
		assertNotNull(company);
		assertTrue(stat);

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
		computers = serviceComputer.findAllComputers();
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
		computers = serviceComputer.findAllComputers(limit, offset);
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
		computers = serviceComputer.findAllComputers(limit, offset);
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
		computer = serviceComputer.findComputerById((long) 100);
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
		computer = serviceComputer.findComputerById((long) -1);
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
		computer = serviceComputer.findComputerById((long) 1000);
		// THEN
		assertNull(computer);
	}

	/**
	 * Test insert computer null.
	 */
	@Test(expected = IllegalArgumentException.class)
	@Transactional
	public final void testInsertComputerNull() {

		// GIVEN
		ComputerDTO computer;
		// WHENE
		computer = null;
		serviceComputer.insertComputer(computer);
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
		serviceComputer.insertComputer(computer);
		// THEN
		assertNotNull(computer);
	}

	/**
	 * Test delete computer error.
	 */
	@Test(expected = IllegalArgumentException.class)
	@Transactional
	public final void testDeleteComputerError() {
		// GIVEN
		ComputerDTO computer;
		// WHENE
		computer = null;
		serviceComputer.deleteComputer(computer);
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
		serviceComputer.updateComputer(computer);
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
		ComputerDTO computer = serviceComputer.findComputerById((long) 700);
		// WHENE
		computer.setName("Updated");
		serviceComputer.updateComputer(computer);
		// THEN
		assertNotNull(computer);
	}

}
