package service;

import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.Company;
import model.Computer;
import persistence.CompanyDAO;
import persistence.ComputerDAO;
import persistence.DaoFactory;
import servlets.EditComputer;

// TODO: Auto-generated Javadoc
/**
 * The Class Service.
 */
public class Service {

	/** The company dao. */
	CompanyDAO companyDAO = DaoFactory.INSTANCE.getCompanyDAO();

	/** The computer dao. */
	ComputerDAO computerDAO = DaoFactory.INSTANCE.getComputerDAO();

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(EditComputer.class);
	
	/**
	 * Gets the count computers.
	 *
	 * @return the count computers
	 */
	public int getCountComputers() {
		return computerDAO.getCountComputers();
	}
	
	/**
	 * Gets the count companies.
	 *
	 * @return the count companies
	 */
	public int getCountCompanies() {
		return companyDAO.getCountCompanies();
	}
	
	
	
	/**
	 * Find all companies.
	 *
	 * @return the list
	 */
	public List<Company> findAllCompanies() {
		return companyDAO.findAllCompanies();
	}
	
	/**
	 * Find all companies.
	 *
	 * @param limit the limit
	 * @param offset the offset
	 * @return the list
	 */
	public List<Company> findAllCompanies(int limit, int offset) {
		return companyDAO.findAllCompanies(limit, offset);
	}

	/**
	 * Find company by id.
	 *
	 * @param id
	 *            the id
	 * @return the company
	 */
	public Company findCompanyById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Id company is null");
		}
		logger.info("Founded company by id");
		return companyDAO.findCompanyById(id);
	}

	/**
	 * Find all computers.
	 *
	 * @return the list
	 */
	public List<Computer> findAllComputers() {
		return computerDAO.findAllComputers();
	}
	
	
	/**
	 * Find all computers.
	 *
	 * @param fromIndex the from index
	 * @param toIndex the to index
	 * @return the list
	 */
	public List<Computer> findAllComputers(int limit, int offset){
		return computerDAO.findAllComputers(limit, offset);	
	}

	/**
	 * Find computer by id.
	 *
	 * @param id
	 *            the id
	 * @return the computer
	 */
	public Computer findComputerById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Id computer is null");
		}
		logger.info("Founded computer by id");
		return computerDAO.findComputerById(id);
	}

	/**
	 * Insert computer.
	 *
	 * @param computer
	 *            the computer
	 * @return true, if successful
	 */
	public boolean insertComputer(Computer computer) {
		if (computer == null) {
			throw new IllegalArgumentException("Insert : Computer bean is null");
		}		
		return computerDAO.insertComputer(computer);
	}

	/**
	 * Delete computer.
	 *
	 * @param computer
	 *            the computer
	 * @return true, if successful
	 */
	public boolean deleteComputer(Computer computer) {
		if (computer == null) {
			throw new IllegalArgumentException("Delete : Computer bean is null");
		}
		return computerDAO.deleteComputer(computer);
	}

	/**
	 * Update computer.
	 *
	 * @param computer
	 *            the computer
	 * @return true, if successful
	 */
	public boolean updateComputer(Computer computer) {
		if (computer == null) {
			throw new IllegalArgumentException("Update : Computer bean is null");
		}
		return computerDAO.updateComputer(computer);
	}
}
