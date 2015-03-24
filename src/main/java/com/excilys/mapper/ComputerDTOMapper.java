package com.excilys.mapper;

import java.sql.ResultSet;
import java.util.List;

import com.excilys.dto.ComputerDTO;
import java.sql.PreparedStatement;

// TODO: Auto-generated Javadoc
/**
 * The Interface ComputerDTOMapper.
 */
public interface ComputerDTOMapper {

	/**
	 * Mapp computer.
	 *
	 * @param resultSet
	 *            the result set
	 * @return the computer dto
	 */
	public ComputerDTO MappComputer(ResultSet resultSet);

	/**
	 * Mapp computers.
	 *
	 * @param resultSet
	 *            the result set
	 * @return the list
	 */
	public List<ComputerDTO> MappComputers(ResultSet resultSet);

	
	/**
	 * Mapp computer in prepared statemet insert.
	 *
	 * @param preparedStatement the prepared statement
	 * @param computerDTO the computer dto
	 */
	public void MappComputerInPreparedStatemetInsert(
			PreparedStatement preparedStatement, ComputerDTO computerDTO);
	
	/**
	 * Mapp computer in prepared statemet update.
	 *
	 * @param preparedStatement the prepared statement
	 * @param computerDTO the computer dto
	 */
	public void MappComputerInPreparedStatemetUpdate(
			PreparedStatement preparedStatement, ComputerDTO computerDTO);

}
