package com.excilys.servlets;

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

import org.apache.commons.validator.routines.DateValidator;
import org.slf4j.LoggerFactory;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.services.ServiceCompany;
import com.excilys.services.ServiceComputer;

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

		Long id = (long) Long.valueOf(request.getParameter("id"));
		ServiceComputer serviceComputer = new ServiceComputer();
		ServiceCompany serviceCompany = new ServiceCompany();
		List<Company> lisCompanies = serviceCompany.findAllCompanies();
		request.setAttribute("Companies", lisCompanies);
		if (id != 0) {
			ComputerDTO computer = serviceComputer.findComputerById(id);
			request.setAttribute("Computer", computer);
		}
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editComputer.jsp");
		dispatcher.forward(request, response);
		logger.trace("Redirecting to the EditComputer page.");
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

		ComputerDTO computer = new ComputerDTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ServiceComputer serviceComputer = new ServiceComputer();
		ServiceCompany serviceCompany = new ServiceCompany();
		Long id = (long) Long.valueOf(request.getParameter("id"));
		computer.setId(id);

		String name = (String) request.getParameter("computerName");
		computer.setName(name);

		if (DateValidator.getInstance().isValid(
				request.getParameter("introduced"), "yyyy-MM-dd")) {
			LocalDate introduced = LocalDate.parse(
					request.getParameter("introduced"), formatter);
			computer.setIntroduced(introduced);
		} else
			computer.setIntroduced(null);

		if (DateValidator.getInstance().isValid(
				request.getParameter("discontinued"), "yyyy-MM-dd")) {
			LocalDate discontinued = LocalDate.parse(
					request.getParameter("discontinued"), formatter);
			computer.setDiscontinued(discontinued);
		} else
			computer.setDiscontinued(null);

		Company company = serviceCompany.findCompanyById((long) Long
				.valueOf(request.getParameter("companyId")));
		computer.setCompanyId(company.getId());
		computer.setCompanyName(company.getName());

		// Computer update
		serviceComputer.updateComputer(computer);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		dispatcher.forward(request, response);
		logger.trace("Success editing, redirecting to the Dashboard page.");
	}
}
