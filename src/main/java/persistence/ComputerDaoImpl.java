package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Company;
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
		LocalDate introduced = null;
		LocalDate discontinued = null;

		try {
			connection = DaoFactory.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id;");

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				if (resultSet.getTimestamp("introduced") != null)
					introduced = resultSet.getTimestamp("introduced")
							.toLocalDateTime().toLocalDate();
				if (resultSet.getTimestamp("discontinued") != null)
					discontinued = resultSet.getTimestamp("discontinued")
							.toLocalDateTime().toLocalDate();

				Long idcompany = resultSet.getLong("company_id");
				String companyName = resultSet.getString("compa.name");
				
				Company company = new Company(idcompany, companyName);
				Computer computer = new Computer();
				computer.setId(id);
				computer.setName(name);
				computer.setIntroduced(introduced);
				computer.setDiscontinued(discontinued);
				computer.setCompany(company);
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
	 * @see persistence.ComputerDAO#findAllComputers(int, int)
	 */
	@Override
	public List<Computer> findAllComputers(int limit, int offset) {
		List<Computer> computers = new ArrayList<Computer>();
		Connection connection = null;
		// Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		LocalDate introduced = null;
		LocalDate discontinued = null;

		try {
			connection = DaoFactory.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id limit ? offset ?;");
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				if (resultSet.getTimestamp("introduced") != null)
					introduced = resultSet.getTimestamp("introduced")
							.toLocalDateTime().toLocalDate();
				if (resultSet.getTimestamp("discontinued") != null)
					discontinued = resultSet.getTimestamp("discontinued")
							.toLocalDateTime().toLocalDate();

				Long idcompany = resultSet.getLong("company_id");
				String companyName = resultSet.getString("compa.name");
				
				Company company = new Company(idcompany, companyName);
				Computer computer = new Computer();
				computer.setId(id);
				computer.setName(name);
				computer.setIntroduced(introduced);
				computer.setDiscontinued(discontinued);
				computer.setCompany(company);
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
		LocalDate introduced = null;
		LocalDate discontinued = null;

		try {
			connection = DaoFactory.INSTANCE.getConnection();
			// statement = connection.createStatement();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id WHERE comp.id=?;");
			preparedStatement.setLong(1, id);
			preparedStatement.executeQuery();
			resultSet = preparedStatement.getResultSet();
			if (resultSet.next()) {
//				int idc = resultSet.getInt("id");
				String name = resultSet.getString("name");
				if (resultSet.getTimestamp("introduced") != null)
					introduced = resultSet.getTimestamp("introduced")
							.toLocalDateTime().toLocalDate();
				if (resultSet.getTimestamp("discontinued") != null)
					discontinued = resultSet.getTimestamp("discontinued")
							.toLocalDateTime().toLocalDate();

				Long idcompany = resultSet.getLong("company_id");
				String companyName = resultSet.getString("compa.name");
				
				Company company = new Company(idcompany, companyName);
				computer.setId(id);
				computer.setName(name);
				computer.setIntroduced(introduced);
				computer.setDiscontinued(discontinued);
				computer.setCompany(company);
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
			connection = DaoFactory.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement("INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (?,?,?,?);");
			preparedStatement.setString(1, computer.getName());

			if (computer.getIntroduced() != null)
				preparedStatement.setDate(2,
						Date.valueOf(computer.getIntroduced()));
			else
				preparedStatement.setDate(2, null);

			if (computer.getDiscontinued() != null)
				preparedStatement.setDate(3,
						Date.valueOf(computer.getDiscontinued()));
			else
				preparedStatement.setDate(3, null);

			if(computer.getCompany().getId() > 0)
			preparedStatement.setLong(4, computer.getCompany().getId());
			else
				preparedStatement.setLong(4, 0);
			
			preparedStatement.executeUpdate();
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

			if (computer.getIntroduced() != null)
				preparedStatement.setDate(2,
						Date.valueOf(computer.getIntroduced()));
			else
				preparedStatement.setDate(2, null);

			if (computer.getDiscontinued() != null)
				preparedStatement.setDate(3,
						Date.valueOf(computer.getDiscontinued()));
			else
				preparedStatement.setDate(3, null);			
			
			if(computer.getCompany().getId() > 0)
				preparedStatement.setLong(4, computer.getCompany().getId());
				else
					preparedStatement.setLong(4, 0);
			
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistence.ComputerDAO#getCountComputers()
	 */
	@Override
	public int getCountComputers() {
		Connection connexion = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		int count = 0;

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
