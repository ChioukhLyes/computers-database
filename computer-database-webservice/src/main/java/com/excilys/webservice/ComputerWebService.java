/*
 * 
 */
package com.excilys.webservice;

import java.util.List;

import javax.ws.rs.core.Response;

import com.excilys.dto.ComputerDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ComputerWebService.
 */
public interface ComputerWebService {

	/**
	 * Find by id.
	 *
	 * @param id
	 *            the id
	 * @return the computer dto
	 */
	public ComputerDTO findById(Long id);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<ComputerDTO> findAll();

	// public List<ComputerDTO> findPage(); Find with pages

	/**
	 * Adds the computer.
	 *
	 * @param ComputerDTO
	 *            the computer dto
	 * @return the response
	 */
	public Response addComputer(ComputerDTO ComputerDTO);

	/**
	 * Update computer.
	 *
	 * @param ComputerDTO
	 *            the computer dto
	 * @return the response
	 */
	public Response updateComputer(ComputerDTO ComputerDTO);

	/**
	 * Delete computer.
	 *
	 * @param id
	 *            the id
	 * @return the response
	 */
	public Response deleteComputer(Long id);

	/**
	 * Fin all computers quota.
	 *
	 * @param limit
	 *            the limit
	 * @param offset
	 *            the offset
	 * @return the list
	 */
	public List<ComputerDTO> findAllComputersQuota(int limit, int offset);

}