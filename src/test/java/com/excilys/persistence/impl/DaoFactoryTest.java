package com.excilys.persistence.impl;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;

public class DaoFactoryTest {

	DaoFactory daoFactory; 
	
	@Before
	public void setUp()  {
		// TODO Auto-generated method stub
		this.daoFactory = DaoFactory.getInstance();
	}

	@After
	public void tearDown() {
		// TODO Auto-generated method stub
		this.daoFactory = null;
	}
	
	@Test
	public void testGetConnection() {
		// Given
//		Connection connection = daoFactory.getConnection();
		// Whene
		
		// Then

		// fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCloseConnection() {

		boolean isClosed = false;
		// Given
		Connection connection = daoFactory.getConnection();
		// Whene
		daoFactory.CloseConnections(connection,null,null);
		try {
			isClosed = connection.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		// Then
		assertNotNull(connection);
		assertEquals(isClosed, true);
	}

	@Test
	public void testGetCompanyDAO() {
		//Given
		//Whene
		CompanyDAO companyDAO =  daoFactory.getCompanyDAO();
		//Then
		assertNotNull(companyDAO);
		assertTrue(companyDAO instanceof CompanyDAO);
		
	}

	@Test
	public void testGetComputerDAO() {
		//Given
		//Whene
		ComputerDAO compuerDAO =  daoFactory.getComputerDAO();
		//Then
		assertNotNull(compuerDAO);
		assertTrue(compuerDAO instanceof ComputerDAO);
	}

}
