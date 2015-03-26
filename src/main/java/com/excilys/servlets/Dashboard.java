package com.excilys.servlets;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Page;
import com.excilys.services.ServiceComputer;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class Dashboard.
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	/** The service computer. */
	public ServiceComputer serviceComputer = new ServiceComputer();
	
	/** The current page. */
	public Page<ComputerDTO> currentPage = new Page<ComputerDTO>();
	
	/** The number computers. */
	public int  numberComputers;
	/**
	 * Instantiates a new dashboard.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public Dashboard() {
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
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Default values
		int size = 10;
		int page = 1;
		String search = "";
		String orderBy = "name";
		String optionOrder="ASC";
		
		List<ComputerDTO> lisComputers;
		
		if (request.getParameter("page") != null)
			page = Integer.valueOf(request.getParameter("page"));
		if (request.getParameter("size") != null)
			size = Integer.valueOf(request.getParameter("size"));
		
		if (request.getParameter("search") != null) {
			search = request.getParameter("search");
		}
		if (request.getParameter("orderby") != null) {
			orderBy = request.getParameter("orderby");
		}
		if (request.getParameter("orderoption") != null) {
			optionOrder = request.getParameter("orderoption");
		}
		
		lisComputers = serviceComputer.findAllComputersCompaniesByName(size,
				((page - 1) * size), orderBy, search, optionOrder);
		
		numberComputers = serviceComputer.getCountComputers(search);
		
		currentPage.setEntities(lisComputers);
		currentPage.setMaxPage((numberComputers - 1) / size + 1);
		currentPage.setPageNumber(page);
		currentPage.setPageSize(size);
		currentPage.setSearchString(search);
		currentPage.setOrderEntitiesBy(orderBy);
		currentPage.setoptionOrder(optionOrder);

		request.setAttribute("currentPage", currentPage);
		request.setAttribute("numberComputers", numberComputers);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		dispatcher.forward(request, response);
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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String computersIds = request.getParameter("selection");
		StringTokenizer stringTokenizer = new StringTokenizer(computersIds, ",");
		ServiceComputer serviceComputer = new ServiceComputer();

		while (stringTokenizer.hasMoreTokens()) {
					
			ComputerDTO computer = new ComputerDTO();
			computer.setId(Long.valueOf(stringTokenizer.nextToken()));
			serviceComputer.deleteComputer(computer);
			numberComputers--;	
			computer = null;
		}
		
		currentPage.setEntities(serviceComputer.findAllComputersCompaniesByName(currentPage.getPageSize(),
				((currentPage.getPageNumber() - 1) * currentPage.getPageSize()), currentPage.getOrderEntitiesBy(), 
				currentPage.getSearchString(), currentPage.getoptionOrder()));
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("numberComputers", numberComputers);
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		dispatcher.forward(request, response);
	}
}
