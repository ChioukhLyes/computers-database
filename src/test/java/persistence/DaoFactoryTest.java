package persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import junit.framework.TestCase;

public class DaoFactoryTest extends TestCase {

	DaoFactory daoFactory;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		this.daoFactory = DaoFactory.INSTANCE;
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		this.daoFactory = null;
		super.tearDown();
	}
	
	@Test
	public void testGetConnection() {
		// Given
//		Connection connection = daoFactory.getConnection();
		// Whene
		
		// Then

		// fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCloseConnection() {

		boolean isClosed = false;
		// Given
		Connection connection = DaoFactory.INSTANCE.getConnection();
		// Whene
		daoFactory.CloseConnection(connection);
		try {
			isClosed = connection.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		// Then
		assertNotNull(connection);
		assertEquals(isClosed, true);
	}

	@Test
	public void testGetCompanyDAO() {
		//Given
		//Whene
		CompanyDAO companyDAO =  daoFactory.getCompanyDAO();
		//Then
		assertNotNull(companyDAO);
		assertTrue(companyDAO instanceof CompanyDAO);
		
	}

	@Test
	public void testGetComputerDAO() {
		//Given
		//Whene
		ComputerDAO compuerDAO =  daoFactory.getComputerDAO();
		//Then
		assertNotNull(compuerDAO);
		assertTrue(compuerDAO instanceof ComputerDAO);
	}

}
