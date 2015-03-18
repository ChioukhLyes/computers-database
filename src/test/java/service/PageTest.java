package service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import model.Page;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class PageTest.
 */
public class PageTest extends TestCase {

	/** The page. */
	Page<String> page;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		page = new Page<String>();
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		this.page = null;
		super.tearDown();
	}

	/**
	 * Test paginate sub list elements.
	 */
	@Test
	public void testPaginateSubListElements() {
		// Given
		List<String> list = new ArrayList<String>();
		List<String> sublist = new ArrayList<String>();

		// When
		list.add("First");
		sublist = page.paginate(list, 0, 1);
		// Then
		assertNotNull(sublist);
		assertEquals("First", sublist.get(0));
	}

	/**
	 * Test paginate exception.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testPaginateException() {
		// Given
		List<String> list = new ArrayList<String>();

		// Whene
		list.add("First");
		list.add("Seconde");
		// Then
		try {
			page.paginate(list, 4, 0);
			assertFalse(false);
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}

}
