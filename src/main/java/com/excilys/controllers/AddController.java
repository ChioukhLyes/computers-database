/*
 * 
 */
package com.excilys.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.validator.routines.DateValidator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.ModelAndView;

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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		computer.setName(computerName);

		if (DateValidator.getInstance().isValid(introduced, "yyyy-MM-dd")) {
			LocalDate introducedT = LocalDate.parse(introduced, formatter);
			computer.setIntroduced(introducedT);
		}

		if (DateValidator.getInstance().isValid(discontinued, "yyyy-MM-dd")) {
			LocalDate discontinuedT = LocalDate.parse(discontinued, formatter);
			computer.setDiscontinued(discontinuedT);
		}

		if (companyId != 0) {
			Company company = serviceCompany.findCompanyById(companyId);
			computer.setCompanyId(companyId);
			computer.setCompanyName(company.getName());
		}
		
		serviceComputer.insertComputer(computer);
		modelAndView.setViewName("success");
		
		logger.trace("[doPost Add] - Computer created with success, redirecting to the success page.");
		return modelAndView;
	}

}
