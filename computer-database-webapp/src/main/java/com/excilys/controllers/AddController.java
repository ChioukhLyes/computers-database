/*
 * 
 */
package com.excilys.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.validator.routines.DateValidator;

import ch.qos.logback.classic.Logger;

import com.excilys.dto.ComputerDTO;
import com.excilys.models.Company;
import com.excilys.service.ServiceCompany;
import com.excilys.service.ServiceComputer;
//import com.excilys.validator.DateValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class AddComputer.
 */
@Controller
@RequestMapping("/addComputer")
public class AddController {

	/** The service company. */
	@Autowired
	ServiceCompany serviceCompany;

	/** The computer. */
	@Autowired
	ComputerDTO computer;

	/** The service computer. */
	@Autowired
	ServiceComputer serviceComputer;

	/**
	 * Instantiates a new adds the computer.
	 */
	public AddController() {
		super();
	}

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(AddController.class);

	/**
	 * Do get.
	 *
	 * @param modelAndView
	 *            the model and view
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET)
	@Validated
	protected ModelAndView doGet(ModelAndView modelAndView) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		List<Company> lisCompanies = serviceCompany.findAllCompanies();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
	    		  
		modelAndView.addObject("username",username);
		modelAndView.addObject("Companies", lisCompanies);
		modelAndView.setViewName("addComputer");

		logger.trace("[doGet Add] - Redirecting to the AddComputer page.");
		return modelAndView;
	}

	/**
	 * Do post.
	 *
	 * @param id
	 *            the id
	 * @param computerName
	 *            the computer name
	 * @param introduced
	 *            the introduced
	 * @param discontinued
	 *            the discontinued
	 * @param companyId
	 *            the company id
	 * @param modelAndView
	 *            the model and view
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doPost(
			@Validated @RequestParam(value = "id", required = true, defaultValue = "0") Long id,
			@Validated @RequestParam(value = "computerName", required = true, defaultValue = "") String computerName,
			@Validated @RequestParam(value = "introduced", required = false, defaultValue = "") String introduced,
			@Validated @RequestParam(value = "discontinued", required = false, defaultValue = "") String discontinued,
			@RequestParam(value = "companyId", required = false, defaultValue = "") Long companyId,
			ModelAndView modelAndView) {

		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		DateTimeFormatter formatterUs = DateTimeFormatter
				.ofPattern("MM-dd-yyyy");
		DateTimeFormatter formatterFr = DateTimeFormatter
				.ofPattern("dd-MM-yyyy");
		computer.setName(computerName);
		
		LocalDate introducedT = null;
		if (DateValidator.getInstance().isValid(introduced, "MM-dd-yyyy"))
			introducedT = LocalDate.parse(introduced, formatterUs);
		else if (DateValidator.getInstance().isValid(introduced, "dd-MM-yyyy"))
			introducedT = LocalDate.parse(introduced, formatterFr);
		computer.setIntroduced(introducedT);

		
		LocalDate discontinuedT = null;
		if (DateValidator.getInstance().isValid(discontinued, "MM-dd-yyyy"))
			discontinuedT = LocalDate.parse(discontinued, formatterUs);
		else if (DateValidator.getInstance().isValid(discontinued, "dd-MM-yyyy"))
			discontinuedT = LocalDate.parse(discontinued, formatterFr);
		computer.setDiscontinued(discontinuedT);
		
		if (companyId != 0) {
			Company company = serviceCompany.findCompanyById(companyId);
			computer.setCompanyId(companyId);
			computer.setCompanyName(company.getName());
		}
		else computer.setCompanyId(companyId);

		serviceComputer.insertComputer(computer);
		modelAndView.setViewName("success");

		logger.trace("[doPost Add] - Computer created with success, redirecting to the success page.");
		return modelAndView;
	}
}
