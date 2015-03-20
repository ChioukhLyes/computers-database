package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.DateValidator;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.Company;
import model.Computer;
import service.Service;

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
			.getLogger(EditComputer.class);

	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Service service = new Service();
		List<Company> lisCompanies = service.findAllCompanies();
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
		
		Computer computer = new Computer();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Service service = new Service();
		String name = (String) request.getParameter("computerName");
		computer.setName(name);
		
		
		if(DateValidator.getInstance().isValid(request.getParameter("introduced"),"yyyy-MM-dd")){
			System.out.println("I'am in");
			LocalDate introduced = LocalDate.parse("2000-02-02", formatter);
			System.out.println(LocalDateTime.of(introduced, null)+" 12 ");
//			LocalDateTime introduced = LocalDateTime.parse("2000-02-02", formatter); 
			//computer.setIntroduced(introduced);
		}
		
		if(DateValidator.getInstance().isValid(request.getParameter("discontinued"),"yyyy-MM-dd")){
			LocalDateTime discontinued = LocalDateTime.parse(request.getParameter("discontinued"), formatter); 
			computer.setDiscontinued(discontinued);
		}
		
		Long companyId = (long) Long.valueOf(request.getParameter("companyId"));
		computer.setCompanyId(companyId);
		System.out.println("Finalll  " + companyId);
		
		//Computer insertion
		service.insertComputer(computer);
		List<Computer> lisComputers = service.findAllComputers();
		request.setAttribute("Computers", lisComputers);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		dispatcher.forward(request, response);
		logger.info("Computer created with success, redirecting to the Dashboard page.");
	}
	
}
