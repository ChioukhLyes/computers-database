package com.excilys.persistence.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Dao objects.
 */
public class DaoFactory {

	/** The Constant FILE_NAME. */
	private static final String FILE_NAME = "/MySqlProperties.properties";

	/** The config prop. */
	private Properties configProp = new Properties();

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(DaoFactory.class);

	/** The dao factory. */
	private static DaoFactory daoFactory;

	/** The connection pool. */
	private BoneCP connectionPool;

	/**
	 * Instantiates a new dao factory.
	 */
	private DaoFactory() {
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

		InputStream ips = DaoFactory.class.getResourceAsStream(FILE_NAME);
		try {
			configProp.load(ips);
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.err.println(e.getMessage());
		}
		String url = configProp.getProperty("MYSQL_DATABASE_URL");
		String user = configProp.getProperty("USER_NAME");
		String password = configProp.getProperty("PASSWORD");

		try {
			// Creation and configuration of pool object
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(url);
			config.setUsername(user);
			config.setPassword(password);
			// Pool size
			config.setMinConnectionsPerPartition(4);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(4);
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new RuntimeException("Unable to set up pool connections.", e);
		}
	}

	/**
	 * Gets the single instance of DaoFactory.
	 *
	 * @return single instance of DaoFactory
	 */
	public static DaoFactory getInstance() {
		if (daoFactory == null) {
			daoFactory = new DaoFactory();
			return daoFactory;
		} else {
			return daoFactory;
		}
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		try {
			return this.connectionPool.getConnection();
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
	public void CloseConnections(Connection connection,
			PreparedStatement preparedStatement, ResultSet resultSet) {

		if (connection != null) {
			try {
				connection.close();
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
	 * Gets the company dao.
	 *
	 * @return the company dao
	 */
	public CompanyDAO getCompanyDAO() {
		return new CompanyDaoImpl();
	}

	/**
	 * Gets the computer dao.
	 *
	 * @return the computer dao
	 */
	public ComputerDAO getComputerDAO() {
		return new ComputerDaoImpl();
	}
}
