package com.excilys.services;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.persistence.ComputerDAO;
import com.excilys.persistence.impl.DaoFactory;

import ch.qos.logback.classic.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class Service.
 */
public class ServiceComputer {

	DaoFactory daoFactory = DaoFactory.getInstance();

	/** The computer dao. */
	ComputerDAO computerDAO = daoFactory.getComputerDAO();

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(ServiceComputer.class);
	
	/**
	 * Gets the count computers.
	 *
	 * @return the count computers
	 */
	public int getCountComputers(String search) {
		return computerDAO.getCountComputers(search);
	}

	/**
	 * Find all computers.
	 *
	 * @return the list
	 */
	public List<ComputerDTO> findAllComputers() {
		return computerDAO.findAllComputers();
	}
	
	
	/**
	 * Find all computers.
	 *
	 * @param fromIndex the from index
	 * @param toIndex the to index
	 * @return the list
	 */
	public List<ComputerDTO> findAllComputers(int limit, int offset){
		return computerDAO.findAllComputers(limit, offset);	
	}

	
	public List<ComputerDTO> findAllComputersCompaniesByName(int limit, int offset, String orderBy, String search, String orderOption){
		return computerDAO.findAllComputersCompaniesByName(limit, offset, orderBy, search, orderOption);	
	}
	
	
	
	/**
	 * Find computer by id.
	 *
	 * @param id
	 *            the id
	 * @return the computer
	 */
	public ComputerDTO findComputerById(Long id) {
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
	public boolean insertComputer(ComputerDTO computer) {
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
	public boolean deleteComputer(ComputerDTO computer) {
		if (computer == null) {
			throw new IllegalArgumentException("Delete : Computer bean is null");
		}
		return computerDAO.deleteComputer(computer);
	}

	
	/**
	 * Delete computer by company id.
	 *
	 * @param company the company
	 * @return true, if successful
	 */
	public boolean deleteComputerByCompanyId(Company company){
		if (company == null) {
			throw new IllegalArgumentException("Delete : Company bean is null");
		}
		return computerDAO.deleteComputerByCompanyId(company);
	}
	/**
	 * Update computer.
	 *
	 * @param computer
	 *            the computer
	 * @return true, if successful
	 */
	public boolean updateComputer(ComputerDTO computer) {
		if (computer == null) {
			throw new IllegalArgumentException("Update : Computer bean is null");
		}
		return computerDAO.updateComputer(computer);
	}
}
