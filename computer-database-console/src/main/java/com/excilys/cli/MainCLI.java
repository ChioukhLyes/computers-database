package com.excilys.cli;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.validator.routines.DateValidator;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.excilys.dto.ComputerDTO;
import com.excilys.models.Company;
import com.excilys.service.ServiceCompany;
import com.excilys.service.ServiceComputer;

// TODO: Auto-generated Javadoc
/**
 * The Class MainCLI.
 */

@Component
public class MainCLI {

	/** The main cli client. */
	private Client mainCliClient = ClientBuilder.newBuilder()
			.register(JacksonFeature.class).build();

	/** The target companies. */
	private WebTarget targetCompanies = mainCliClient
			.target("http://localhost:8080/computer-database-webservice/rest/companies");

	/** The target computers. */
	private WebTarget targetComputers = mainCliClient
			.target("http://localhost:8080/computer-database-webservice/rest/computers");

	/** The service. */
	@Autowired
	private ServiceComputer serviceComputer;

	/** The service company. */
	@Autowired
	/** The service. */
	private ServiceCompany serviceCompany;

	/** The computer dto. */
	@Autowired
	private ComputerDTO computerDTO;

	/** The company. */
	@Autowired
	private Company company;

	/** The logger. */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(Class.class);

	/**
	 * Main cli.
	 *
	 * @throws ParseException
	 *             the parse exception
	 */
	private void mainCLI() throws ParseException {

		ArrayList<String> choices = new ArrayList<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			{
				add("a");
				add("b");
				add("c");
				add("d");
				add("e");
				add("f");
				add("g");
				add("exit");
			}
		};
		System.out.println("	************************");
		System.out.println("	- a) List computers");
		System.out.println("	- b) List companies");
		System.out.println("	- c) Show computer details ");
		System.out.println("	- d) Create a computer");
		System.out.println("	- e) Update a computer");
		System.out.println("	- f) Delete a computer");
		System.out.println("	- g) Delete a company");
		System.out.println("	- Type \"exit\" to finish prgram");
		System.out.println("	************************");

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String choice = null;
		while (choice == null || !choices.contains(choice.toLowerCase())) {
			System.out.println("Make your choice :");
			choice = scanner.next();
		}

		switch (choice.toLowerCase()) {
		case "a":
			showComputers();
			break;
		case "b":
			showCompanies();
			break;
		case "c":
			showComputerDetails();
			break;
		case "d":
			createComputer();
			break;
		case "e":
			updateComputer();
			break;
		case "f":
			deleteComputer();
			break;
		case "g":
			deleteCompany();
			break;
		case "exit":
			System.out.println("Program ended.");
			System.exit(0);
		}

	}

	/**
	 * Show computers.
	 *
	 * @throws ParseException
	 *             the parse exception
	 */
	public void showComputers() throws ParseException {
		int limit = 5;
		int offset = 0;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> choices = new ArrayList<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add("o");
				add("n");
			}
		};

		System.out.println("List computers (pages of 5 computers) :");
		while (true) {

			List<ComputerDTO> computers = targetComputers.path("/listpage")
					.queryParam("limit", limit).queryParam("offset", offset)
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<ComputerDTO>>() {
					});

			for (ComputerDTO computer : computers) {
				System.out.println(computer.toString());
			}

			String choice = null;
			while (choice == null || !choices.contains(choice.toLowerCase())) {
				System.out.println("\nShow more computers ? [o/n] :");
				choice = scanner.next();
			}

			switch (choice.toLowerCase()) {
			case "o":
				offset += limit;
				break;
			case "n":
				mainCLI();
				break;
			}
		}
	}

	/**
	 * Show companies.
	 *
	 * @throws ParseException
	 *             the parse exception
	 */
	public void showCompanies() throws ParseException {
		int limit = 5;
		int offset = 0;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> choices = new ArrayList<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add("o");
				add("n");
			}
		};

		System.out.println("List companies (pages of 5 companies) :");
		while (true) {
			List<Company> companies = targetCompanies.path("/listpage")
					.queryParam("limit", limit).queryParam("offset", offset)
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<Company>>() {
					});

			for (Company company : companies) {
				System.out.println(company.toString());
			}

			String choice = null;
			while (choice == null || !choices.contains(choice.toLowerCase())) {
				System.out.println("\nShow more companies ? [o/n] :");
				choice = scanner.next();
			}

			switch (choice.toLowerCase()) {
			case "o":
				offset += limit;
				break;
			case "n":
				mainCLI();
				break;
			}
		}
	}

	/**
	 * Show computer details.
	 *
	 * @throws ParseException
	 *             the parse exception
	 */
	public void showComputerDetails() throws ParseException {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Long choice = null;

		while (choice == null || !(choice instanceof Long)) {
			System.out.println("Enter computer id (Long) : ");
			choice = scanner.nextLong();
		}
		System.out.println("Computer details :");

		Response response = targetComputers.path("/list/" + choice)
				.request(MediaType.APPLICATION_JSON).get();

		if (response.getStatus() == 200) {
			System.out.println(response.readEntity(ComputerDTO.class));
		} else {
			throw new RuntimeException("[HTTP] Failed : error code "
					+ response.getStatus());
		}

		// System.out.println(serviceComputer.findComputerById(choice).toString());
		mainCLI();
	}

	/**
	 * Creates the computer.
	 *
	 * @throws ParseException
	 *             the parse exception
	 */
	public void createComputer() throws ParseException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String name = null;
		LocalDate introduced = null;
		LocalDate discontinued = null;
		Long companyId = null;
		// SimpleDateFormat dateFormat =
		// SimpleDateFormat.ofPattern("yyyy/MM/dd");
		
		DateTimeFormatter formatterUs = DateTimeFormatter
				.ofPattern("MM-dd-yyyy");
		DateTimeFormatter formatterFr = DateTimeFormatter
				.ofPattern("dd-MM-yyyy");
		
		String donnee;
		while (name == null) {
			System.out.println("Enter computer name : ");
			// scanner.nextLine();
			name = scanner.nextLine();
		}

		while (introduced == null) {
			System.out
					.println("Enter computer introduced date (MM-dd-yyyy or dd-MM-yyyy) : ");
			donnee = scanner.next();
			
			if (DateValidator.getInstance().isValid(donnee, "MM-dd-yyyy"))
				introduced = LocalDate.parse(donnee, formatterUs);
			else if (DateValidator.getInstance().isValid(donnee, "dd-MM-yyyy"))
				introduced = LocalDate.parse(donnee, formatterFr);
		}

		while (discontinued == null) {
			System.out
					.println("Enter computer discontinued date (MM-dd-yyyy or dd-MM-yyyy) : ");
			donnee = scanner.next();
			if (DateValidator.getInstance().isValid(donnee, "MM-dd-yyyy"))
				discontinued = LocalDate.parse(donnee, formatterUs);
			else if (DateValidator.getInstance().isValid(donnee, "dd-MM-yyyy"))
				discontinued = LocalDate.parse(donnee, formatterFr);
		}

		System.out.println("Enter company id (Long) : ");
		companyId = scanner.nextLong();

		try {
			computerDTO.setName(name);
			computerDTO.setIntroduced(introduced);
			computerDTO.setDiscontinued(discontinued);
			computerDTO.setCompanyId(companyId);
			// serviceComputer.insertComputer(computerDTO);

			Response response = targetComputers
					.path("/add")
					.request(MediaType.APPLICATION_JSON)
					.put(Entity
							.entity(computerDTO, MediaType.APPLICATION_JSON));

			if (response.getStatus() != 200 && response.getStatus() != 204) {
				throw new RuntimeException("[HTTP] Failed : error code :"
						+ response.getStatus());
			}

			System.out.println("Computer inserted");
		} catch (Exception e) {
			System.out.println("Computer not inserted" + e.getMessage());
		}
		mainCLI();
	}

	/**
	 * Update computer.
	 *
	 * @throws ParseException
	 *             the parse exception
	 */
	public void updateComputer() throws ParseException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Long id = null;
		String name = null;
		LocalDate introduced = null;
		LocalDate discontinued = null;
		Long companyId = null;
		// DateTimeFormatter dateFormat = DateTimeFormatter
		// .ofPattern("yyyy/MM/dd");
		DateTimeFormatter dateFormat = DateTimeFormatter
				.ofPattern("yyyy-MM-dd");
		String donnee;
		while (id == null || computerDTO == null) {
			System.out.println("Enter computer id (Long) : ");
			id = scanner.nextLong();
			computerDTO = serviceComputer.findComputerById(id);
		}

		while (name == null) {
			System.out.println("Current computer name : "
					+ computerDTO.getName());
			System.out.println("Enter new computer name : \t");
			scanner.nextLine();
			name = scanner.nextLine();
		}
		computerDTO.setName(name);

		while (introduced == null) {
			System.out.println("Current computer introduced date : "
					+ computerDTO.getIntroduced());
			System.out
					.println("Enter new computer introduced date (yyyy-MM-dd) : ");
			donnee = scanner.next();
			if (DateValidator.getInstance().isValid(donnee, "yyyy-MM-dd")) {
				introduced = LocalDate.parse(donnee, dateFormat);
			}
		}
		computerDTO.setIntroduced(introduced);

		while (discontinued == null) {
			System.out.println("Current computer discontinued date : "
					+ computerDTO.getDiscontinued());
			System.out
					.println("Enter new computer discontinued date (yyyy-MM-ddd) : ");
			donnee = scanner.next();
			if (DateValidator.getInstance().isValid(donnee, "yyyy-MM-dd")) {
				discontinued = LocalDate.parse(donnee, dateFormat);
			}
		}
		computerDTO.setDiscontinued(discontinued);

		System.out.println("Current  company id  : "
				+ computerDTO.getCompanyId());
		System.out.println("Enter new company id (Long) : ");
		companyId = scanner.nextLong();
		if (companyId != null && companyId instanceof Long)
			computerDTO.setCompanyId(companyId);

		try {
			Response response = targetComputers
					.path("/update")
					.request(MediaType.APPLICATION_JSON)
					.post(Entity
							.entity(computerDTO, MediaType.APPLICATION_JSON));

			if (response.getStatus() != 200 && response.getStatus() != 204) {
				throw new RuntimeException("[HTTP] Failed : error code :"
						+ response.getStatus());
			}

			System.out.println("Computer updated");
		} catch (Exception e) {
			System.out.println("Computer not updated" + e.getMessage());
		}
		mainCLI();
	}

	/**
	 * Delete computer.
	 *
	 * @throws ParseException
	 *             the parse exception
	 */
	public void deleteComputer() throws ParseException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Long id = null;
		while (id == null) {
			System.out.println("Enter computer id (Long) : ");
			//scanner.next();
			if (scanner.hasNextLong()) {
				id = scanner.nextLong();
				
				targetComputers.path("/delete/" + id)
						.request(MediaType.APPLICATION_JSON).delete();
				
				System.out.println("The computer whit id "+id+" has been deleted.");
			}
		}
		// try {
		// serviceComputer.deleteComputer(computerDTO);
		// System.out.println("The following computer has been deleted : "
		// + computerDTO.toString());
		// } catch (Exception e) {
		// System.err.println("Computer not deleted" + e.getMessage());
		// }
		mainCLI();

	}

	/**
	 * Delete company.
	 *
	 * @throws ParseException
	 *             the parse exception
	 */
	public void deleteCompany() throws ParseException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Long id = null;

		while (id == null) {
			System.out.println("Enter company id (Long) : ");
			if (scanner.hasNextLong()) {
				id = scanner.nextLong();
				
				targetCompanies.path("/delete/" + id)
						.request(MediaType.APPLICATION_JSON).delete();
				System.out.println("The company whit id "+id+" has been deleted.");
			}
		}
		// }
		// try {
		// serviceCompany.deleteCompany(company);
		// System.out.println("The following company has been deleted : "
		// + company.toString());
		// } catch (Exception e) {
		// System.err.println("Company not deleted " + e.getMessage());
		// }
		mainCLI();

	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws ParseException
	 *             the parse exception
	 */
	public static void main(String[] args) throws ParseException {

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"./application-context-console.xml");
		MainCLI mc = context.getBean(MainCLI.class);
		mc.mainCLI();
	}
}
