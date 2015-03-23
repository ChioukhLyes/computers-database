package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Computer;
import persistence.ComputerDAO;
import persistence.DaoFactory;
import service.Service;

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
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

//		HttpSession session = request.getSession();
		Service service = new Service();
		
		int page = 1;
		if(request.getParameter("page")!=null) page = Integer.valueOf(request.getParameter("page"));
		int size = 10;
		if(request.getParameter("size") != null) size = Integer.valueOf(request.getParameter("size"));
		
//		
//		
//		 session.setAttribute("size", size);
//		 Long numberOfComputer = ComputerService.INSTANCE.getNumberOfElement();
//		 session.setAttribute("numberOfComputer", numberOfComputer);
//		 List<Computer> computers = ComputerService.INSTANCE.list(new Long((page - 1 )* size), new Long(page * size));
//		 List<ComputerDto> computerDtos = DtoMapper.INSTANCE.mapComputers(computers);
//		 session.setAttribute("computers", computerDtos);
//		 session.setAttribute("pageMax", ( numberOfComputer - 1) / size + 1);
//		 request.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(
//		 request, response);
		 
		System.out.println("page"+page);
		System.out.println("size"+size);
		
		request.setAttribute("page",page);
		request.setAttribute("size",size);
		
		int numberComputers = service.getCountComputers();
		request.setAttribute("numberComputers",numberComputers);
		List<Computer> lisComputers = service.findAllComputers(size, ((page -1) * size));
		
		request.setAttribute("Computers", lisComputers);
		request.setAttribute("pageMax", ( numberComputers - 1) / size + 1);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ComputerDAO computerDAO = DaoFactory.INSTANCE.getComputerDAO();
		List<Computer> lisComputers = computerDAO.findAllComputers();
		request.setAttribute("Computers", lisComputers);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		dispatcher.forward(request, response);

	}
}
