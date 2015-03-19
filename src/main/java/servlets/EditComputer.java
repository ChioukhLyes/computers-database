package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import model.Computer;
import service.Service;

@WebServlet("/editComputer")
public class EditComputer extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EditComputer(){
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Long id = (long) Long.valueOf(request.getParameter("id"));
		Service service = new Service();
		List<Company> lisCompanies = service.findAllCompanies();
		request.setAttribute("Companies", lisCompanies);
		if (id != 0) {
			Computer computer  = service.findComputerById(id);
			request.setAttribute("Computer", computer );
			request.setAttribute("companyName", service.findCompanyById(computer.getCompanyId()).getName());
		}
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editComputer.jsp");
		dispatcher.forward(request, response);

		// if ("include".equalsIgnoreCase(action)) {
		// rd.include(request, response);
		// } else if ("forward".equalsIgnoreCase(action)) {
		// rd.forward(request, response);
		// }

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		dispatcher.forward(request, response);
	}
	
}
