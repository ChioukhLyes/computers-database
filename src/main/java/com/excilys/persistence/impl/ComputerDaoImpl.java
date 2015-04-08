package com.excilys.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.impl.ComputerDTOmapperImpl;
import com.excilys.model.Company;
import com.excilys.persistence.ComputerDAO;
import com.excilys.services.ServiceComputer;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDaoImpl.
 */
@Repository
public class ComputerDaoImpl implements ComputerDAO {

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(ServiceComputer.class);

	/** The dao factory. */
	@Autowired
	DaoFactory daoFactory;

	/** The computer dto. */
	@Autowired
	ComputerDTO computerDTO;

	/** The computer mapper. */
	@Autowired
	ComputerDTOmapperImpl computerMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#findAllComputers()
	 */
	@Override
	public List<ComputerDTO> findAllComputers() {
		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id;");
			resultSet = preparedStatement.executeQuery();
			computers = computerMapper.mappComputers(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			daoFactory.closeConnections(connection, preparedStatement,
					resultSet);
		}
		return computers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistence.ComputerDAO#findAllComputers(int, int)
	 */
	@Override
	public List<ComputerDTO> findAllComputers(int limit, int offset) {
		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		Connection connection = null;
		// Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id limit ? offset ?;");
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			resultSet = preparedStatement.executeQuery();
			computers = computerMapper.mappComputers(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			daoFactory.closeConnections(connection, preparedStatement,
					resultSet);
		}
		return computers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#findComputerById(java.lang.Long)
	 */
	@Override
	public ComputerDTO findComputerById(Long id) {
		Connection connection = null;
		// Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			// statement = connection.createStatement();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id WHERE comp.id=?;");
			preparedStatement.setLong(1, id);
			preparedStatement.executeQuery();
			resultSet = preparedStatement.getResultSet();
			computerDTO = computerMapper.mappComputer(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			daoFactory.closeConnections(connection, preparedStatement,
					resultSet);
		}
		return computerDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#insertComputer(model.Computer)
	 */
	@Override
	public boolean insertComputer(ComputerDTO computer) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = daoFactory.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection
					.prepareStatement("INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (?,?,?,?);");

			computerMapper.mappComputerInPreparedStatemetInsert(
					preparedStatement, computer);
			connection.commit();
			return true;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e1.getMessage());
			}
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			daoFactory.closeConnections(connection, preparedStatement, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.persistence.ComputerDAO#deleteComputerByCompanyId(com.excilys
	 * .model.Company)
	 */
	@Override
	public boolean deleteComputerByCompanyId(Company company) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = daoFactory.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection
					.prepareStatement("DELETE FROM computer WHERE company_id=?;");
			preparedStatement.setLong(1, company.getId());
			preparedStatement.execute();
			connection.commit();
			return true;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e1.getMessage());
			}
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			daoFactory.closeConnections(connection, preparedStatement, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#deleteComputer(model.Computer)
	 */
	@Override
	public boolean deleteComputer(ComputerDTO computer) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = daoFactory.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection
					.prepareStatement("DELETE FROM computer WHERE id=?;");
			preparedStatement.setLong(1, computer.getId());
			preparedStatement.execute();
			connection.commit();
			return true;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e1.getMessage());
			}
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			daoFactory.closeConnections(connection, preparedStatement, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#updateComputer(model.Computer)
	 */
	@Override
	public boolean updateComputer(ComputerDTO computer) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection
					.prepareStatement("UPDATE computer set name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;");
			computerMapper.mappComputerInPreparedStatemetUpdate(
					preparedStatement, computer);
			connection.commit();
			return true;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e1.getMessage());
			}
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			daoFactory.closeConnections(connection, preparedStatement, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistence.ComputerDAO#getCountComputers()
	 */
	@Override
	public int getCountComputers(String search) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		int count = 0;

		String query = new String(
				"SELECT count(*) FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id");
		if (search != null) {
			query += " WHERE comp.name LIKE '%" + search
					+ "%' OR compa.name LIKE '%" + search + "%';";
		}
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			System.err.println(e.getMessage());
		} finally {
			daoFactory.closeConnections(connection, preparedStatement,
					resultSet);
		}
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.persistence.ComputerDAO#findAllComputersCompaniesByName(int,
	 * int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ComputerDTO> findAllComputersCompaniesByName(int limit,
			int offset, String orderBy, String search, String orderOption) {

		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		Connection connection = null;
		// Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = daoFactory.getConnection();
			if (orderBy.equals("companyname"))
				preparedStatement = connection
						.prepareStatement("SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id WHERE comp.name LIKE '%"
								+ search
								+ "%'  OR compa.name LIKE '%"
								+ search
								+ "%' ORDER BY compa.name "
								+ orderOption
								+ " limit ? offset ?;");
			else
				preparedStatement = connection
						.prepareStatement("SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id WHERE comp.name LIKE '%"
								+ search
								+ "%'  OR compa.name LIKE '%"
								+ search
								+ "%' ORDER BY comp."
								+ orderBy
								+ " "
								+ orderOption + " limit ? offset ?;");
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			resultSet = preparedStatement.executeQuery();
			computers = computerMapper.mappComputers(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			daoFactory.closeConnections(connection, preparedStatement,
					resultSet);
		}
		return computers;
	}

}
