package persistence;

import java.sql.Connection;

import model.Page;
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
	
	public final void testGetConnection() {
		fail("Not yet implemented"); // TODO
//		assertEquals(Connection.class,daoFactory.getConnection());
	}

	public final void testCloseConnection() {
		fail("Not yet implemented"); // TODO
	}

	public final void testGetCompanyDAO() {
		fail("Not yet implemented"); // TODO
	}

	public final void testGetComputerDAO() {
		fail("Not yet implemented"); // TODO
	}

}
