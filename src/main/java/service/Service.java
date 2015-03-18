package service;

import java.util.List;

import model.Company;
import model.Computer;
import persistence.CompanyDAO;
import persistence.ComputerDAO;
import persistence.DaoFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class Service.
 */
public class Service {

	/** The company dao. */
	CompanyDAO companyDAO = DaoFactory.INSTANCE.getCompanyDAO();

	/** The computer dao. */
	ComputerDAO computerDAO = DaoFactory.INSTANCE.getComputerDAO();

	/**
	 * Find all companies.
	 *
	 * @return the list
	 */
	public List<Company> findAllCompanies() {
		return companyDAO.findAllCompanies();
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
