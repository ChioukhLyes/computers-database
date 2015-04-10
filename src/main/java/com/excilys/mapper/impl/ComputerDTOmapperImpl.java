package com.excilys.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

import com.excilys.dto.ComputerDTO;

// TODO: Auto-generated Javadoc
/**
 * The Enum ComputerDTOmapperImpl.
 */
@Service
public class ComputerDTOmapperImpl implements RowMapper<ComputerDTO> {

	/** The logger. */
	Logger logger = (Logger) LoggerFactory.getLogger(ComputerDTOmapperImpl.class);

	/**
	 * Instantiates a new computer dt omapper impl.
	 */
	public ComputerDTOmapperImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public ComputerDTO mapRow(ResultSet resultSet, int arg1)
			throws SQLException {
		final ComputerDTO computerDTO = new ComputerDTO();
		LocalDate introduced = null;
		LocalDate discontinued = null;
		String name = resultSet.getString("name");
		Long id = resultSet.getLong("id");
		if (resultSet.getTimestamp("introduced") != null)
			introduced = resultSet.getTimestamp("introduced").toLocalDateTime()
					.toLocalDate();
		if (resultSet.getTimestamp("discontinued") != null)
			discontinued = resultSet.getTimestamp("discontinued")
					.toLocalDateTime().toLocalDate();

		Long idcompany = resultSet.getLong("company_id");
		String companyName = resultSet.getString("compa.name");
		computerDTO.setId(id);
		computerDTO.setName(name);
		computerDTO.setIntroduced(introduced);
		computerDTO.setDiscontinued(discontinued);
		computerDTO.setCompanyId(idcompany);
		computerDTO.setCompanyName(companyName);
		return computerDTO;
	}
}
