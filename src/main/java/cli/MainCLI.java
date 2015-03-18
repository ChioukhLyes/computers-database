package cli;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Company;
import model.Computer;
import model.Page;
import service.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class MainCLI.
 */
public class MainCLI {

	/** The service. */
	static Service service = new Service();
	static Logger logger = LoggerFactory.getLogger(Class.class);

	/**
	 * Main cli.
	 *
	 * @throws ParseException
	 *             the parse exception
	 */
	private static void mainCLI() throws ParseException {

		
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
	public static void showComputers() throws ParseException {
		int fromIndex = 0;
		int toIndex = 5;
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
		List<Computer> computers = service.findAllComputers();
		Page<Computer> pagination = new Page<Computer>();
		while (true) {
			pagination.showPaginatedList(pagination.paginate(computers,
					fromIndex, toIndex));

			String choice = null;
			while (choice == null || !choices.contains(choice.toLowerCase())) {
				System.out.println("\nShow more computers ? [o/n] :");
				choice = scanner.next();
			}

			switch (choice.toLowerCase()) {
			case "o":
				fromIndex = toIndex;
				toIndex += 5;
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
	public static void showCompanies() throws ParseException {
		int fromIndex = 0;
		int toIndex = 5;
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
		List<Company> companies = service.findAllCompanies();
		Page<Company> pagination = new Page<Company>();
		while (true) {
			pagination.showPaginatedList(pagination.paginate(companies,
					fromIndex, toIndex));
			String choice = null;
			while (choice == null || !choices.contains(choice.toLowerCase())) {
				System.out.println("\nShow more companies ? [o/n] :");
				choice = scanner.next();
			}

			switch (choice.toLowerCase()) {
			case "o":
				fromIndex = toIndex;
				toIndex += 5;
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
	public static void showComputerDetails() throws ParseException {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Long choice = null;

		while (choice == null || !(choice instanceof Long)) {
			System.out.println("Enter computer id (Long) : ");
			choice = scanner.nextLong();
		}
		System.out.println("Computer details :");
		System.out.println(service.findComputerById(choice).toString());
		mainCLI();
	}

	/**
	 * Creates the computer.
	 *
	 * @throws ParseException
	 *             the parse exception
	 */
	public static void createComputer() throws ParseException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String name = null;
		LocalDateTime introduced = null;
		LocalDateTime discontinued = null;
		Long companyId = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date parsedDate = null;

		while (name == null) {
			System.out.println("Enter computer name : ");
			// scanner.nextLine();
			name = scanner.nextLine();
		}

		while (introduced == null) {
			System.out
					.println("Enter computer introduced date (yyyy/MM/dd) : ");
			parsedDate = dateFormat.parse(scanner.next());
			Instant instant = Instant.ofEpochMilli(parsedDate.getTime());
			introduced = LocalDateTime.ofInstant(instant,
					ZoneId.systemDefault());
		}

		while (discontinued == null) {
			System.out
					.println("Enter computer discontinued date (yyyy/MM/dd) : ");
			parsedDate = dateFormat.parse(scanner.next());
			Instant instant = Instant.ofEpochMilli(parsedDate.getTime());
			discontinued = LocalDateTime.ofInstant(instant,
					ZoneId.systemDefault());
		}

		System.out.println("Enter company id (Long) : ");
		companyId = scanner.nextLong();

		try {
			service.insertComputer(new Computer(name, introduced, discontinued,
					companyId));
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
	public static void updateComputer() throws ParseException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Computer computer = null;
		Long id = null;
		String name = null;
		LocalDateTime introduced = null;
		LocalDateTime discontinued = null;
		Long companyId = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date parsedDate = null;

		while (id == null || computer == null) {
			System.out.println("Enter computer id (Long) : ");
			id = scanner.nextLong();
			computer = service.findComputerById(id);
		}

		while (name == null) {
			System.out.println("Current computer name : " + computer.getName());
			System.out.println("Enter new computer name : \t");
			scanner.nextLine();
			name = scanner.nextLine();
		}
		computer.setName(name);

		while (introduced == null) {
			System.out.println("Current computer introduced date : "
					+ computer.getIntroduced());
			System.out
					.println("Enter new computer introduced date (yyyy/MM/dd) : ");
			parsedDate = dateFormat.parse(scanner.next());
			Instant instant = Instant.ofEpochMilli(parsedDate.getTime());
			introduced = LocalDateTime.ofInstant(instant,
					ZoneId.systemDefault());
		}
		computer.setIntroduced(introduced);

		while (discontinued == null) {
			System.out.println("Current computer discontinued date : "
					+ computer.getDiscontinued());
			System.out
					.println("Enter new computer discontinued date (yyyy/MM/dd) : ");
			parsedDate = dateFormat.parse(scanner.next());
			Instant instant = Instant.ofEpochMilli(parsedDate.getTime());
			discontinued = LocalDateTime.ofInstant(instant,
					ZoneId.systemDefault());
		}
		computer.setDiscontinued(discontinued);

		System.out.println("Current  company id  : " + computer.getCompanyId());
		System.out.println("Enter new company id (Long) : ");
		companyId = scanner.nextLong();
		if (companyId != null && companyId instanceof Long)
			computer.setCompanyId(companyId);

		try {
			service.updateComputer(computer);
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
	public static void deleteComputer() throws ParseException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Computer computer = null;
		Long id = null;
		while (id == null || computer == null) {
			System.out.println("Enter computer id (Long) : ");
			id = scanner.nextLong();
			computer = service.findComputerById(id);
		}

		try {
			service.deleteComputer(computer);
			System.out.println("The following computer has been deleted : "
					+ computer.toString());
		} catch (Exception e) {
			System.err.println("Computer not deleted" + e.getMessage());
		}
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
		mainCLI();
	}
}
