package com.excilys.persistence;

import java.util.List;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;

// TODO: Auto-generated Javadoc
/**
 * The Interface ComputerDAO.
 */
public interface ComputerDAO {

	/**
	 * Find all computers.
	 *
	 * @return the list
	 */
	List<ComputerDTO> findAllComputers();
	
	/**
	 * Find all computers.
	 *
	 * @param fromIndex the from index
	 * @param toIndex the to index
	 * @return the list
	 */
	List<ComputerDTO> findAllComputers(int fromIndex, int toIndex);
	
	
	/**
	 * Find all computers companies by name.
	 *
	 * @param fromIndex the from index
	 * @param toIndex the to index
	 * @param search the search
	 * @return the list
	 */
	List<ComputerDTO> findAllComputersCompaniesByName(int fromIndex, int toIndex, String search);
	
	/**
	 * Find computer by id.
	 *
	 * @param id
	 *            the id
	 * @return the computer
	 */
	ComputerDTO findComputerById(Long id);

	/**
	 * Insert computer.
	 *
	 * @param computer
	 *            the computer
	 * @return true, if successful
	 */
	boolean insertComputer(ComputerDTO computer);

	/**
	 * Delete computer.
	 *
	 * @param computer
	 *            the computer
	 * @return true, if successful
	 */
	boolean deleteComputer(ComputerDTO computer);

	
	
	
	/**
	 * Delete computer by company id.
	 *
	 * @param company the company
	 * @return true, if successful
	 */
	boolean deleteComputerByCompanyId(Company company);
	
	
	/**
	 * Update computer.
	 *
	 * @param computer
	 *            the computer
	 * @return true, if successful
	 */
	boolean updateComputer(ComputerDTO computer);
	
	/**
	 * Gets the count computers.
	 *
	 * @return the count computers
	 */
	int getCountComputers();
}
