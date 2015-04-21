package com.excilys.webservice;

import java.util.List;

import javax.ws.rs.core.Response;

import com.excilys.models.Company;

// TODO: Auto-generated Javadoc
/**
 * The Interface CompanyWebService.
 */
public interface CompanyWebService {

	/**
	 * Find all companies.
	 *
	 * @return the list
	 */
	public List<Company> findAllCompanies();

	/**
	 * Delete company.
	 *
	 * @param company
	 *            the company
	 */
	public Response deleteCompany(Long id);
}
