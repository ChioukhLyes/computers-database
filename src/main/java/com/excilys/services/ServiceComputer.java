/*
 * 
 */
package com.excilys.services;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Logger;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.persistence.ComputerDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class Service.
 */
@Component
public class ServiceComputer {

	/** The computer dao. */
	@Autowired
	ComputerDAO computerDAO;

	/** The i. */
	public int i = 0;
	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(ServiceComputer.class);

	/**
	 * Gets the count computers.
	 *
	 * @param search
	 *            the search
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
	 * @param limit
	 *            the limit
	 * @param offset
	 *            the offset
	 * @return the list
	 */
	public List<ComputerDTO> findAllComputers(int limit, int offset) {
		return computerDAO.findAllComputers(limit, offset);
	}

	/**
	 * Find all computers companies by name.
	 *
	 * @param limit
	 *            the limit
	 * @param offset
	 *            the offset
	 * @param orderBy
	 *            the order by
	 * @param search
	 *            the search
	 * @param orderOption
	 *            the order option
	 * @return the list
	 */
	public List<ComputerDTO> findAllComputersCompaniesByName(int limit,
			int offset, String orderBy, String search, String orderOption) {
		System.out.println(computerDAO.findAllComputersCompaniesByName(limit,
				offset, orderBy, search, orderOption).toString());
		return computerDAO.findAllComputersCompaniesByName(limit, offset,
				orderBy, search, orderOption);
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
	@Transactional
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
	@Transactional
	public boolean deleteComputer(ComputerDTO computer) {
		if (computer == null) {
			throw new IllegalArgumentException("Delete : Computer bean is null");
		}
		return computerDAO.deleteComputer(computer);
	}

	/**
	 * Delete computer by company id.
	 *
	 * @param company
	 *            the company
	 * @return true, if successful
	 */
	@Transactional
	public boolean deleteComputerByCompanyId(Company company) {
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
	@Transactional
	public boolean updateComputer(ComputerDTO computer) {
		if (computer == null) {
			throw new IllegalArgumentException("Update : Computer bean is null");
		}
		return computerDAO.updateComputer(computer);
	}
}
