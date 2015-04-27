package com.excilys.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.models.Page;

// TODO: Auto-generated Javadoc
/**
 * The Class PageTest.
 */
public class PageTest {

	/** The page. */
	private Page<String> page;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		// TODO Auto-generated method stub
		page = new Page<String>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
		// TODO Auto-generated method stub
		this.page = null;
	}

	
	/**
	 * Test paginate delete entities.
	 */
	@Test
	public void testPaginateDeleteEntities() {

		// Given
		List<String> list = new ArrayList<String>();
		String first = new String("first");
		String seconde = new String("seconde");
		list.add(first);
		list.add(seconde);
		page.setEntities(list);

		// When
		page.deleteFromEntities(first);

		// Then
		assertNotNull(page.getEntities());
		assertEquals(page.getEntities().size(), 1);
		assertEquals(page.getEntities().get(0), seconde);
	}
	
	
	
	/**
	 * Test paginate delete entities exception.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testPaginateDeleteEntitiesException() {

		// Given
		List<String> list = new ArrayList<String>();
		String first = new String("first");
		String seconde = new String("seconde");
		list.add(first);
		list.add(seconde);
		page.setEntities(list);

		// When
		page.deleteFromEntities(first);
		page.deleteFromEntities(seconde);
		page.deleteFromEntities(first);

		// Then
		assertEquals(page.getEntities().get(0), seconde);
	}
	

	
	/**
	 * Test paginate delete entities false.
	 */
	@Test
	public void testPaginateDeleteEntitiesFalse() {
		// Given

		// Whene
		page.setEntities(null);

		// Then
		assertNull(page.getEntities());
		assertFalse(page.deleteFromEntities(new String("False")));
	}

}
