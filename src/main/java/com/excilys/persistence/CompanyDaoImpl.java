package com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.mapper.impl.CompanyMapperImpl;
import com.excilys.model.Company;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyDaoImplementation.
 */
public class CompanyDaoImpl implements CompanyDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.CompanyDAO#findAllCompanies()
	 */
	@Override
	public List<Company> findAllCompanies() {

		List<Company> companies = new ArrayList<Company>();
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DaoFactory.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT id, name FROM company;");
			resultSet = preparedStatement.executeQuery();
			companies = CompanyMapperImpl.INSTANCE.MappCompanies(resultSet);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			DaoFactory.INSTANCE.CloseConnections(connection, preparedStatement,
					resultSet);
		}
		return companies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.CompanyDAO#findAllCompanies()
	 */
	@Override
	public List<Company> findAllCompanies(int limit, int offset) {

		List<Company> companies = new ArrayList<Company>();
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DaoFactory.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT id, name FROM company limit ? offset ?;");
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			resultSet = preparedStatement.executeQuery();
			companies = CompanyMapperImpl.INSTANCE.MappCompanies(resultSet);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			DaoFactory.INSTANCE.CloseConnections(connection, preparedStatement,
					resultSet);
		}
		return companies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.CompanyDAO#findCompanyById(java.lang.Long)
	 */
	@Override
	public Company findCompanyById(Long id) {
		Company company = new Company();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DaoFactory.INSTANCE.getConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("SELECT id, name FROM company WHERE id=" + id
							+ ";");

			company = CompanyMapperImpl.INSTANCE.MappCompany(resultSet);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			DaoFactory.INSTANCE.CloseConnections(connection, null, resultSet);
		}
		return company;
	}

	@Override
	public int getCountCompanies() {

		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		try {
			connection = DaoFactory.INSTANCE.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT count(*) FROM company;");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			DaoFactory.INSTANCE.CloseConnections(connection, preparedStatement,
					null);
		}
		return count;
	}

}
