/*
 * 
 */
package com.excilys.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.DateValidator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import ch.qos.logback.classic.Logger;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.services.ServiceCompany;
import com.excilys.services.ServiceComputer;

// TODO: Auto-generated Javadoc
/**
 * The Class AddComputer.
 */
@Controller
@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {

	/** The service company. */
	@Autowired
	ServiceCompany serviceCompany;

	/** The computer. */
	@Autowired
	ComputerDTO computer;

	/** The service computer. */
	@Autowired
	ServiceComputer serviceComputer;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * Instantiates a new adds the computer.
	 */
	public AddComputer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(AddComputer.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<Company> lisCompanies = serviceCompany.findAllCompanies();
		request.setAttribute("Companies", lisCompanies);
		request.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(
				request, response);
		logger.trace("Redirecting to the AddComputer page.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Long companyId = null;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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

		if (Long.valueOf(request.getParameter("companyId")) != 0) {
			companyId = (long) Long.valueOf(request.getParameter("companyId"));
			Company company = serviceCompany.findCompanyById(companyId);
			computer.setCompanyId(companyId);
			computer.setCompanyName(company.getName());
		}

		// Computer insertion
		serviceComputer.insertComputer(computer);
		request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(
				request, response);
		logger.trace("Computer created with success, redirecting to the success page.");
	}

}
