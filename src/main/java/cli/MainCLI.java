package cli;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Company;
import model.Computer;

import org.apache.commons.validator.routines.DateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class MainCLI.
 */
public class MainCLI {

	/** The service. */
	static Service service = new Service();

	/** The logger. */
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
		List<Computer> computers;
		// Page<Computer> pagination = new Page<Computer>();
		while (true) {
			computers = service.findAllComputers(limit, offset);
			System.out.println(computers.size() + " okok");
			for (Computer computer : computers) {
				System.out.println(computer.toString());
			}

			// pagination.showPaginatedList(pagination.paginate(computers,
			// fromIndex, toIndex));

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
	public static void showCompanies() throws ParseException {
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
		List<Company> companies;
		// Page<Computer> pagination = new Page<Computer>();
		while (true) {
			companies = service.findAllCompanies(limit, offset);
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
		LocalDate introduced = null;
		LocalDate discontinued = null;
		Long companyId = null;
		// SimpleDateFormat dateFormat =
		// SimpleDateFormat.ofPattern("yyyy/MM/dd");
		DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String donnee;

		while (name == null) {
			System.out.println("Enter computer name : ");
			// scanner.nextLine();
			name = scanner.nextLine();
		}

		while (introduced == null) {
			System.out
					.println("Enter computer introduced date (yyyy-MM-dd) : ");
			donnee = scanner.next();
			if (DateValidator.getInstance().isValid(donnee, "yyyy-MM-dd")) {
				introduced = LocalDate.parse(donnee, dateFormat);
			}
		}

		while (discontinued == null) {
			System.out
					.println("Enter computer discontinued date (yyyy-MM-dd) : ");
			donnee = scanner.next();
			if (DateValidator.getInstance().isValid(donnee, "yyyy-MM-dd")) {
				discontinued = LocalDate.parse(donnee, dateFormat);
			}
		}

		System.out.println("Enter company id (Long) : ");
		companyId = scanner.nextLong();

		try {
			service.insertComputer(new Computer(name, introduced, discontinued,	new Company(companyId, null)));
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
		LocalDate introduced = null;
		LocalDate discontinued = null;
		Long companyId = null;
		// DateTimeFormatter dateFormat = DateTimeFormatter
		// .ofPattern("yyyy/MM/dd");
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String donnee;
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
					.println("Enter new computer introduced date (yyyy-MM-dd) : ");
			donnee = scanner.next();
			if (DateValidator.getInstance().isValid(donnee, "yyyy-MM-dd")) {
				introduced = LocalDate.parse(donnee, dateFormat);
			}
		}
		computer.setIntroduced(introduced);

		while (discontinued == null) {
			System.out.println("Current computer discontinued date : "
					+ computer.getDiscontinued());
			System.out
					.println("Enter new computer discontinued date (yyyy-MM-ddd) : ");
			donnee = scanner.next();
			if (DateValidator.getInstance().isValid(donnee, "yyyy-MM-dd")) {
				discontinued = LocalDate.parse(donnee, dateFormat);
			}
		}
		computer.setDiscontinued(discontinued);

		System.out.println("Current  company id  : " + computer.getCompany().getId());
		System.out.println("Enter new company id (Long) : ");
		companyId = scanner.nextLong();
		if (companyId != null && companyId instanceof Long)
			computer.getCompany().setId(companyId);

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
			scanner.next();
			if (scanner.hasNextLong()) {
				id = scanner.nextLong();
				computer = service.findComputerById(id);
			}
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
