package com.excilys.servlets;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.dto.ComputerDTO;
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
	private Page<ComputerDTO> currentPage = new Page<ComputerDTO>();

	/** The number computers. */
	private int numberComputers;

	/**
	 * Instantiates a new dashboard.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardController() {
		super();

		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() {
		// TODO Auto-generated method stub
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
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
	protected String doGet(
			@RequestParam(value = "size", required = true, defaultValue = "10") int size,
			@RequestParam(value = "page", required = true, defaultValue = "1") int page,
			@RequestParam(value = "search", required = false, defaultValue = "") String search,
			@RequestParam(value = "orderby", required = false, defaultValue = "name") String orderby,
			@RequestParam(value = "optionorder", required = false, defaultValue = "ASC") String orderoption,
			ModelMap model) {

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

		List<ComputerDTO> lisComputers = serviceComputer
				.findAllComputersCompaniesByName(size, ((page - 1) * size),
						orderby, search, orderoption);

		numberComputers = serviceComputer.getCountComputers(search);

		currentPage.setMaxPage((numberComputers - 1) / size + 1);
		currentPage.setEntities(lisComputers);

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("numberComputers", numberComputers);

		return "dashboard";
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
	protected String doPost(
			@RequestParam(value = "selection", required = false, defaultValue = "") String selection,
			ModelMap model) {

		StringTokenizer stringTokenizer = new StringTokenizer(selection, ",");
		while (stringTokenizer.hasMoreTokens()) {

			computer.setId(Long.valueOf(stringTokenizer.nextToken()));
			serviceComputer.deleteComputer(computer);
			numberComputers--;
			computer = null;
		}

		currentPage.setEntities(serviceComputer
				.findAllComputersCompaniesByName(currentPage.getPageSize(),
						((currentPage.getPageNumber() - 1) * currentPage
								.getPageSize()), currentPage
								.getOrderEntitiesBy(), currentPage
								.getSearchString(), currentPage
								.getoptionOrder()));

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("numberComputers", numberComputers);

		return "dashboard";
	}
}
