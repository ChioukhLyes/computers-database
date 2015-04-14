package com.excilys.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.ComputerDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDaoImpl.
 */
@Repository
public class ComputerDaoImpl implements ComputerDAO {

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(ComputerDaoImpl.class);

	// /** The computerDTO mapper. */
	// @Autowired
	// ComputerDTOmapperImpl computerMapper;
	//
	// /** The jdbc template. */
	// @Autowired
	// private JdbcTemplate jdbcTemplate;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private Computer computer;
	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#findAllComputers()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ComputerDTO> findAllComputers() {

		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Computer");
		computers = query.list();
		return computers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistence.ComputerDAO#findAllComputers(int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ComputerDTO> findAllComputers(int limit, int offset) {
		
		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM ComputerDTO");
		query.setFirstResult(offset);
		query.setMaxResults(limit);
//		query.setInteger("limit", limit);
//		query.setInteger("offset", offset);
		computers = query.list();
		logger.info("Get "+limit+" Computers start from "+offset);
		return computers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#findComputerById(java.lang.Long)
	 */
	@Override
	public ComputerDTO findComputerById(Long id) {
		ComputerDTO ComputerDTO = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM ComputerDTO where id= :id");
		query.setLong("id", id);
		@SuppressWarnings("unchecked")
		List<ComputerDTO> ComputerList = query.list();
		if (ComputerList.size() > 0) {
			ComputerDTO = ComputerList.get(0);
		}
		logger.info("Find computerDTO by Id");
		return ComputerDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#insertComputer(model.Computer)
	 */
	@Override
	public void insertComputer(ComputerDTO computerDTO) {
		Session session = sessionFactory.getCurrentSession();
		computer.setName(computerDTO.getName());
		computer.setIntroduced(computerDTO.getIntroduced());
		computer.setDiscontinued(computerDTO.getDiscontinued());
		computer.setCompany(new Company(computerDTO.getCompanyId(), computerDTO.getCompanyName()));
		session.save(computer);
		logger.info("Computer insertion");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.persistence.ComputerDAO#deleteComputerByCompanyId(com.excilys
	 * .model.Company)
	 */
	@Override
	public void deleteComputerByCompanyId(Company company) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("DELETE FROM ComputerDTO WHERE company_id= :id");
		query.setLong("id", company.getId());
		query.executeUpdate();
		logger.info("Computer deletion by deletion company");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#deleteComputer(model.Computer)
	 */
	@Override
	public void deleteComputer(ComputerDTO computerDTO) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("DELETE ComputerDTO WHERE id= :id");
		query.setLong("id", computerDTO.getId());
		query.executeUpdate();
		logger.info("Computer deletion");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#updateComputer(model.Computer)
	 */
	@Override
	public void updateComputer(ComputerDTO computerDTO) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(computerDTO);
		logger.info("Computer update");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistence.ComputerDAO#getCountComputers()
	 */
	@Override
	public int getCountComputers(String search) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("COUNT(*) FROM ComputerDTO comp LEFT JOIN Company compa ON comp.company_id = compa.id WHERE comp.name like :search OR compa.name like :search");
		query.setString("search", "%" + search + "%");
		logger.info("Computer get count");
		return (int) query.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.persistence.ComputerDAO#findAllComputersCompaniesByName(int,
	 * int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ComputerDTO> findAllComputersCompaniesByName(int limit,
			int offset, String orderBy, String search, String orderOption) {
		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		Session session = sessionFactory.getCurrentSession();
		if (orderBy.equals("companyname")) {
			Query query = session
					.createQuery("FROM ComputerDTO comp LEFT JOIN Company compa ON comp.company_id = compa.id WHERE comp.name "
							+ "LIKE :search OR compa.name LIKE :search ORDER BY compa.name :orderoption limit :limit offset :offset;");
			query.setString("search", "%" + search + "%");
			query.setString("orderoption", orderOption);
			query.setInteger("limit", limit);
			query.setInteger("offset", offset);
			computers = query.list();
		} else{
			Query query = session
					.createQuery("FROM ComputerDTO comp LEFT JOIN Company compa ON comp.company_id = compa.id WHERE comp.name LIKE :search OR compa.name LIKE :search ORDER BY compa.:orderby :orderoption limit :limit offset :offset;");
			query.setString("search", "%" + search + "%");
			query.setString("orderoption", orderOption);
			query.setString("orderby", orderBy);
			query.setInteger("limit", limit);
			query.setInteger("offset", offset);
			computers = query.list();
		}
		logger.info("Computers get by companies name");
		return computers;
	}
}
