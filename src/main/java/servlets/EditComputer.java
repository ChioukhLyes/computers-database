package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import model.Computer;

import org.slf4j.LoggerFactory;

import service.Service;
import ch.qos.logback.classic.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class EditComputer.
 */
@WebServlet("/editComputer")
public class EditComputer extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(EditComputer.class);

	/**
	 * Instantiates a new edits the computer.
	 */
	public EditComputer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Long id = (long) Long.valueOf(request.getParameter("id"));
		Service service = new Service();
		List<Company> lisCompanies = service.findAllCompanies();
		request.setAttribute("Companies", lisCompanies);
		if (id != 0) {
			Computer computer = service.findComputerById(id);
			request.setAttribute("Computer", computer);
			request.setAttribute("companyName",
					service.findCompanyById(computer.getCompanyId()).getName());
		}
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editComputer.jsp");
		dispatcher.forward(request, response);
		logger.trace("Redirecting to the EditComputer page.");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Service service = new Service();
		String name = (String) request.getParameter("computerName");
		LocalDateTime introduced = LocalDateTime.now(); // parse("1991/01/01", formatter);
		LocalDateTime discontinued = LocalDateTime.now(); // parse("1991/01/02", formatter);
		Long companyId = (long) Long.valueOf(request.getParameter("companyId"));
		
		
//		
//		System.out.println("Finalll  " + name);
//		System.out.println("Finalll  " + introduced);
//		System.out.println("Finalll  " + discontinued);
		System.out.println("Finalll  " + companyId);
//		
		
		Computer computer = new Computer();
		computer.setName(name);
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		computer.setCompanyId(companyId);
		//Computer insertion
		service.updateComputer(computer);
		List<Computer> lisComputers = service.findAllComputers();
		request.setAttribute("Computers", lisComputers);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		dispatcher.forward(request, response);
		logger.info("Success editing, redirecting to the Dashboard page.");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
}
