/*
 * 
 */
package com.excilys.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// TODO: Auto-generated Javadoc
/**
 * The Class EditComputer.
 */
@Controller
@RequestMapping("/success")
public class SuccessController {


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	
	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(@RequestParam(value = "message", required = false, defaultValue="") String message,
			ModelMap model){
		model.addAttribute("message", message);
		return "success";
	}

}
