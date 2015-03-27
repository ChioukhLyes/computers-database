package com.excilys.mapper.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerDTOMapper;

// TODO: Auto-generated Javadoc
/**
 * The Enum ComputerDTOmapperImpl.
 */
public enum ComputerDTOmapperImpl implements ComputerDTOMapper {

	/** The instance. */
	INSTANCE;

	/** The logger. */
	Logger logger = (Logger) LoggerFactory.getLogger(CompanyMapperImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.mapper.ComputerDTOMapper#MappComputer(java.sql.ResultSet)
	 */
	@Override
	public ComputerDTO mappComputer(ResultSet resultSet) {
		ComputerDTO computerDTO = new ComputerDTO();
		try {
			if (resultSet != null && resultSet.next()) {
				LocalDate introduced = null;
				LocalDate discontinued = null;
				String name = resultSet.getString("name");
				Long id = resultSet.getLong("id");
				if (resultSet.getTimestamp("introduced") != null)
					introduced = resultSet.getTimestamp("introduced")
							.toLocalDateTime().toLocalDate();
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
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			System.err.println(e.getMessage());
		}
		return computerDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.mapper.ComputerDTOMapper#MappComputers(java.sql.ResultSet)
	 */
	@Override
	public List<ComputerDTO> mappComputers(ResultSet resultSet) {
		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();

		try {
			while (resultSet != null && resultSet.next()) {
				LocalDate introduced = null;
				LocalDate discontinued = null;
				Long id = resultSet.getLong("id");
				String name = resultSet.getString("name");
				if (resultSet.getTimestamp("introduced") != null)
					introduced = resultSet.getTimestamp("introduced")
							.toLocalDateTime().toLocalDate();
				if (resultSet.getTimestamp("discontinued") != null)
					discontinued = resultSet.getTimestamp("discontinued")
							.toLocalDateTime().toLocalDate();

				Long idcompany = resultSet.getLong("company_id");
				String companyName = resultSet.getString("compa.name");
				ComputerDTO computerDTO = new ComputerDTO();
				computerDTO.setId(id);
				computerDTO.setName(name);
				computerDTO.setIntroduced(introduced);
				computerDTO.setDiscontinued(discontinued);
				computerDTO.setCompanyId(idcompany);
				computerDTO.setCompanyName(companyName);
				computers.add(computerDTO);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			System.err.println(e.getMessage());
		}
		return computers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.mapper.ComputerDTOMapper#MappComputerInPreparedStatemetInsert
	 * (java.sql.PreparedStatement, com.excilys.dto.ComputerDTO)
	 */
	@Override
	public void mappComputerInPreparedStatemetInsert(
			PreparedStatement preparedStatement, ComputerDTO computerDTO) {

		try {
			preparedStatement.setString(1, computerDTO.getName());
			if (computerDTO.getIntroduced() != null)
				preparedStatement.setDate(2,
						Date.valueOf(computerDTO.getIntroduced()));
			else
				preparedStatement.setDate(2, null);

			if (computerDTO.getDiscontinued() != null)
				preparedStatement.setDate(3,
						Date.valueOf(computerDTO.getDiscontinued()));
			else
				preparedStatement.setDate(3, null);

			if (computerDTO.getCompanyId() != null
					&& computerDTO.getCompanyId() > 0)
				preparedStatement.setLong(4, computerDTO.getCompanyId());
			else
				preparedStatement.setString(4, null);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
			System.err.println(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excilys.mapper.ComputerDTOMapper#MappComputerInPreparedStatemetUpdate
	 * (java.sql.PreparedStatement, com.excilys.dto.ComputerDTO)
	 */
	@Override
	public void mappComputerInPreparedStatemetUpdate(
			PreparedStatement preparedStatement, ComputerDTO computerDTO) {
		try {
			preparedStatement.setString(1, computerDTO.getName());

			if (computerDTO.getIntroduced() != null)
				preparedStatement.setDate(2,
						Date.valueOf(computerDTO.getIntroduced()));
			else
				preparedStatement.setDate(2, null);

			if (computerDTO.getDiscontinued() != null)
				preparedStatement.setDate(3,
						Date.valueOf(computerDTO.getDiscontinued()));
			else
				preparedStatement.setDate(3, null);

			if (computerDTO.getCompanyId() != null
					&& computerDTO.getCompanyId() > 0)
				preparedStatement.setLong(4, computerDTO.getCompanyId());
			else
				preparedStatement.setLong(4, 0);

			preparedStatement.setLong(5, computerDTO.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			System.err.println(e.getMessage());
		}

	}
}
