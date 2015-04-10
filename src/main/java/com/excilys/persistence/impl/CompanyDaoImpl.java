package com.excilys.persistence.impl;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;

import com.excilys.mapper.impl.CompanyMapperImpl;
import com.excilys.model.Company;
import com.excilys.persistence.CompanyDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyDaoImplementation.
 */
@Repository
public class CompanyDaoImpl implements CompanyDAO {

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(CompanyDaoImpl.class);

	
	/** The company mapper. */
	@Autowired
	private CompanyMapperImpl companyMapper;

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Instantiates a new company dao impl.
	 */
	public CompanyDaoImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.CompanyDAO#findAllCompanies()
	 */
	@Override
	public List<Company> findAllCompanies() {
		return jdbcTemplate.query("SELECT id, name FROM company;",
				companyMapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.CompanyDAO#findAllCompanies()
	 */
	@Override
	public List<Company> findAllCompanies(int limit, int offset) {
		return jdbcTemplate.query(
				"SELECT id, name FROM company limit ? offset ?;", new Object[] {
						limit, offset }, companyMapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.CompanyDAO#findCompanyById(java.lang.Long)
	 */
	@Override
	public Company findCompanyById(Long id) {
		return jdbcTemplate.queryForObject(
				"SELECT id, name FROM company WHERE id= ?;",
				new Object[] { id }, companyMapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.persistence.CompanyDAO#deleteCompany(com.excilys.model.Company
	 * )
	 */
	@Override
	public boolean deleteCompany(Company company) {
		logger.info("Company deletion");
		return jdbcTemplate.update("DELETE FROM company WHERE id=?;",
				company.getId()) != 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.persistence.CompanyDAO#getCountCompanies()
	 */
	@Override
	public int getCountCompanies() {
		return jdbcTemplate.queryForObject("SELECT count(*) FROM company;",
				Integer.class);
	}

}
