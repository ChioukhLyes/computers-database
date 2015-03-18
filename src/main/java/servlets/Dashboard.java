package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Computer;
import persistence.ComputerDAO;
import persistence.DaoFactory;
import service.Service;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Service service = new Service();
		List<Computer> lisComputers = service.findAllComputers();
		request.setAttribute("Computers", lisComputers);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/views/dashboard.jsp");	
		dispatcher.forward(request, response);
		
//		if ("include".equalsIgnoreCase(action)) {
//			rd.include(request, response);
//		} else if ("forward".equalsIgnoreCase(action)) {
//			rd.forward(request, response);
//		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ComputerDAO computerDAO = DaoFactory.INSTANCE.getComputerDAO();
		List<Computer> lisComputers = computerDAO.findAllComputers();
		request.setAttribute("Computers", lisComputers);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/dashboard.jsp");	
		dispatcher.forward(request, response);
		
		
		
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println("TestServlet says hi<br/>");
//
//		RequestDispatcher rd = request
//				.getRequestDispatcher("views/dashboard.html");
//		rd.forward(request, response);
	}

}