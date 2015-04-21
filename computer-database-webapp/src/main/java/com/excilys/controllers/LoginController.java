package com.excilys.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginController.
 */
@Controller
public class LoginController {

	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(Class.class);
	/**
	 * Welcome page.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		logger.info("[Get] from base url");
		return model;

	}

	/**
	 * Admin page.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/dashboard**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("dashboard");
		logger.info("[Get] from base /dashnoard** url");
		return model;

	}

	/**
	 * Login.
	 *
	 * @param error
	 *            the error
	 * @param logout
	 *            the logout
	 * @return the model and view
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "errorr", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("errorr", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");
		logger.info("[Get] from /login url - Error :"+error+" Logout :"+logout);
		return model;
	}
}
