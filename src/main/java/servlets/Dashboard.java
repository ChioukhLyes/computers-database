package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Page;
import persistence.ComputerDAO;
import persistence.DaoFactory;
import services.ServiceComputer;
import dto.ComputerDTO;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class Dashboard.
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

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

		ServiceComputer serviceComputer = new ServiceComputer();
		Page<ComputerDTO>  currentPage = new Page<ComputerDTO>();
		
		// Default values
		int size = 10;
		int page = 1;

		if (request.getParameter("page") != null)
			page = Integer.valueOf(request.getParameter("page"));
		if (request.getParameter("size") != null)
			size = Integer.valueOf(request.getParameter("size"));
		int numberComputers = serviceComputer.getCountComputers();
		List<ComputerDTO> lisComputers = serviceComputer.findAllComputers(size,
				((page - 1) * size));

		currentPage.setEntities(lisComputers);
		currentPage.setMaxPage((numberComputers - 1) / size + 1);
		currentPage.setPageNumber(page);
		currentPage.setPageSize(size);
		currentPage.setOrderEntitiesBy(null);
		
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
		// TODO Auto-generated method stub

		ComputerDAO computerDAO = DaoFactory.INSTANCE.getComputerDAO();
		List<ComputerDTO> lisComputers = computerDAO.findAllComputers();
		request.setAttribute("Computers", lisComputers);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		dispatcher.forward(request, response);

	}
}
