package com.excilys.mapper.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.model.Company;
import com.excilys.persistence.impl.DaoFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyMapperImplTest.
 */
@Service
public class CompanyMapperImplTest {

	/** The company mapper impl. */
	@Autowired
	CompanyMapperImpl companyMapperImpl;

	@Autowired
	DaoFactory daoFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {
		// TODO Auto-generated method stub
		companyMapperImpl = null;
	}

	/**
	 * Test mapp company true.
	 */
	@Test
	public final void testMappCompanyTrue() {

		// GIVEN

		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Company company;
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT id, name FROM company WHERE id="
							+ 1 + ";");
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		// WHENE
		company = companyMapperImpl.MappCompany(resultSet);
		// THEN
		assertNotNull(company);
		assertEquals(company.getId(), 1);
		daoFactory.closeConnections(connection, preparedStatement, resultSet);
	}

	/**
	 * Test mapp company empty resulset.
	 */
	@Test
	public final void testMappCompanyEmptyResulset() {

		// GIVEN

		ResultSet resultSet = null;
		Company company;
		// WHENE
		company = companyMapperImpl.MappCompany(resultSet);
		// THEN
		assertEquals(company, new Company());
		assertEquals(company.getId(), 0);
		assertEquals(company.getName(), null);
		daoFactory.closeConnections(null, null, resultSet);
	}

	/**
	 * Test mapp companies true.
	 */
	@Test
	public final void testMappCompaniesTrue() {

		// GIVEN

		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<Company> companies = new ArrayList<Company>();
		int compte;
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT count(*) FROM company;");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			compte = resultSet.getInt(1);

			preparedStatement = connection
					.prepareStatement("SELECT id, name FROM company;");
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		// WHENE
		companies = companyMapperImpl.MappCompanies(resultSet);
		// THEN
		assertNotNull(companies);
		assertEquals(compte, companies.size());
		daoFactory.closeConnections(connection, preparedStatement, resultSet);
	}

	/**
	 * Test mapp companies empty resulset.
	 */
	@Test
	public final void testMappCompaniesEmptyResulset() {

		// GIVEN

		ResultSet resultSet = null;
		List<Company> companies = new ArrayList<Company>();
		// WHENE
		companies = companyMapperImpl.MappCompanies(resultSet);
		// THEN
		assertEquals(companies, new ArrayList<Company>());
		assertTrue(companies.isEmpty());
		daoFactory.closeConnections(null, null, resultSet);
	}

}
