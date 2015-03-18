package persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Dao objects.
 */
public enum DaoFactory {

	/** The instance. */
	INSTANCE;

	/** The Constant FILE_NAME. */
	private static final String FILE_NAME = "/MySqlProperties.properties";

	/** The config prop. */
	private Properties configProp = new Properties();

	// /** The Constant MYSQL_DATABASE_URL. */
	// private static final String MYSQL_DATABASE_URL =
	// "jdbc:mysql://localhost/computer-database-db?zeroDateTimeBehavior=convertToNull";
	//
	// /** The Constant USER_NAME. */
	// private static final String USER_NAME = "admincdb";
	//
	// /** The Constant PASSWORD. */
	// private static final String PASSWORD = "qwerty1234";
	private DaoFactory() {
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		Connection connection;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.err.println("eerrr"+ e1.getMessage());
		}

		try {
			InputStream ips = DaoFactory.class.getResourceAsStream(FILE_NAME);
			try {
				configProp.load(ips);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
			String url = configProp.getProperty("MYSQL_DATABASE_URL");
			String user = configProp.getProperty("USER_NAME");
			String password = configProp.getProperty("PASSWORD");
			connection = DriverManager.getConnection(url, user, password);
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Close connection.
	 *
	 * @param connection
	 *            the connection
	 */
	public void CloseConnection(Connection connection) {

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
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
