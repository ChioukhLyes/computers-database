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
import service.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class AddComputer.
 */
@WebServlet("/addComputer")
public class AddComputer extends HttpServlet{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Instantiates a new adds the computer.
	 */
	public 	AddComputer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Service service = new Service();
		List<Company> lisCompanies = service.findAllCompanies();
		request.setAttribute("Companies", lisCompanies);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/addComputer.jsp");
		dispatcher.forward(request, response);

		// if ("include".equalsIgnoreCase(action)) {
		// rd.include(request, response);
		// } else if ("forward".equalsIgnoreCase(action)) {
		// rd.forward(request, response);
		// }

	}

	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
