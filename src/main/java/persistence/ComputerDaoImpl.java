package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Computer;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDaoImplementation.
 */
public class ComputerDaoImpl implements ComputerDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#findAllComputers()
	 */
	@Override
	public List<Computer> findAllComputers() {
		List<Computer> computers = new ArrayList<Computer>();
		Connection connection = null;
		// Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		LocalDateTime introduced = null;
		LocalDateTime discontinued = null;

		try {
			connection = DaoFactory.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT id, name, introduced, discontinued, company_id FROM computer;");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				if (resultSet.getTimestamp("introduced") != null)
					introduced = resultSet.getTimestamp("introduced").toLocalDateTime();
				if (resultSet.getTimestamp("discontinued") != null)
					discontinued = resultSet.getTimestamp("discontinued").toLocalDateTime();

				Long idcompany = resultSet.getLong("company_id");
				Computer computer = new Computer();
				computer.setId(id);
				computer.setName(name);
				computer.setIntroduced(introduced);
				computer.setDiscontinued(discontinued);
				computer.setCompanyId(idcompany);
				computers.add(computer);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			DaoFactory.INSTANCE.CloseConnection(connection);
		}
		return computers;
	}

	
	@Override
	public List<Computer> findAllComputers(int limit, int offset) {
		List<Computer> computers = new ArrayList<Computer>();
		Connection connection = null;
		// Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		LocalDateTime introduced = null;
		LocalDateTime discontinued = null;

		try {
			connection = DaoFactory.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT id, name, introduced, discontinued, company_id FROM computer limit ? offset ?;");
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				if (resultSet.getTimestamp("introduced") != null)
					introduced = resultSet.getTimestamp("introduced").toLocalDateTime();
				if (resultSet.getTimestamp("discontinued") != null)
					discontinued = resultSet.getTimestamp("discontinued").toLocalDateTime();

				Long idcompany = resultSet.getLong("company_id");
				Computer computer = new Computer();
				computer.setId(id);
				computer.setName(name);
				computer.setIntroduced(introduced);
				computer.setDiscontinued(discontinued);
				computer.setCompanyId(idcompany);
				computers.add(computer);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			DaoFactory.INSTANCE.CloseConnection(connection);
		}
		return computers;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#findComputerById(java.lang.Long)
	 */
	@Override
	public Computer findComputerById(Long id) {
		Computer computer = new Computer();
		Connection connection = null;
		// Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		LocalDateTime introduced = null;
		LocalDateTime discontinued = null;

		try {
			connection = DaoFactory.INSTANCE.getConnection();
			// statement = connection.createStatement();
			preparedStatement = connection
					.prepareStatement("SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id=?;");
			preparedStatement.setLong(1, id);
			preparedStatement.executeQuery();
			resultSet = preparedStatement.getResultSet();
			if (resultSet.next()) {
				int idc = resultSet.getInt("id");
				String name = resultSet.getString("name");
				if (resultSet.getTimestamp("introduced") != null)
					introduced = resultSet.getTimestamp("introduced").toLocalDateTime();
				if (resultSet.getTimestamp("discontinued") != null)
					discontinued = resultSet.getTimestamp("discontinued").toLocalDateTime();

				Long idcompany = resultSet.getLong("company_id");
				computer.setId(idc);
				computer.setName(name);
				computer.setIntroduced(introduced);
				computer.setDiscontinued(discontinued);
				computer.setCompanyId(idcompany);
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			DaoFactory.INSTANCE.CloseConnection(connection);
		}
		return computer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#insertComputer(model.Computer)
	 */
	@Override
	public boolean insertComputer(Computer computer) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			
			System.out.println("");
			connection = DaoFactory.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement("INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (?,?,?,?);");
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced()));
			preparedStatement.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued()));
			preparedStatement.setLong(4, computer.getCompanyId());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("dddddd  "+e.getMessage());
			return false;
		} finally {
			DaoFactory.INSTANCE.CloseConnection(connection);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#deleteComputer(model.Computer)
	 */
	@Override
	public boolean deleteComputer(Computer computer) {
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
			DaoFactory.INSTANCE.CloseConnection(connection);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#updateComputer(model.Computer)
	 */
	@Override
	public boolean updateComputer(Computer computer) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DaoFactory.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement("UPDATE computer set name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;");
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced()));
			preparedStatement.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued()));
			preparedStatement.setLong(4, computer.getCompanyId());
			preparedStatement.setLong(5, computer.getId());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		} finally {
			DaoFactory.INSTANCE.CloseConnection(connection);
		}
	}


	@Override
	public int getCountComputers() {
		Connection connexion = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		int count=0;

		try {
			connexion = DaoFactory.INSTANCE.getConnection();
			preparedStatement = connexion
					.prepareStatement("SELECT count(*) FROM computer;");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			DaoFactory.INSTANCE.CloseConnection(connexion);
		}
		return count;
	}

}
