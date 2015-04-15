package com.excilys.servlets;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Computer;
import com.excilys.model.Page;
import com.excilys.services.ServiceComputer;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class Dashboard.
 */

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	
	
	/** The service computer. */
	@Autowired
	/** The service computer. */
	private ServiceComputer serviceComputer;

	/** The computer. */
	@Autowired
	private ComputerDTO computer;

	/** The current page. */
	private Page<Computer> currentPage = new Page<Computer>();

	/** The number computers. */
	private long numberComputers;

	
	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(DashboardController.class);
	
	/**
	 * Instantiates a new dashboard.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardController() {
		super();

		// TODO Auto-generated constructor stub
	}

	

	/**
	 * Do get.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(
			@RequestParam(value = "size", required = true, defaultValue = "10") int size,
			@RequestParam(value = "page", required = true, defaultValue = "1") int page,
			@RequestParam(value = "search", required = false, defaultValue = "") String search,
			@RequestParam(value = "orderby", required = false, defaultValue = "name") String orderby,
			@RequestParam(value = "orderoption", required = false, defaultValue = "ASC") String orderoption,
			ModelAndView modelAndView) {

		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		if (page != 0)
			currentPage.setPageNumber(page);
		if (size != 0)
			currentPage.setPageSize(size);
		if (search != null)
			currentPage.setSearchString(search);
		if (orderby != null)
			currentPage.setOrderEntitiesBy(orderby);
		if (orderoption != null)
			currentPage.setoptionOrder(orderoption);

		List<Computer> lisComputers = this.serviceComputer
				.findAllComputersCompaniesByName(size, ((page - 1) * size),
						orderby, search, orderoption);
		
		
		numberComputers = this.serviceComputer.getCountComputers(search);

		currentPage.setMaxPage((long) ((numberComputers - 1) / size + 1));
		currentPage.setEntities(lisComputers);

		modelAndView.addObject("currentPage", currentPage);
		modelAndView.addObject("numberComputers", numberComputers);
		logger.info("Get request access");
		modelAndView.setViewName("dashboard");
		return modelAndView;
	}

	/**
	 * Do post.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doPost(
			@RequestParam(value = "selection", required = false, defaultValue = "") String selection,
			ModelAndView modelAndView) {
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		StringTokenizer stringTokenizer = new StringTokenizer(selection, ",");
		while (stringTokenizer.hasMoreTokens()) {
			computer.setId(Long.valueOf(stringTokenizer.nextToken()));
			this.serviceComputer.deleteComputer(computer);
			numberComputers--;
		}

		currentPage.setEntities(this.serviceComputer
				.findAllComputersCompaniesByName(currentPage.getPageSize(),
						((currentPage.getPageNumber() - 1) * currentPage
								.getPageSize()), currentPage
								.getOrderEntitiesBy(), currentPage
								.getSearchString(), currentPage
								.getoptionOrder()));

		modelAndView.addObject("currentPage", currentPage);
		modelAndView.addObject("numberComputers", numberComputers);
		logger.info("Post request access");
		modelAndView.setViewName("dashboard");
		return modelAndView;
	}
}
