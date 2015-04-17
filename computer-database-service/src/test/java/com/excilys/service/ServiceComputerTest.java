package com.excilys.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.dto.ComputerDTO;
import com.excilys.models.Company;

public class ServiceComputerTest {

	ServiceComputer serviceComputer;

	@Before
	public void setUp() throws Exception {
		// TODO Auto-generated method stub
		serviceComputer = new ServiceComputer();
	}

	@After
	public void tearDown() throws Exception {
		// TODO Auto-generated method stub
		serviceComputer = null;
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
	public void testfindComputerByIdZero() {

		ComputerDTO computerDTO;
		long id = 0;
		// GIVE
		// WHENE
		computerDTO = serviceComputer.findComputerById(id);
		// THEN
		assertNotNull(computerDTO);
		assertEquals(computerDTO, new ComputerDTO());
	}

	@Test
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
	public void testinsertComputerException() {

		ComputerDTO computerDTO;
		// GIVE
		computerDTO = null;
		// WHENE
		serviceComputer.insertComputer(computerDTO);
		// THEN
	}

	@Test
	public void testinsertComputerTrue() {

		ComputerDTO computerDTO;
		boolean stat = false;
		// GIVE
		computerDTO = new ComputerDTO();
		// WHENE
		//stat = serviceComputer.insertComputer(computerDTO);
		// THEN
		assertNotNull(computerDTO);
		assertTrue(stat);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testdeleteComputerException() {

		ComputerDTO computerDTO;
		// GIVE
		computerDTO = null;
		// WHENE
		serviceComputer.deleteComputer(computerDTO);
		// THEN
	}

	@Test
	public void testdeleteComputerTrue() {

		ComputerDTO computerDTO;
		boolean stat = false;
		// GIVE
		computerDTO = new ComputerDTO();
		// WHENE
		//stat = serviceComputer.deleteComputer(computerDTO);
		// THEN
		assertNotNull(computerDTO);
		assertTrue(stat);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testupdateComputerException() {

		ComputerDTO computerDTO;
		// GIVE
		computerDTO = null;
		// WHENE
		serviceComputer.updateComputer(computerDTO);
		// THEN
	}

	@Test
	public void testupdateComputerNull() {

		ComputerDTO computerDTO;
		boolean stat = false;
		// GIVE
		computerDTO = new ComputerDTO();
		// WHENE
		//stat = serviceComputer.updateComputer(computerDTO);
		// THEN
		assertNotNull(computerDTO);
		assertTrue(stat);

	}

	@Test
	public void testupdateComputerTrue() {

		ComputerDTO computerDTO;
		boolean stat = false;
		// GIVE
		computerDTO = new ComputerDTO();
		computerDTO.setId(12);
		// WHENE
		//stat = serviceComputer.updateComputer(computerDTO);
		// THEN
		assertNotNull(computerDTO);
		assertTrue(stat);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testdeleteComputerByCompanyIdException() {

		Company company;
		// GIVE
		company = null;
		// WHENE
		serviceComputer.deleteComputerByCompanyId(company);
		// THEN
	}

	@Test
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

}
