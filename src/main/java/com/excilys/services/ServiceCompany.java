/*
 * 
 */
package com.excilys.services;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Logger;

import com.excilys.model.Company;
import com.excilys.persistence.impl.CompanyDaoImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class Service.
 */
@Service
@Transactional
public class ServiceCompany {

	/** The dao factory. */
	@Autowired
	private CompanyDaoImpl companyDAO;

	/** The service computer. */
	@Autowired
	private ServiceComputer serviceComputer;

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(ServiceCompany.class);

	/**
	 * Gets the count companies.
	 *
	 * @return the count companies
	 */
	@Transactional
	public int getCountCompanies() {
		logger.trace("Founded count companies.");
		return companyDAO.getCountCompanies();
	}

	/**
	 * Find all companies.
	 *
	 * @return the list
	 */
	@Transactional
	public List<Company> findAllCompanies() {
		logger.trace("Founded all companies.");
		return companyDAO.findAllCompanies();
	}

	/**
	 * Find all companies.
	 *
	 * @param limit
	 *            the limit
	 * @param offset
	 *            the offset
	 * @return the list
	 */
	@Transactional
	public List<Company> findAllCompanies(int limit, int offset) {
		logger.trace("Founded all companies by limit and offset.");
		return companyDAO.findAllCompanies(limit, offset);
	}

	/**
	 * Delete company.
	 *
	 * @param company
	 *            the company
	 */
	@Transactional
	public void deleteCompany(Company company) {
		if (company == null) {
			throw new IllegalArgumentException(
					"[Delete] - company bean is null");
		}
		serviceComputer.deleteComputerByCompanyId(company);
		companyDAO.deleteCompany(company);
		logger.trace("Companie deletion.");
	}

	/**
	 * Find company by id.
	 *
	 * @param id
	 *            the id
	 * @return the company
	 */
	@Transactional
	public Company findCompanyById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("[Found] -Id company is null");
		}
		logger.trace("Founded company by id");
		return companyDAO.findCompanyById(id);
	}

}
