/**
 * 
 */
package com.excilys.webservices.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.models.Company;

/**
 * filCompanyWebServiceImplTest.java
 */
public class CompanyWebServiceImplTest {

	private Client mainCliClient;
	private WebTarget targetCompanies;
	/** The logger. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(Class.class);

	@Rule
	public ExpectedException exception = ExpectedException.none();

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		/** The main cli client. */
		mainCliClient = ClientBuilder.newBuilder()
				.register(JacksonFeature.class).build();

		/** The target companies. */
		targetCompanies = mainCliClient
				.target("http://localhost:8080/computer-database-webservice/rest/companies");
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@After
	public void tearDown() throws Exception {
		mainCliClient = null;
		targetCompanies = null;
	}

	/**
	 * Test get companies pages.
	 */
	@Test
	public final void testGetCompaniesPages() {

		// GIVEN
		int limit = 5;
		int offset = 0;
		// WHENE
		List<Company> companies = targetCompanies.path("/listpage")
				.queryParam("limit", limit).queryParam("offset", offset)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Company>>() {
				});

		// THEN
		assertNotNull(companies);
		assertTrue(companies.get(0) instanceof Company);
		assertEquals(companies.size(), 5);
	}

	/**
	 * Test get all companies.
	 */
	@Test
	public final void testGetAllCompanies() {

		// GIVEN
		// WHENE
		List<Company> companies = targetCompanies.path("/list")
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Company>>() {
				});

		// THEN
		assertNotNull(companies);
		assertTrue(companies.get(0) instanceof Company);
	}

	/**
	 * Test get companies pages error.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public final void testGetCompaniesPagesError() {

		// GIVEN
		int limit = -1;
		int offset = -1;
		// WHENE
		List<Company> companies = targetCompanies.path("/listpage")
				.queryParam("limit", limit).queryParam("offset", offset)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Company>>() {
				});
		// THEN
		companies.get(0);

	}

	/**
	 * Test get all companies error.
	 */
	@Test(expected = NotFoundException.class)
	public final void testGetAllCompaniesError() {

		// GIVEN
		// WHENE
		List<Company> companies = targetCompanies.path("/listMM")
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Company>>() {
				});
		// THEN
		companies.get(0);

	}

	/**
	 * Test delete computer error object.
	 */
	@Test
	public final void testDeleteComputerErrorObject() {

		// GIVEN
		Long id = (long) -1;
		// WHENE
		Response response = targetCompanies.path("/delete/" + id)
				.request(MediaType.APPLICATION_JSON).delete();

		// THEN
		assertNotNull(response);
		assertEquals(response.getStatus(), 404);

	}

	/**
	 * Test delete computer error url.
	 */
	@Test
	public final void testDeleteComputerErrorUrl() {

		// GIVEN
		Long id = (long) 731;
		// WHENE

		Response response = targetCompanies.path("/deleteeee/" + id)
				.request(MediaType.APPLICATION_JSON).delete();

		// THEN
		assertNotNull(response);
		assertEquals(response.getStatus(), 404);

	}

}
