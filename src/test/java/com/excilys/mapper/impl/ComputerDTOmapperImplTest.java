package com.excilys.mapper.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.persistence.impl.DaoFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDTOmapperImplTest.
 */
@Service
public class ComputerDTOmapperImplTest {

	/** The computer dt omapper impl. */
	@Autowired
	ComputerDTOmapperImpl computerDTOmapperImpl;

	@Autowired
	DaoFactory daoFactory;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		// TODO Auto-generated method stub
	}

	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {
		// TODO Auto-generated method stub
		computerDTOmapperImpl = null;
	}

	/**
	 * Test mapp computer true.
	 */
	@Test
	public final void testMappComputerTrue() {

		// GIVEN

		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		ComputerDTO computerDTO;
		String name;
		LocalDate introduced;
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id WHERE comp.id=1;");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			name = resultSet.getString("name");
			introduced = resultSet.getTimestamp("introduced").toLocalDateTime()
					.toLocalDate();
			resultSet.previous();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		// WHENE
		computerDTO = computerDTOmapperImpl.mappComputer(resultSet);
		// THEN
		assertNotNull(computerDTO);
		assertEquals(computerDTO.getId(), 1);
		assertEquals(computerDTO.getName(), name);
		assertEquals(computerDTO.getIntroduced(), introduced);
		daoFactory.closeConnections(connection, preparedStatement, resultSet);
	}

	/**
	 * Test mapp computer empty resulset.
	 */
	@Test
	public final void testMappComputerEmptyResulset() {

		// GIVEN
		ResultSet resultSet = null;
		ComputerDTO computerDTO;
		// WHENE
		computerDTO = computerDTOmapperImpl.mappComputer(resultSet);
		// THEN
		assertEquals(computerDTO, new ComputerDTO());
		assertEquals(computerDTO.getId(), 0);
		assertEquals(computerDTO.getName(), null);
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
		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		int compte;
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT count(*) FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id;");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			compte = resultSet.getInt(1);
			preparedStatement = connection
					.prepareStatement("SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id;");
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		// WHENE
		computers = computerDTOmapperImpl.mappComputers(resultSet);
		// THEN
		assertNotNull(computers);
		assertEquals(compte, computers.size());
		daoFactory.closeConnections(connection, preparedStatement, resultSet);
	}

	/**
	 * Test mapp companies empty resulset.
	 */
	@Test
	public final void testMappCompaniesEmptyResulset() {

		// GIVEN
		ResultSet resultSet = null;
		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		// WHENE
		computers = computerDTOmapperImpl.mappComputers(resultSet);
		// THEN
		assertEquals(computers, new ArrayList<Company>());
		assertTrue(computers.isEmpty());
		daoFactory.closeConnections(null, null, resultSet);
	}

	/**
	 * Testmapp computer in prepared statemet insert null.
	 */
	@Test(expected = NullPointerException.class)
	public final void testmappComputerInPreparedStatemetInsertNull() {

		// GIVEN
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ComputerDTO computerDTO = null;

		// WHENE
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (?,?,?,?);");
			computerDTOmapperImpl.mappComputerInPreparedStatemetInsert(
					preparedStatement, computerDTO);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		// THEN
	}

	/**
	 * Testmapp computer in prepared statemet insert sql.
	 */
	@Test
	public final void testmappComputerInPreparedStatemetInsertSql() {

		// GIVEN
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ComputerDTO computerDTO = new ComputerDTO();

		// WHENE
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (?,?,?,?);");
			computerDTOmapperImpl.mappComputerInPreparedStatemetInsert(
					preparedStatement, computerDTO);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		// THEN
	}

	/**
	 * Testmapp computer in prepared statemet update null.
	 */
	@Test(expected = NullPointerException.class)
	public final void testmappComputerInPreparedStatemetUpdateNull() {

		// GIVEN
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ComputerDTO computerDTO = null;

		// WHENE
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("UPDATE computer set name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;");
			computerDTOmapperImpl.mappComputerInPreparedStatemetUpdate(
					preparedStatement, computerDTO);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		// THEN
	}

	/**
	 * Testmapp computer in prepared statemet update sql.
	 */
	@Test
	public final void testmappComputerInPreparedStatemetUpdateSql() {

		// GIVEN
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ComputerDTO computerDTO = new ComputerDTO();

		// WHENE
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("UPDATE computer set name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;");
			computerDTOmapperImpl.mappComputerInPreparedStatemetUpdate(
					preparedStatement, computerDTO);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		// THEN
	}

}
