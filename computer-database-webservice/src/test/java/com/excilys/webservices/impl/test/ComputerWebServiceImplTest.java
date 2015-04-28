package com.excilys.webservices.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.message.internal.MessageBodyProviderNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dto.ComputerDTO;
import com.excilys.models.Computer;

/**
 * filComputerWebServiceImplTest.java
 */
public class ComputerWebServiceImplTest {

	private Client mainCliClient;
	private WebTarget targetComputers;
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

		/** The target computers. */
		targetComputers = mainCliClient
				.target("http://localhost:8080/computer-database-webservice/rest/computers");
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
		targetComputers = null;
	}

	/**
	 * Test get computers pages.
	 */
	@Test
	public final void testGetComputersPages() {

		// GIVEN
		int limit = 5;
		int offset = 0;
		// WHENE
		List<ComputerDTO> computers = targetComputers.path("/listpage")
				.queryParam("limit", limit).queryParam("offset", offset)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<ComputerDTO>>() {
				});

		// THEN
		assertNotNull(computers);
		assertTrue(computers.get(0) instanceof ComputerDTO);
		assertEquals(computers.size(), 5);
	}

	/**
	 * Test get all computers.
	 */
	@Test
	public final void testGetAllComputers() {

		// GIVEN
		// WHENE
		List<ComputerDTO> computers = targetComputers.path("/list")
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<ComputerDTO>>() {
				});

		// THEN
		assertNotNull(computers);
		assertTrue(computers.get(0) instanceof ComputerDTO);
	}

	/**
	 * Test get computers pages error.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public final void testGetComputersPagesError() {

		// GIVEN
		int limit = -1;
		int offset = -1;
		// WHENE
		List<ComputerDTO> computers = targetComputers.path("/listpage")
				.queryParam("limit", limit).queryParam("offset", offset)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<ComputerDTO>>() {
				});
		// THEN
		computers.get(0);

	}

	/**
	 * Test get all computers error.
	 */
	@Test(expected = NotFoundException.class)
	public final void testGetAllComputersError() {

		// GIVEN
		// WHENE
		List<ComputerDTO> computers = targetComputers.path("/listMM")
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<ComputerDTO>>() {
				});
		// THEN
		computers.get(0);

	}

	/**
	 * Test get computer.
	 */
	@Test
	public final void testGetComputer() {

		// GIVEN
		long id = 5;
		// WHENE
		Response response = targetComputers.path("/list/" + id)
				.request(MediaType.APPLICATION_JSON).get();
		ComputerDTO computer = response.readEntity(ComputerDTO.class);

		// THEN
		assertNotNull(computer);
		assertTrue(computer instanceof ComputerDTO);
		assertEquals(computer.getId(), id);
	}

	/**
	 * Test get computer error.
	 */
	@Test(expected = MessageBodyProviderNotFoundException.class)
	public final void testGetComputerError() {

		// GIVEN
		long id = -1;
		// WHENE
		Response response = targetComputers.path("/list/" + id)
				.request(MediaType.APPLICATION_JSON).get();
		ComputerDTO computer = response.readEntity(ComputerDTO.class);

		// THEN
		assertNotNull(computer);
		assertTrue(computer instanceof ComputerDTO);
		assertEquals(computer.getId(), id);
	}

	/**
	 * Test add computer.
	 */
	@Test
	public final void testAddComputer() {

		// GIVEN
		ComputerDTO computerDTO = new ComputerDTO();
		// WHENE
		computerDTO.setName("TestWS");
		computerDTO.setIntroduced(null);
		computerDTO.setDiscontinued(LocalDate.parse("12-12-2000",
				DateTimeFormatter.ofPattern("MM-dd-yyyy")));
		computerDTO.setCompanyId((long) 2);
		Response response = targetComputers.path("/add")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(computerDTO, MediaType.APPLICATION_JSON));

		// THEN
		assertNotNull(response);
		assertEquals(response.getStatus(), 201);

	}

	/**
	 * Test add computer error object.
	 */
	@Test
	public final void testAddComputerErrorObject() {

		// GIVEN
		// WHENE

		Response response = targetComputers
				.path("/add")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(new Computer(), MediaType.APPLICATION_JSON));

		// THEN
		assertNotNull(response);
		assertEquals(response.getStatus(), 400);

	}

	/**
	 * Test add computer error url.
	 */
	@Test
	public final void testAddComputerErrorUrl() {

		// GIVEN
		// WHENE

		Response response = targetComputers
				.path("/addddd")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(new Computer(), MediaType.APPLICATION_JSON));

		// THEN
		assertNotNull(response);
		assertEquals(response.getStatus(), 404);

	}

	/**
	 * Test update computer.
	 */
	@Test
	public final void testUpdateComputer() {

		// GIVEN
		ComputerDTO computerDTO = new ComputerDTO();
		// WHENE
		computerDTO.setId(450);
		computerDTO.setName("TestWSUpdated");
		computerDTO.setIntroduced(null);
		computerDTO.setDiscontinued(LocalDate.parse("10-10-2000",
				DateTimeFormatter.ofPattern("MM-dd-yyyy")));
		computerDTO.setCompanyId((long) 2);
		Response response = targetComputers.path("/update")
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(computerDTO, MediaType.APPLICATION_JSON));

		// THEN
		assertNotNull(response);
		assertEquals(response.getStatus(), 201);

	}

	/**
	 * Test update computer error object.
	 */
	@Test
	public final void testUpdateComputerErrorObject() {

		// GIVEN
		// WHENE

		Response response = targetComputers.path("/update")
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(new Computer(), MediaType.APPLICATION_JSON));

		// THEN
		assertNotNull(response);
		assertEquals(response.getStatus(), 400);

	}

	/**
	 * Test update computer error url.
	 */
	@Test
	public final void testUpdateComputerErrorUrl() {

		// GIVEN
		// WHENE

		Response response = targetComputers
				.path("/updateee")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(new Computer(), MediaType.APPLICATION_JSON));

		// THEN
		assertNotNull(response);
		assertEquals(response.getStatus(), 404);

	}

	// @Test
	// public final void testDeleteComputer() {
	//
	// // GIVEN
	// Long id = (long) 732;
	// // WHENE
	//
	// Response response = targetComputers.path("/delete/" + id)
	// .request(MediaType.APPLICATION_JSON).delete();
	//
	// // THEN
	// assertNotNull(response);
	// assertEquals(response.getStatus(), 201);
	//
	// }

	/**
	 * Test delete computer error object.
	 */
	@Test
	public final void testDeleteComputerErrorObject() {

		// GIVEN
		Long id = (long) -1;
		// WHENE
		Response response = targetComputers.path("/delete/" + id)
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

		Response response = targetComputers.path("/deleteeee/" + id)
				.request(MediaType.APPLICATION_JSON).delete();

		// THEN
		assertNotNull(response);
		assertEquals(response.getStatus(), 404);

	}

}
