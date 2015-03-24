package com.excilys.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;

// TODO: Auto-generated Javadoc
/**
 * The Enum CompanyMapperImpl.
 */
public enum CompanyMapperImpl implements CompanyMapper {

	/** The instance. */
	INSTANCE;

	/** The logger. */
	Logger logger = (Logger) LoggerFactory.getLogger(CompanyMapperImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.mapper.CompanyMapper#MappCompany(java.sql.ResultSet)
	 */
	@Override
	public Company MappCompany(ResultSet resultSet) {
		Company company = new Company();
		try {
			if (resultSet.next()) {
				company.setId(resultSet.getLong("id"));
				company.setName(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			System.err.println(e.getMessage());
		}
		return company;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.mapper.CompanyMapper#MappCompanies(java.sql.ResultSet)
	 */
	@Override
	public List<Company> MappCompanies(ResultSet resultSet) {

		List<Company> companies = new ArrayList<Company>();

		try {
			while (resultSet.next()) {
				Company company = new Company();
				company.setId(resultSet.getLong("id"));
				company.setName(resultSet.getString("name"));
				companies.add(company);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			System.err.println(e.getMessage());
		}
		return companies;
	}

}
