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
	 * @param id
	 *            the id
	 * @return the response
	 */
	public Response deleteCompany(Long id);

	/**
	 * Find all companies quota.
	 *
	 * @param limit
	 *            the limit
	 * @param offset
	 *            the offset
	 * @return the response
	 */
	public List<Company> findAllCompaniesQuota(int limit, int offset);
}
