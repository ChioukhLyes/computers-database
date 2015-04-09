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

import com.excilys.model.Company;
import com.excilys.persistence.CompanyDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class Service.
 */
@Component
public class ServiceCompany {

	/** The dao factory. */
	@Autowired
	CompanyDAO companyDAO;

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(ServiceCompany.class);

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
	 * @param limit
	 *            the limit
	 * @param offset
	 *            the offset
	 * @return the list
	 */
	public List<Company> findAllCompanies(int limit, int offset) {
		return companyDAO.findAllCompanies(limit, offset);
	}

	/**
	 * Delete company.
	 *
	 * @param company
	 *            the company
	 * @return true, if successful
	 */
	@Transactional
	public boolean deleteCompany(Company company) {
		if (company == null) {
			throw new IllegalArgumentException("Delete : company bean is null");
		}
		ServiceComputer serviceComputer = new ServiceComputer();
		serviceComputer.deleteComputerByCompanyId(company);
		return companyDAO.deleteCompany(company);
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
		logger.trace("Founded company by id");
		return companyDAO.findCompanyById(id);
	}

}
