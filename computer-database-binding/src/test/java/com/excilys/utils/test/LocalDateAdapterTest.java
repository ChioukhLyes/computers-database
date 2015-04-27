/**
 * 
 */
package com.excilys.utils.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.utils.LocalDateAdapter;

// TODO: Auto-generated Javadoc
/**
 * filLocalDateAdapterTest.java
 */
public class LocalDateAdapterTest {

	private LocalDateAdapter localDateAdapter;

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		localDateAdapter = new LocalDateAdapter();
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@After
	public void tearDown() throws Exception {
		localDateAdapter = null;
	}

	/**
	 * Unmarshal.
	 *
	 * @param dateString
	 *            the date string
	 * @return the local date
	 * @throws Exception
	 *             the exception
	 */
	public LocalDate unmarshal(String dateString) throws Exception {
		return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
	}

	/**
	 * Test unmarshal.
	 */
	@Test
	public final void testUnmarshal() {

		// GIVEN
		String date = "2000-12-12";
		// WHENE
		// THENE
		try {
			assertTrue(localDateAdapter.unmarshal(date) instanceof LocalDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test unmarshal error.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = DateTimeParseException.class)
	public final void testUnmarshalError() throws Exception {
		// GIVEN
		String date = "";
		// WHENE
		// THENE
		assertTrue(localDateAdapter.unmarshal(date) instanceof LocalDate);
	}

	/**
	 * Test marshal.
	 */
	@Test
	public final void testMarshal() {

		// GIVEN
		LocalDate date = LocalDate.parse("12-12-2000",
				DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		// WHENE
		// THENE
		try {
			assertTrue(localDateAdapter.marshal(date) instanceof String);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test marshal error.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test(expected = DateTimeParseException.class)
	public final void testMarshalError() throws Exception {
		// GIVEN
		LocalDate date = LocalDate.parse("");
		// WHENE
		// THENE
		assertTrue(localDateAdapter.marshal(date) instanceof String);
	}

	/**
	 * Test marshal unmarshal.
	 */
	@Test
	public final void testMarshalUnmarshal() {

		// GIVEN
		LocalDate date = LocalDate.parse("12-12-2000",
				DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		// WHENE
		// THENE
		try {
			assertTrue(localDateAdapter.marshal(date) instanceof String);
			assertEquals(localDateAdapter.marshal(date), "2000-12-12");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test unmarshal marshal.
	 */
	@Test
	public final void testUnmarshalMarshal() {

		// GIVEN
		String date = "2000-12-12";
		// WHENE
		// THENE
		try {
			assertTrue(localDateAdapter.unmarshal(date) instanceof LocalDate);
			assertEquals(
					localDateAdapter.unmarshal(date),
					LocalDate.parse("12-12-2000",
							DateTimeFormatter.ofPattern("MM-dd-yyyy")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
