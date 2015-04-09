/*
 * 
 */
package com.excilys.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

import com.excilys.model.Company;

// TODO: Auto-generated Javadoc
/**
 * The Enum CompanyMapperImpl.
 */

@Service
public class CompanyMapperImpl implements RowMapper<Company> {

	/** The logger. */
	Logger logger = (Logger) LoggerFactory.getLogger(CompanyMapperImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public Company mapRow(ResultSet resultSet, int arg1) throws SQLException {
		final Company company = new Company();
		company.setId(resultSet.getLong("id"));
		company.setName(resultSet.getString("name"));
		logger.info("mapRow : Company with id" + company.getId());
		return company;
	}

}
