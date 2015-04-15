/*
 * 
 */
package com.excilys.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import ch.qos.logback.classic.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class EditComputer.
 */
@Controller
@RequestMapping("/success")
public class SuccessController {

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(SuccessController.class);

	/**
	 * Do get.
	 *
	 * @param message
	 *            the message
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(
			@RequestParam(value = "message", required = false, defaultValue = "") String message,
			ModelMap model) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		model.addAttribute("message", message);

		logger.trace("[DoGet] - Redirecting to the success page.");
		return "success";
	}

}
