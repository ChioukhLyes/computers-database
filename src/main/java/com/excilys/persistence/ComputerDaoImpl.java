
package com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.impl.ComputerDTOmapperImpl;

public class ComputerDaoImpl implements ComputerDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#findAllComputers()
	 */
	@Override
	public List<ComputerDTO> findAllComputers() {
		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		Connection connection = null;
		// Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DaoFactory.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id;");
			resultSet = preparedStatement.executeQuery();
			computers = ComputerDTOmapperImpl.INSTANCE.MappComputers(resultSet);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			DaoFactory.INSTANCE.CloseConnections(connection, preparedStatement,resultSet);
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
			connection = DaoFactory.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id limit ? offset ?;");
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			resultSet = preparedStatement.executeQuery();
			computers = ComputerDTOmapperImpl.INSTANCE.MappComputers(resultSet);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			DaoFactory.INSTANCE.CloseConnections(connection, preparedStatement,resultSet);
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
		ComputerDTO computerDTO = new ComputerDTO();
		Connection connection = null;
		// Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DaoFactory.INSTANCE.getConnection();
			// statement = connection.createStatement();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id WHERE comp.id=?;");
			preparedStatement.setLong(1, id);
			preparedStatement.executeQuery();
			resultSet = preparedStatement.getResultSet();
			computerDTO = ComputerDTOmapperImpl.INSTANCE.MappComputer(resultSet);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			DaoFactory.INSTANCE.CloseConnections(connection, preparedStatement,resultSet);
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
			connection = DaoFactory.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement("INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (?,?,?,?);");
			
			ComputerDTOmapperImpl.INSTANCE.MappComputerInPreparedStatemetInsert(preparedStatement, computer);
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		} finally {
			DaoFactory.INSTANCE.CloseConnections(connection, preparedStatement,null);
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
			connection = DaoFactory.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement("DELETE FROM computer WHERE id=?;");
			preparedStatement.setLong(1, computer.getId());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		} finally {
			DaoFactory.INSTANCE.CloseConnections(connection, preparedStatement,null);
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
			connection = DaoFactory.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement("UPDATE computer set name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;");
			ComputerDTOmapperImpl.INSTANCE.MappComputerInPreparedStatemetUpdate(preparedStatement, computer);
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		} finally {
			DaoFactory.INSTANCE.CloseConnections(connection, preparedStatement,null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistence.ComputerDAO#getCountComputers()
	 */
	@Override
	public int getCountComputers() {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		int count = 0;

		try {
			connection = DaoFactory.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT count(*) FROM computer;");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			DaoFactory.INSTANCE.CloseConnections(connection, preparedStatement,resultSet);
		}
		return count;
	}

}
