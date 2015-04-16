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
	 * Instantiates a new edits the controller.
	 */
	public EditController() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param id
	 *            the id
	 * @param modelAndView
	 *            the model and view
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(
			@Validated @RequestParam(value = "id", required = true, defaultValue = "0") Long id,
			ModelAndView modelAndView) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		List<Company> lisCompanies = serviceCompany.findAllCompanies();
		modelAndView.addObject("Companies", lisCompanies);
		if (id != 0) {
			computer = serviceComputer.findComputerById(id);
			if (computer.getCompanyId() != null) {
				company = serviceCompany.findCompanyById(computer
						.getCompanyId());
			}
			modelAndView.addObject("Computer", computer);
			modelAndView.addObject("Company", company);
		}

		modelAndView.setViewName("editComputer");

		logger.trace("[DoGet] - Redirecting to the EditComputer page.");
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
			@RequestParam(value = "companyId", required = false, defaultValue = "0") Long companyId,
			ModelAndView modelAndView) {

		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		computer.setId(id);
		computer.setName(computerName);

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
		
		company = serviceCompany.findCompanyById(companyId);
		computer.setCompanyId(company.getId());
		computer.setCompanyName(company.getName());

		serviceComputer.updateComputer(computer);
		modelAndView.setViewName("success");

		logger.trace("[DoPost] - Success editing, redirecting to the success page.");
		return modelAndView;
	}
}
