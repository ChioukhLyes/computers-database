package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;

import org.apache.commons.validator.routines.DateValidator;
import org.slf4j.LoggerFactory;

import services.ServiceCompany;
import services.ServiceComputer;
import ch.qos.logback.classic.Logger;
import dto.ComputerDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class AddComputer.
 */
@WebServlet("/addComputer")
public class AddComputer extends HttpServlet{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Instantiates a new adds the computer.
	 */
	public 	AddComputer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(AddComputer.class);

	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		ServiceCompany serviceCompany = new ServiceCompany();
		List<Company> lisCompanies = serviceCompany.findAllCompanies();
		request.setAttribute("Companies", lisCompanies);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/addComputer.jsp");
		dispatcher.forward(request, response);

		// if ("include".equalsIgnoreCase(action)) {
		// rd.include(request, response);
		// } else if ("forward".equalsIgnoreCase(action)) {
		// rd.forward(request, response);
		// }

	}

	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ComputerDTO computer = new ComputerDTO();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ServiceComputer serviceComputer = new ServiceComputer();
		ServiceCompany serviceCompany = new ServiceCompany();
		String name = (String) request.getParameter("computerName");
		computer.setName(name);
		
		
		if (DateValidator.getInstance().isValid(
				request.getParameter("introduced"), "yyyy-MM-dd")) {
			LocalDate introduced = LocalDate.parse(
					request.getParameter("introduced"), formatter);
			computer.setIntroduced(introduced);
		}

		if (DateValidator.getInstance().isValid(
				request.getParameter("discontinued"), "yyyy-MM-dd")) {
			LocalDate discontinued = LocalDate.parse(
					request.getParameter("discontinued"), formatter);
			computer.setDiscontinued(discontinued);
		}
		
		Long companyId = (long) Long.valueOf(request.getParameter("companyId"));
		Company company =  serviceCompany.findCompanyById(companyId);
		computer.setCompanyId(companyId);
		computer.setCompanyName(company.getName());
		//Computer insertion
		serviceComputer.insertComputer(computer);
//		List<Computer> lisComputers = service.findAllComputers();
//		request.setAttribute("Computers", lisComputers);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		dispatcher.forward(request, response);
		logger.info("Computer created with success, redirecting to the Dashboard page.");
	}
	
}
