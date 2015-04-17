/*
 * 
 */
package com.excilys.dao.interfaces;

import java.util.List;

import org.springframework.stereotype.Component;

import com.excilys.models.Company;

// TODO: Auto-generated Javadoc
/**
 * The Interface CompanyDAO.
 */
@Component
public interface CompanyDAO {

	/**
	 * Find all companies.
	 *
	 * @return the list
	 */
	List<Company> findAllCompanies();

	/**
	 * Find all companies.
	 *
	 * @param fromIndex
	 *            the from index
	 * @param toIndex
	 *            the to index
	 * @return the list
	 */
	List<Company> findAllCompanies(int fromIndex, int toIndex);

	/**
	 * Find company by id.
	 *
	 * @param id
	 *            the id
	 * @return the company
	 */
	Company findCompanyById(Long id);

	/**
	 * Delete company.
	 *
	 * @param company
	 *            the company
	 * @return true, if successful
	 */
	void deleteCompany(Company company);

	/**
	 * Gets the count companies.
	 *
	 * @return the count companies
	 */
	int getCountCompanies();

}
