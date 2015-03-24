package com.excilys.mapper;

import java.sql.ResultSet;
import java.util.List;

import com.excilys.model.Company;

// TODO: Auto-generated Javadoc
/**
 * The Interface CompanyMapper.
 */
public interface CompanyMapper {

	/**
	 * Mapp company.
	 *
	 * @param resultSet
	 *            the result set
	 * @return the company
	 */
	public Company MappCompany(ResultSet resultSet);

	/**
	 * Mapp companies.
	 *
	 * @param resultSet
	 *            the result set
	 * @return the list
	 */
	public List<Company> MappCompanies(ResultSet resultSet);
}
