package com.excilys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;

import com.excilys.dao.interfaces.CompanyDAO;
import com.excilys.models.Company;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyDaoImplementation.
 */
@Repository
public class CompanyDaoImpl implements CompanyDAO {

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(CompanyDaoImpl.class);

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a new company dao impl.
	 */
	public CompanyDaoImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.persistence.CompanyDAO#findAllCompanies()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> findAllCompanies() {
		List<Company> companies = new ArrayList<Company>();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Company");
		companies = query.list();
		logger.info("Getting all companies");
		return companies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.persistence.CompanyDAO#findAllCompanies(int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> findAllCompanies(int limit, int offset) {

		List<Company> companies = new ArrayList<Company>();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Company");
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		companies = query.list();
		logger.info("Get " + limit + " Companies start from " + offset);
		return companies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.persistence.CompanyDAO#findCompanyById(java.lang.Long)
	 */
	@Override
	public Company findCompanyById(Long id) {
		Company company = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Company where id= :id");
		query.setLong("id", id);
		@SuppressWarnings("unchecked")
		List<Company> CompanyList = query.list();
		if (CompanyList.size() > 0) {
			company = CompanyList.get(0);
		}
		logger.info("Find company by Id");
		return company;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.persistence.CompanyDAO#deleteCompany(com.excilys.model.Company
	 * )
	 */
	@Override
	public void deleteCompany(Company company) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("DELETE Company WHERE id= :id");
		query.setLong("id", company.getId());
		query.executeUpdate();
		logger.info("Company deletion");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.persistence.CompanyDAO#getCountCompanies()
	 */
	@Override
	public int getCountCompanies() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("COUNT(*) FROM  Company;");
		logger.info("Computer get count");
		return (int) query.uniqueResult();
	}

}
