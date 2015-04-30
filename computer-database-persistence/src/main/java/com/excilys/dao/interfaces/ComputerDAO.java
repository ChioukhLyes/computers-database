package com.excilys.dao.interfaces;

import java.util.List;

import org.springframework.stereotype.Component;

import com.excilys.dto.ComputerDTO;
import com.excilys.models.Company;
import com.excilys.models.Computer;

// TODO: Auto-generated Javadoc
/**
 * The Interface ComputerDAO.
 */
@Component
public interface ComputerDAO  {

	/**
	 * Find all computers.
	 *
	 * @return the list
	 */
	List<ComputerDTO> findAllComputers();

	/**
	 * Find all computers.
	 *
	 * @param fromIndex
	 *            the from index
	 * @param toIndex
	 *            the to index
	 * @return the list
	 */
	List<ComputerDTO> findAllComputers(int fromIndex, int toIndex);

	/**
	 * Find all computers companies by name.
	 *
	 * @param fromIndex
	 *            the from index
	 * @param toIndex
	 *            the to index
	 * @param orderBy
	 *            the order by
	 * @param search
	 *            the search
	 * @param orderOtion
	 *            the order otion
	 * @return the list
	 */
	List<Computer> findAllComputersCompaniesByName(int fromIndex,
			int toIndex, String orderBy, String search, String orderOtion);

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
	void insertComputer(Computer computer);

	/**
	 * Delete computer.
	 *
	 * @param computer
	 *            the computer
	 * @return true, if successful
	 */
	void deleteComputer(ComputerDTO computer);

	/**
	 * Delete computer by company id.
	 *
	 * @param company
	 *            the company
	 * @return true, if successful
	 */
	void deleteComputerByCompanyId(Company company);

	/**
	 * Update computer.
	 *
	 * @param computer
	 *            the computer
	 * @return true, if successful
	 */
	void updateComputer(ComputerDTO computer);

	/**
	 * Gets the count computers.
	 *
	 * @param search
	 *            the search
	 * @return the count computers
	 */
	long getCountComputers(String search);
}
