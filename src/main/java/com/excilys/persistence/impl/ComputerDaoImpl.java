package com.excilys.persistence.impl;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.impl.ComputerDTOmapperImpl;
import com.excilys.model.Company;
import com.excilys.persistence.ComputerDAO;
import com.excilys.services.ServiceComputer;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDaoImpl.
 */
@Repository
public class ComputerDaoImpl implements ComputerDAO {

	/** The logger. */
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(ServiceComputer.class);

	/** The computer mapper. */
	@Autowired
	ComputerDTOmapperImpl computerMapper;

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#findAllComputers()
	 */
	@Override
	public List<ComputerDTO> findAllComputers() {
		return jdbcTemplate
				.query("SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id;",
						computerMapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistence.ComputerDAO#findAllComputers(int, int)
	 */
	@Override
	public List<ComputerDTO> findAllComputers(int limit, int offset) {
		return jdbcTemplate
				.query("SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id limit ? offset ?;",
						new Object[] { limit, offset }, computerMapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#findComputerById(java.lang.Long)
	 */
	@Override
	public ComputerDTO findComputerById(Long id) {
		return jdbcTemplate
				.queryForObject(
						"SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id WHERE comp.id=?;",
						new Object[] { id }, computerMapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#insertComputer(model.Computer)
	 */
	@Override
	public boolean insertComputer(ComputerDTO computer) {
		String quary = new String(
				"INSERT INTO computer(name, introduced, discontinued, company_id) VALUES ('"
						+ computer.getName() + "','" + computer.getIntroduced()
						+ "','" + computer.getDiscontinued() + "',"
						+ computer.getCompanyId() + ");");
		logger.info("Computer insertion");
		return this.jdbcTemplate.update(quary) != 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.persistence.ComputerDAO#deleteComputerByCompanyId(com.excilys
	 * .model.Company)
	 */
	@Override
	public boolean deleteComputerByCompanyId(Company company) {
		logger.info("Computer deletion by deletion company");
		return jdbcTemplate.update("DELETE FROM computer WHERE company_id=?;",
				new Object[] { company.getId() }) != 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#deleteComputer(model.Computer)
	 */
	@Override
	public boolean deleteComputer(ComputerDTO computer) {
		logger.info("Computer deletion");
		return jdbcTemplate.update("DELETE FROM computer WHERE id=?;",
				computer.getId()) != 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.ComputerDAO#updateComputer(model.Computer)
	 */
	@Override
	public boolean updateComputer(ComputerDTO computer) {
		logger.info("Computer update");
		return jdbcTemplate.update("UPDATE computer set name='"
				+ computer.getName() + "', introduced='"
				+ computer.getIntroduced() + "' , discontinued='"
				+ computer.getDiscontinued() + "' , company_id="
				+ computer.getCompanyId() + " WHERE id=" + computer.getId()
				+ ";") != 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistence.ComputerDAO#getCountComputers()
	 */
	@Override
	public int getCountComputers(String search) {
		String query = new String(
				"SELECT count(*) FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id");
		if (search != null) {
			query += " WHERE comp.name LIKE '%" + search
					+ "%' OR compa.name LIKE '%" + search + "%';";
		}
		return jdbcTemplate.queryForObject(query, Integer.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.persistence.ComputerDAO#findAllComputersCompaniesByName(int,
	 * int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ComputerDTO> findAllComputersCompaniesByName(int limit,
			int offset, String orderBy, String search, String orderOption) {
		String quary = null;
		if (orderBy.equals("companyname"))
			quary = new String(
					"SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id WHERE comp.name LIKE '%"
							+ search
							+ "%'  OR compa.name LIKE '%"
							+ search
							+ "%' ORDER BY compa.name "
							+ orderOption
							+ " limit ? offset ?;");
		else
			quary = new String(
					"SELECT * FROM computer comp LEFT JOIN company compa ON comp.company_id = compa.id WHERE comp.name LIKE '%"
							+ search
							+ "%'  OR compa.name LIKE '%"
							+ search
							+ "%' ORDER BY comp."
							+ orderBy
							+ " "
							+ orderOption
							+ " limit ? offset ?;");
		return jdbcTemplate.query(quary, computerMapper);
	}

}
