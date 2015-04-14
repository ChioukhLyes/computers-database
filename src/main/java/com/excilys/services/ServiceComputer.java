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
import com.excilys.persistence.impl.ComputerDaoImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class Service.
 */
@Component
@Transactional
public class ServiceComputer {

	/** The computerDTO dao. */
	@Autowired
	private ComputerDaoImpl computerDAO;

	@Autowired
	private ComputerDTO computerDTO;
	
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
	@Transactional
	public long getCountComputers(String search) {
		return computerDAO.getCountComputers(search);
	}

	/**
	 * Find all computers.
	 *
	 * @return the list
	 */
	@Transactional
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
	@Transactional
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
	@Transactional
	public List<ComputerDTO> findAllComputersCompaniesByName(int limit,
			int offset, String orderBy, String search, String orderOption) {
		return computerDAO.findAllComputersCompaniesByName(limit, offset,
				orderBy, search, orderOption);
	}

	/**
	 * Find computerDTO by id.
	 *
	 * @param id
	 *            the id
	 * @return the computerDTO
	 */
	@Transactional
	public ComputerDTO findComputerById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Id computerDTO is null");
		}
		logger.info("Founded computerDTO by id");
		return computerDAO.findComputerById(id);
	}

	/**
	 * Insert computerDTO.
	 *
	 * @param computerDTO
	 *            the computerDTO
	 * @return true, if successful
	 */
	@Transactional
	public void insertComputer(ComputerDTO computerDTO) {
		if (computerDTO == null) {
			throw new IllegalArgumentException("Insert : ComputerDTO bean is null");
		}
		computerDAO.insertComputer(computerDTO);
	}

	/**
	 * Delete computerDTO.
	 *
	 * @param computerDTO
	 *            the computerDTO
	 * @return true, if successful
	 */
	@Transactional
	public void deleteComputer(ComputerDTO computerDTO) {
		if (computerDTO == null) {
			throw new IllegalArgumentException("Delete : ComputerDTO bean is null");
		}
		computerDAO.deleteComputer(computerDTO);
	}

	/**
	 * Delete computerDTO by company id.
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
		computerDAO.deleteComputerByCompanyId(company);
		return true;
	}

	/**
	 * Update computerDTO.
	 *
	 * @param computerDTO
	 *            the computerDTO
	 * @return true, if successful
	 */
	@Transactional
	public void updateComputer(ComputerDTO computerDTO) {
		if (computerDTO == null) {
			throw new IllegalArgumentException("Update : ComputerDTO bean is null");
		}
		computerDAO.updateComputer(computerDTO);
	}
}
