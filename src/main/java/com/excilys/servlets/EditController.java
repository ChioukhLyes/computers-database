package com.excilys.servlets;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.validator.routines.DateValidator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.classic.Logger;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.services.ServiceCompany;
import com.excilys.services.ServiceComputer;

// TODO: Auto-generated Javadoc
/**
 * The Class EditComputer.
 */
@Controller
@RequestMapping("/editComputer")
public class EditController {

	/** The service company. */
	@Autowired
	private ServiceCompany serviceCompany;

	/** The computer. */
	@Autowired
	private ComputerDTO computer;

	/** The service computer. */
	@Autowired
	private ServiceComputer serviceComputer;

	/** The company. */
	@Autowired
	private Company company;
	

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(EditController.class);

	/**
	 * Instantiates a new edits the computer.
	 */
	public EditController() {
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
	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(@Validated @RequestParam(value = "id", required = true, defaultValue="") Long id,
			ModelMap model)  {
		// TODO Auto-generated method stub

		List<Company> lisCompanies = serviceCompany.findAllCompanies();
		model.addAttribute("Companies", lisCompanies);
		if (id != 0) {
			computer = serviceComputer.findComputerById(id);
			model.addAttribute("Computer", computer);
		}
		logger.trace("Redirecting to the EditComputer page.");
		return "editComputer";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(method = RequestMethod.POST)
	
	protected String doPost(
			@Validated @RequestParam(value = "id", required = true, defaultValue="0") Long id,
			@Validated @RequestParam(value = "computerName", required = true, defaultValue="") String computerName,
			@Validated @RequestParam(value = "introduced", required = false, defaultValue="") String introduced,
			@Validated @RequestParam(value = "discontinued", required = false, defaultValue="") String discontinued,
			@RequestParam(value = "companyId", required = false, defaultValue="") Long companyId,			
			ModelMap model) {

		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		computer.setId(id);
		computer.setName(computerName);

		if (DateValidator.getInstance().isValid(introduced, "yyyy-MM-dd")) {
			LocalDate introducedT = LocalDate.parse(introduced, formatter);
			computer.setIntroduced(introducedT);
		} else
			computer.setIntroduced(null);

		if (DateValidator.getInstance().isValid(discontinued, "yyyy-MM-dd")) {
			LocalDate discontinuedT = LocalDate.parse(discontinued, formatter);
			computer.setDiscontinued(discontinuedT);
		} else
			computer.setDiscontinued(null);

		company = serviceCompany.findCompanyById(companyId);
		computer.setCompanyId(company.getId());
		computer.setCompanyName(company.getName());
		
		// Computer update
		serviceComputer.updateComputer(computer);
		logger.trace("Success editing, redirecting to the success page.");
		return "success";
	}
}
