/*
 * 
 */
package com.excilys.persistence.impl;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Dao objects.
 */
@Component
public class DaoFactory {

	/** The Constant FILE_NAME. */
	// private static final String FILE_NAME = "/MySqlProperties.properties";

	@Autowired
	private DataSource dataSource;

	/** The company dao impl. */
	@Autowired
	CompanyDaoImpl companyDaoImpl;

	/** The computer dao impl. */
	@Autowired
	ComputerDaoImpl computerDaoImpl;
	// /** The config prop. */
	// private Properties configProp = new Properties();

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(DaoFactory.class);

	// /** The dao factory. */
	// private static DaoFactory daoFactory;

	// /** The connection pool. */
	// private BoneCP connectionPool;

	/** The Constant threadConnection. */
	private static final ThreadLocal<Connection> threadConnection = new ThreadLocal<Connection>();

	/**
	 * Instantiates a new dao factory.
	 */
	public DaoFactory() {
		try {
			Driver monDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(monDriver);

		} catch (SQLException e2) {
			logger.error(e2.getMessage());
			System.err.println(e2.getMessage());
		}

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e1) {
			logger.error(e1.getMessage());
			System.err.println(e1.getMessage());
		}

	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		try {
			if (threadConnection.get() == null
					|| threadConnection.get().isClosed()) {
				threadConnection.set(dataSource.getConnection());
				return threadConnection.get();
			} else
				return threadConnection.get();

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Close connections.
	 *
	 * @param connection
	 *            the connection
	 * @param preparedStatement
	 *            the prepared statement
	 * @param resultSet
	 *            the result set
	 */
	public void closeConnections(Connection connection,
			PreparedStatement preparedStatement, ResultSet resultSet) {

		if (threadConnection.get() != null) {
			try {
				threadConnection.get().close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				logger.error("Impossible to close connection.");
			}
		}

		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				logger.error("Impossible to close preparedStatement.");
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				logger.error("Impossible to close resultSet.");
			}
		}
	}

	/**
	 * Gets the data source.
	 *
	 * @return the data source
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Sets the data source.
	 *
	 * @param dataSource
	 *            the new data source
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Gets the company dao.
	 *
	 * @return the company dao
	 */
	public CompanyDAO getCompanyDAO() {
		return companyDaoImpl;
	}

	/**
	 * Gets the computer dao.
	 *
	 * @return the computer dao
	 */
	public ComputerDAO getComputerDAO() {
		return computerDaoImpl;
	}
}
